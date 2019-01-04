package org.lukebillington.university.sharesbroker.data.models.requests;

public class BuyShareRequest {
    private String _buyerUsername;
    private String _companySymbol;
    private int _numberOfSharesToBuy;


    public String getCompanySymbol() {
        return _companySymbol;
    }

    public void setCompanySymbol(String _companySymbol) {
        this._companySymbol = _companySymbol;
    }

    public int getNumberOfSharesToBuy() {
        return _numberOfSharesToBuy;
    }

    public void setNumberOfSharesToBuy(int _numberOfSharesToBuy) {
        this._numberOfSharesToBuy = _numberOfSharesToBuy;
    }

    public String getBuyerUsername() {
        return _buyerUsername;
    }

    public void setBuyerUsername(String _buyerUsername) {
        this._buyerUsername = _buyerUsername;
    }
}
