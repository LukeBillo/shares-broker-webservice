package org.lukebillington.university.sharesbroker.controllers;

import com.mongodb.client.model.Filters;
import org.lukebillington.university.sharesbroker.data.ICompanySharesRepository;
import org.lukebillington.university.sharesbroker.data.IUsersRepository;
import org.lukebillington.university.sharesbroker.data.models.requests.BuyShareRequest;
import org.lukebillington.university.sharesbroker.data.models.CompanyShare;
import org.lukebillington.university.sharesbroker.data.models.User;
import org.lukebillington.university.sharesbroker.data.models.UserShare;
import org.lukebillington.university.sharesbroker.services.CurrencyConversion.CurrencyConversionWS;
import org.lukebillington.university.sharesbroker.services.CurrencyConversion.CurrencyConversionWSService;
import org.lukebillington.university.sharesbroker.utils.HttpResponseHelper;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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

    public static final String Path = "shares";

    private static class Errors {
        static final String SHARES_NOT_FOUND_OR_INSUFFICIENT = "The shares requested to buy were not found or had an insufficient number of shares available.";
        static final String USER_NOT_FOUND = "The user requesting to buy the shares was not found.";
    }

    @GET
    public Response getShares(@QueryParam("currency") String currency) {
        List<CompanyShare> sharesToReturn = companySharesRepository.getShares();

        for (CompanyShare share : sharesToReturn) {
            if (!share.getSharePrice().getCurrency().equals(currency)) {
            }
        }

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

            usersRepository.updateUser(buyingUser);
            buyingShare.setNumberOfShares(buyingShare.getNumberOfShares() - buyShareRequest.getNumberOfSharesToBuy());
            companySharesRepository.updateShare(buyShareRequest.getCompanySymbol(), buyingShare);

            return Response.ok().build();
        }

        UserShare newUserShare = new UserShare(buyingShare, buyShareRequest.getNumberOfSharesToBuy());
        buyingUser.getOwnedShares().add(newUserShare);

        usersRepository.updateUser(buyingUser);
        buyingShare.setNumberOfShares(buyingShare.getNumberOfShares() - buyShareRequest.getNumberOfSharesToBuy());
        companySharesRepository.updateShare(buyShareRequest.getCompanySymbol(), buyingShare);

        return Response.ok().build();
    }
}
