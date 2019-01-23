package org.lukebillington.university.sharesbroker.data.models;

import org.bson.Document;

public class OwnedShare {
    private String _companyName;
    private String _companySymbol;
    private int _numberOfShares;

    public OwnedShare(CompanyShare boughtCompanyShare, int numberOfBoughtShares) {
        _companyName = boughtCompanyShare.getCompanyName();
        _companySymbol = boughtCompanyShare.getCompanySymbol();
        _numberOfShares = numberOfBoughtShares;
    }

    public OwnedShare(Document share) {
        _companyName = share.getString("companyName");
        _companySymbol = share.getString("companySymbol");
        _numberOfShares = share.getInteger("numberOfShares");
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

    public int getNumberOfShares() {
        return _numberOfShares;
    }

    public void setNumberOfShares(int _numberOfShares) {
        this._numberOfShares = _numberOfShares;
    }
}
