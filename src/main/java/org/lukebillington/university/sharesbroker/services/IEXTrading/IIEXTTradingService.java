package org.lukebillington.university.sharesbroker.services.IEXTrading;

import org.lukebillington.university.sharesbroker.services.IEXTrading.models.Quote;
import java.util.List;

public interface IIEXTTradingService {
    Quote getQuote(String companySymbol);
    List<Quote> getMostActive();
    double getPrice(String companySymbol);
}

