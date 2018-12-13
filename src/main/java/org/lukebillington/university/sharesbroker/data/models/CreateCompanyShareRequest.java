package org.lukebillington.university.sharesbroker.data.models;

public class CreateCompanyShareRequest {
    private String _companyName;
    private String _companySymbol;
    private int _numberOfAvailableShares;
    private String _currency;
    private double _price;

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

    public int getNumberOfAvailableShares() {
        return _numberOfAvailableShares;
    }

    public void setNumberOfAvailableShares(int _numberOfAvailableShares) {
        this._numberOfAvailableShares = _numberOfAvailableShares;
    }

    public String getCurrency() {
        return _currency;
    }

    public void setCurrency(String _currency) {
        this._currency = _currency;
    }

    public double getPrice() {
        return _price;
    }

    public void setPrice(double _price) {
        this._price = _price;
    }
}
