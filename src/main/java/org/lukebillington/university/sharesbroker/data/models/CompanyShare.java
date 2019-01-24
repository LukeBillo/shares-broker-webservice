package org.lukebillington.university.sharesbroker.data.models;

import org.bson.Document;
import org.lukebillington.university.sharesbroker.data.models.requests.CreateCompanyShareRequest;
import org.lukebillington.university.sharesbroker.services.IEXTrading.models.Quote;

public class CompanyShare {
    private String _companyName;
    private String _companySymbol;
    private long _numberOfShares;
    private SharePrice _sharePrice;

    public CompanyShare(Document companyShare) {
        _companyName = companyShare.getString("companyName");
        _companySymbol = companyShare.getString("companySymbol");
        _numberOfShares = companyShare.getInteger("numberOfShares");

        Document sharePrice = (Document) companyShare.get("sharePrice");
        _sharePrice = new SharePrice(sharePrice);
    }

    public CompanyShare(CreateCompanyShareRequest newCompanyShareRequest) {
        _companyName = newCompanyShareRequest.getCompanyName();
        _companySymbol = newCompanyShareRequest.getCompanySymbol();
        _numberOfShares = newCompanyShareRequest.getNumberOfAvailableShares();
        _sharePrice = new SharePrice(newCompanyShareRequest.getCurrency(), newCompanyShareRequest.getPrice());
    }

    public CompanyShare(Quote quote) {
        _companyName = quote.getCompanyName();
        _companySymbol = quote.getSymbol();
        _numberOfShares = quote.getLatestVolume();
        _sharePrice = new SharePrice("USD", quote.getLatestPrice());
    }

    public String getCompanyName() {
        return _companyName;
    }

    public void setCompanyName(String _companyName) {
        this._companyName = _companyName;
    }

    public String getCompanySymbol() {
        return _companySymbol;
    }

    public void setCompanySymbol(String _companySymbol) {
        this._companySymbol = _companySymbol;
    }

    public long getNumberOfShares() {
        return _numberOfShares;
    }

    public void setNumberOfShares(long _numberOfShares) {
        this._numberOfShares = _numberOfShares;
    }

    public SharePrice getSharePrice() {
        return _sharePrice;
    }

    public void setSharePrice(SharePrice sharePrice) { this._sharePrice = sharePrice; }
}
