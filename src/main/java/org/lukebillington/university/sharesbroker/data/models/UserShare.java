package org.lukebillington.university.sharesbroker.data.models;

import org.bson.Document;

public class UserShare {
    private String _companyName;
    private String _companySymbol;

    public UserShare(CompanyShare boughtCompanyShare) {
        _companyName = boughtCompanyShare.getCompanyName();
        _companySymbol = boughtCompanyShare.getCompanySymbol();
    }

    public UserShare (Document share) {
        _companyName = share.getString("companyName");
        _companySymbol = share.getString("companySymbol");
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
}
