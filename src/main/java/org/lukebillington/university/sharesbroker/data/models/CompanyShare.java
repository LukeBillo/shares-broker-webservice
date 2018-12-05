package org.lukebillington.university.sharesbroker.data.models;

import org.bson.Document;

import java.util.ArrayList;

public class CompanyShare {
    private String _companyName;
    private String _companySymbol;
    private int _numberOfShares;
    private ArrayList<Share> _shares;

    public CompanyShare(Document companyShare) {

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

    public ArrayList<Share> getShares() {
        return _shares;
    }

    public void setShares(ArrayList<Share> _shares) {
        this._shares = _shares;
    }
}
