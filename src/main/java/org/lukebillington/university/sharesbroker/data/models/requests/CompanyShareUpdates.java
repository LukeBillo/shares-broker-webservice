package org.lukebillington.university.sharesbroker.data.models.requests;

public class CompanyShareUpdates {
    /**
     * Non-primitive types have purposely been used to
     * allow for nulls here.
     * This way, in a PATCH request, nulls can be
     * checked and updates will not be applied
     * if the fields are null.
     */

    private String _companyName;
    private String _companySymbol;
    private Integer _numberOfShares;
    private String _currency;
    private Double _price;

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

    public Integer getNumberOfShares() {
        return _numberOfShares;
    }

    public void setNumberOfShares(Integer _numberOfShares) {
        this._numberOfShares = _numberOfShares;
    }

    public String getCurrency() {
        return _currency;
    }

    public void setCurrency(String _currency) {
        this._currency = _currency;
    }

    public Double getPrice() {
        return _price;
    }

    public void setPrice(Double _price) {
        this._price = _price;
    }
}
