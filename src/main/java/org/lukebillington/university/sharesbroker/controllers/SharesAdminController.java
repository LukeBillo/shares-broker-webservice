package org.lukebillington.university.sharesbroker.controllers;

import com.mongodb.client.model.Filters;
import org.lukebillington.university.sharesbroker.Main;
import org.lukebillington.university.sharesbroker.data.ICompanySharesRepository;
import org.lukebillington.university.sharesbroker.data.models.CompanyShare;
import org.lukebillington.university.sharesbroker.data.models.SharePrice;
import org.lukebillington.university.sharesbroker.data.models.requests.CompanyShareUpdates;
import org.lukebillington.university.sharesbroker.data.models.requests.CreateCompanyShareRequest;
import org.lukebillington.university.sharesbroker.data.models.requests.UpdateCompanyShareRequest;
import org.lukebillington.university.sharesbroker.security.AdminEndpoint;
import org.lukebillington.university.sharesbroker.services.IEXTrading.IIEXTTradingService;
import org.lukebillington.university.sharesbroker.services.IEXTrading.models.Quote;
import org.lukebillington.university.sharesbroker.utils.HttpResponseHelper;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.time.Instant;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Shares admin controller - for usage only by admin users.
 * Allows management of available shares.
 */
@Path(SharesAdminController.Path)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@AdminEndpoint
public class SharesAdminController {
    @Inject
    ICompanySharesRepository companySharesRepository;



    public static final String Path = "shares/admin";
    private static final int ThirtyMinutes = 30 * 60 * 1000;

    private static class Errors {
        static final String COMPANY_SYMBOL_EXISTS = "A company share with that company symbol already exists.";
    }

    public SharesAdminController() {
    }

    @PATCH
    public Response partialUpdateShare(UpdateCompanyShareRequest updateShareRequest) {
        CompanyShare shareToUpdate = companySharesRepository.getShare(Filters.eq("companySymbol", updateShareRequest.getCompanySymbol()));
        CompanyShareUpdates updates = updateShareRequest.getUpdates();

        String updatedCurrency = updates.getCurrency();
        if (updatedCurrency != null) {
            shareToUpdate.getSharePrice().setCurrency(updatedCurrency);
            shareToUpdate.getSharePrice().setLastUpdated(Instant.now());
        }

        Double updatedPrice = updates.getPrice();
        if (updatedPrice != null) {
            shareToUpdate.getSharePrice().setValue(updatedPrice);
            shareToUpdate.getSharePrice().setLastUpdated(Instant.now());
        }

        Integer updatedShares = updates.getNumberOfShares();
        if (updatedShares != null)  {
            shareToUpdate.setNumberOfShares(updatedShares);
        }

        String updatedCompanyName = updates.getCompanyName();
        if (updatedCompanyName != null) {
            shareToUpdate.setCompanyName(updatedCompanyName);
        }

        String updatedCompanySymbol = updates.getCompanySymbol();
        if (updatedCompanySymbol != null) {
            // need to check that the updated company symbol does not already exist
            CompanyShare existingCompanyWithSymbol = companySharesRepository.getShare(Filters.eq("companySymbol", updatedCompanySymbol));
            if (existingCompanyWithSymbol != null) {
                return HttpResponseHelper.CreateBadRequestResponse(Errors.COMPANY_SYMBOL_EXISTS);
            }

            shareToUpdate.setCompanySymbol(updatedCompanySymbol);
        }

        companySharesRepository.updateShare(updateShareRequest.getCompanySymbol(), shareToUpdate);

        return Response.ok().build();
    }

    @POST
    public Response createNewShare(CreateCompanyShareRequest newShareRequest) {
        CompanyShare newShare = new CompanyShare(newShareRequest);
        boolean shareWasCreated = companySharesRepository.insertShare(newShare);

        if (!shareWasCreated) {
            return HttpResponseHelper.CreateConflictResponse(Errors.COMPANY_SYMBOL_EXISTS);
        }

        URI createdShareResourceLocation = UriBuilder.fromPath(Main.BaseUri + SharesController.Path).build();
        return Response.created(createdShareResourceLocation).build();
    }


}
