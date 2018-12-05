package org.lukebillington.university.sharesbroker;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.lukebillington.university.sharesbroker.controllers.SharesController;
import org.lukebillington.university.sharesbroker.data.CompanySharesRepository;
import org.lukebillington.university.sharesbroker.data.ICompanySharesRepository;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class AppConfig extends ResourceConfig {
    public AppConfig() {
        register(SharesController.class);

        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(CompanySharesRepository.class)
                        .to(ICompanySharesRepository.class);
            }
        });
    }
}