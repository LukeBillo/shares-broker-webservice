package org.lukebillington.university.sharesbroker.data.models.requests;

public class UpdateCompanyShareRequest {
    private String _companySymbol;
    private CompanyShareUpdates _updates;


    public String getCompanySymbol() {
        return _companySymbol;
    }

    public void setCompanySymbol(String _companySymbol) {
        this._companySymbol = _companySymbol;
    }

    public CompanyShareUpdates getUpdates() {
        return _updates;
    }

    public void setUpdates(CompanyShareUpdates _updates) {
        this._updates = _updates;
    }
}
