package org.lukebillington.university.sharesbroker.controllers;

import com.mongodb.client.model.Filters;
import org.lukebillington.university.sharesbroker.data.ICompanySharesRepository;
import org.lukebillington.university.sharesbroker.data.IUsersRepository;
import org.lukebillington.university.sharesbroker.data.models.CompanyShare;
import org.lukebillington.university.sharesbroker.data.models.User;
import org.lukebillington.university.sharesbroker.data.models.OwnedShare;
import org.lukebillington.university.sharesbroker.data.models.UserShare;
import org.lukebillington.university.sharesbroker.services.CurrencyConversion.ICurrencyConversionWS;
import org.lukebillington.university.sharesbroker.utils.HttpResponseHelper;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.lukebillington.university.sharesbroker.utils.ListUtils.ContainsPartialString;

@Path(org.lukebillington.university.sharesbroker.controllers.UserSharesController.Path)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserSharesController {
    @Inject
    ICompanySharesRepository companySharesRepository;

    @Inject
    IUsersRepository usersRepository;

    @Inject
    ICurrencyConversionWS currencyConversionWS;

    public static final String Path = "user/shares";

    private static class Errors {
        static final String USER_NOT_FOUND = "The username given was not found.";
        static final String INVALID_CURRENCY = "The currency requested is not available.";
    }

    private void ConvertUserShareCurrency(UserShare share, String requestedCurrency) {
        String sharePriceCurrency = share.getSharePrice().getCurrency();

        if (!requestedCurrency.isEmpty() && !sharePriceCurrency.equals(requestedCurrency)) {
            double conversionRate = currencyConversionWS.getConversionRate(sharePriceCurrency, requestedCurrency);
            double convertedSharePrice = share.getSharePrice().getValue() * conversionRate;
            share.getSharePrice().setValue(convertedSharePrice);
            share.getSharePrice().setCurrency(requestedCurrency);
        }
    }

    @GET
    public Response GetUserShares(@QueryParam("username") String username, @QueryParam("currency") String currency) {
        User requestedUser = usersRepository.getUser(username);
        List<OwnedShare> ownedShares = requestedUser.getOwnedShares();

        List<UserShare> userShares = new ArrayList<>();

        // finds the prices for each owned share, and then converts to
        // a user share (which has price on the object)
        for (OwnedShare share : ownedShares) {
            CompanyShare companyShare = companySharesRepository.getShare(Filters.and(
                    Filters.eq("companySymbol", share.getCompanySymbol())
            ));

            userShares.add(new UserShare(companyShare, share.getNumberOfShares()));
        }

        if (currency == null) {
            return Response.ok(userShares).build();
        }

        boolean isRequestedCurrencyAvailable = ContainsPartialString(currencyConversionWS.getCurrencyCodes(), currency);

        if (!isRequestedCurrencyAvailable)
            return HttpResponseHelper.CreateBadRequestResponse(UserSharesController.Errors.INVALID_CURRENCY);

        // convert all to requested currency
        userShares.forEach(userShare -> ConvertUserShareCurrency(userShare, currency));

        return Response.ok(userShares).build();
    }
}