package org.lukebillington.university.sharesbroker.controllers;

import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.lukebillington.university.sharesbroker.data.ICompanySharesRepository;
import org.lukebillington.university.sharesbroker.data.IUsersRepository;
import org.lukebillington.university.sharesbroker.data.models.builders.CompanyShareQueryBuilder;
import org.lukebillington.university.sharesbroker.data.models.requests.BuyShareRequest;
import org.lukebillington.university.sharesbroker.data.models.CompanyShare;
import org.lukebillington.university.sharesbroker.data.models.User;
import org.lukebillington.university.sharesbroker.data.models.UserShare;
import org.lukebillington.university.sharesbroker.services.CurrencyConversion.ICurrencyConversionWS;
import org.lukebillington.university.sharesbroker.utils.HttpResponseHelper;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

import static org.lukebillington.university.sharesbroker.utils.ListUtils.ContainsPartialString;

/**
 * Shares controller - allows any user to get and buy
 * existing shares.
 */
@Path(SharesController.Path)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SharesController {
    @Inject
    ICompanySharesRepository companySharesRepository;

    @Inject
    IUsersRepository usersRepository;

    @Inject
    ICurrencyConversionWS currencyConversionWS;

    public static final String Path = "shares";

    private static class Errors {
        static final String SHARES_NOT_FOUND_OR_INSUFFICIENT = "The shares requested to buy were not found or had an insufficient number of shares available.";
        static final String USER_NOT_FOUND = "The user requesting to buy the shares was not found.";
        static final String INVALID_CURRENCY = "The currency requested is not available.";
    }

    private void ConvertCompanySharePrice(CompanyShare share, String requestedCurrency) {
        String sharePriceCurrency = share.getSharePrice().getCurrency();

        if (!requestedCurrency.isEmpty() && !sharePriceCurrency.equals(requestedCurrency)) {
            double conversionRate = currencyConversionWS.getConversionRate(sharePriceCurrency, requestedCurrency);
            double convertedSharePrice = share.getSharePrice().getValue() * conversionRate;
            share.getSharePrice().setValue(convertedSharePrice);
            share.getSharePrice().setCurrency(requestedCurrency);
        }
    }

    @GET
    public Response getShares(
            @QueryParam("companySymbol") String companySymbol,
            @QueryParam("companyName") String companyName,
            @QueryParam("availableSharesLessThan") Integer availableSharedLessThan,
            @QueryParam("availableSharesMoreThan") Integer availableSharesMoreThan,
            @QueryParam("priceLessThan") Double priceLessThan,
            @QueryParam("priceMoreThan") Double priceMoreThan,
            @QueryParam("priceLastUpdatedBefore") Date priceLastUpdatedBefore,
            @QueryParam("priceLastUpdatedAfter") Date priceLastUpdatedAfter,
            @QueryParam("currency") String currency,
            @QueryParam("limit") Integer limit) {
        Bson query = new CompanyShareQueryBuilder()
                .WithCompanyName(companyName)
                .WithCompanySymbol(companySymbol)
                .WithAvailableShares(availableSharedLessThan, availableSharesMoreThan)
                .WithLastUpdated(priceLastUpdatedBefore, priceLastUpdatedAfter)
                .build();

        // default limit is 50 so that the response size
        // is controlled.
        if (limit == null)
            limit = 50;

        List<CompanyShare> sharesToReturn = query == null ?
                companySharesRepository.getShares(limit) :
                companySharesRepository.getShares(query);

        if (currency == null) {
            return Response.ok(sharesToReturn).build();
        }

        boolean isRequestedCurrencyAvailable = ContainsPartialString(currencyConversionWS.getCurrencyCodes(), currency);

        if (!isRequestedCurrencyAvailable)
            return HttpResponseHelper.CreateBadRequestResponse(Errors.INVALID_CURRENCY);

        // convert all to requested currency
        sharesToReturn.forEach(companyShare -> ConvertCompanySharePrice(companyShare, currency));

        // this is separated from the rest of the query so that
        // it happens after currency conversion.
        sharesToReturn.removeIf(
                companyShare -> !(companyShare.getSharePrice().getValue() < priceLessThan &&
                companyShare.getSharePrice().getValue() > priceMoreThan)
        );

        sharesToReturn = sharesToReturn.subList(0, limit);

        return Response.ok(sharesToReturn).build();
    }

    @POST
    public Response buyShare(BuyShareRequest buyShareRequest) {
        CompanyShare buyingShare = companySharesRepository.getShare(Filters.and(
                Filters.eq("companySymbol", buyShareRequest.getCompanySymbol()),
                Filters.gte("numberOfShares", buyShareRequest.getNumberOfSharesToBuy())
        ));

        if (buyingShare == null) {
            return HttpResponseHelper.CreateBadRequestResponse(Errors.SHARES_NOT_FOUND_OR_INSUFFICIENT);
        }

        User buyingUser = usersRepository.getUser(buyShareRequest.getBuyerUsername());

        if (buyingUser == null) {
            return HttpResponseHelper.CreateBadRequestResponse(Errors.USER_NOT_FOUND);
        }

        UserShare existingUserShare = buyingUser.getUserShareIfExists(buyingShare.getCompanySymbol());

        if (existingUserShare != null) {
            int updatedNumberOfShares = existingUserShare.getNumberOfShares() + buyShareRequest.getNumberOfSharesToBuy();
            existingUserShare.setNumberOfShares(updatedNumberOfShares);

            return UpdateUserSharesAndOk(buyShareRequest, buyingShare, buyingUser);
        }

        UserShare newUserShare = new UserShare(buyingShare, buyShareRequest.getNumberOfSharesToBuy());
        buyingUser.getOwnedShares().add(newUserShare);

        return UpdateUserSharesAndOk(buyShareRequest, buyingShare, buyingUser);
    }

    private Response UpdateUserSharesAndOk(BuyShareRequest buyShareRequest, CompanyShare buyingShare, User buyingUser) {
        usersRepository.updateUser(buyingUser);
        buyingShare.setNumberOfShares(buyingShare.getNumberOfShares() - buyShareRequest.getNumberOfSharesToBuy());
        companySharesRepository.updateShare(buyShareRequest.getCompanySymbol(), buyingShare);

        return Response.ok().build();
    }
}
