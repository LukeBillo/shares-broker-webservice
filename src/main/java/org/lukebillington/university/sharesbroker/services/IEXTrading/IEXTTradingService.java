package org.lukebillington.university.sharesbroker.services.IEXTrading;

import org.apache.http.impl.client.HttpClientBuilder;
import org.lukebillington.university.sharesbroker.services.IEXTrading.models.Quote;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import java.util.List;

public class IEXTTradingService implements IIEXTTradingService {
    private Client client;

    public IEXTTradingService() {
        client = ClientBuilder.newClient();
    }

    @Override
    public Quote getQuote(String companySymbol) {
        WebTarget target = client.target("https://api.iextrading.com/1.0/stock/" + companySymbol + "/quote");
        return target.request().get(Quote.class);
    }

    @Override
    public List<Quote> getMostActive() {
        WebTarget target = client.target("https://api.iextrading.com/1.0/stock/market/list/mostactive");
        return target.request().get(new GenericType<List<Quote>>() {});
    }

    @Override
    public double getPrice(String companySymbol) {
        WebTarget target = client.target("https://api.iextrading.com/1.0/stock/" + companySymbol + "/price");
        return target.request().get(double.class);
    }
}
