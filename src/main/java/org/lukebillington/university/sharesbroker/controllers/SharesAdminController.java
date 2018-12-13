package org.lukebillington.university.sharesbroker.controllers;

import com.mongodb.client.model.Filters;
import org.lukebillington.university.sharesbroker.Main;
import org.lukebillington.university.sharesbroker.data.ICompanySharesRepository;
import org.lukebillington.university.sharesbroker.data.models.CompanyShare;
import org.lukebillington.university.sharesbroker.data.models.CompanyShareUpdates;
import org.lukebillington.university.sharesbroker.data.models.CreateCompanyShareRequest;
import org.lukebillington.university.sharesbroker.data.models.UpdateCompanyShareRequest;
import org.lukebillington.university.sharesbroker.utils.HttpResponseHelper;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * Shares admin controller - for usage only by admin users.
 * Allows management of available shares.
 */
@Path(SharesAdminController.Path)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SharesAdminController {
    // todo: implement user admin checking

    @Inject
    ICompanySharesRepository companySharesRepository;

    public static final String Path = "shares/admin";

    private static class Errors {
        static final String COMPANY_SYMBOL_EXISTS = "A company share with that company symbol already exists.";
    }

    @PATCH
    public Response partialUpdateShare(UpdateCompanyShareRequest updateShareRequest) {
        CompanyShare shareToUpdate = companySharesRepository.getShare(Filters.eq("companySymbol", updateShareRequest.getCompanySymbol()));
        CompanyShareUpdates updates = updateShareRequest.getUpdates();

        String updatedCurrency = updates.getCurrency();
        if (updatedCurrency != null) {
            shareToUpdate.getSharePrice().setCurrency(updates.getCurrency());
        }


        return Response.ok().build();
    }

    @POST
    public Response createNewShare(CreateCompanyShareRequest newShareRequest) {
        CompanyShare newShare = new CompanyShare(newShareRequest);
        boolean shareWasCreated = companySharesRepository.insertShare(newShare);

        if (!shareWasCreated) {
            return HttpResponseHelper.CreateConflictRequest(Errors.COMPANY_SYMBOL_EXISTS);
        }

        URI createdShareResourceLocation = UriBuilder.fromPath(Main.BaseUri + SharesController.Path).build();
        return Response.created(createdShareResourceLocation).build();
    }
}
