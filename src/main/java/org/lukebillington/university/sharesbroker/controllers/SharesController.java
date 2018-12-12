package org.lukebillington.university.sharesbroker.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mongodb.client.model.Filters;
import org.lukebillington.university.sharesbroker.data.ICompanySharesRepository;
import org.lukebillington.university.sharesbroker.data.IUsersRepository;
import org.lukebillington.university.sharesbroker.data.models.BuyShareRequest;
import org.lukebillington.university.sharesbroker.data.models.CompanyShare;
import org.lukebillington.university.sharesbroker.data.models.User;
import org.lukebillington.university.sharesbroker.data.models.UserShare;
import org.lukebillington.university.sharesbroker.utils.HttpResponseHelper;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Shares controller - allows access and management of shares
 */
@Path("shares")
@Consumes(MediaType.APPLICATION_JSON)
public class SharesController {
    @Inject
    ICompanySharesRepository companySharesRepository;

    @Inject
    IUsersRepository usersRepository;

    private static class Errors {
        static final String SHARES_NOT_FOUND_OR_INSUFFICIENT = "The shares requested to buy were not found or had an insufficient number of shares available.";
        static final String USER_NOT_FOUND = "The user requesting to buy the shares was not found.";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTop10Shares() {
        List<CompanyShare> sharesToReturn = companySharesRepository.getShares();
        return Response.ok(sharesToReturn).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response buyShare(BuyShareRequest buyShareRequest) {
        CompanyShare buyingShare = companySharesRepository.getShare(Filters.and(
                Filters.eq("companySymbol", buyShareRequest.getCompanySymbol()),
                Filters.gte("numberOfShares", buyShareRequest.getNumberOfSharesToBuy())
        ));

        if (buyingShare == null) {
            return HttpResponseHelper.CreateBadRequest(Errors.SHARES_NOT_FOUND_OR_INSUFFICIENT);
        }

        User buyingUser = usersRepository.getUser(buyShareRequest.getBuyerUsername());

        if (buyingUser == null) {
            return HttpResponseHelper.CreateBadRequest(Errors.USER_NOT_FOUND);
        }

        UserShare boughtShare = new UserShare(buyingShare);
        buyingUser.getOwnedShares().add(boughtShare);
        buyingShare.setNumberOfShares(buyingShare.getNumberOfShares() - 1);

        usersRepository.updateUser(buyingUser);
        companySharesRepository.updateShare(buyingShare);

        return Response.ok().build();
    }
}
