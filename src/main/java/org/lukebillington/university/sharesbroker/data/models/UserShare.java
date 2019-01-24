package org.lukebillington.university.sharesbroker.data.models;

public class UserShare extends OwnedShare {
    private SharePrice _sharePrice;

    public UserShare(CompanyShare boughtCompanyShare, int numberOfBoughtShares) {
        super(boughtCompanyShare, numberOfBoughtShares);

        _sharePrice = boughtCompanyShare.getSharePrice();
    }

    public SharePrice getSharePrice() {
        return _sharePrice;
    }

    public void setSharePrice(SharePrice _sharePrice) {
        this._sharePrice = _sharePrice;
    }
}
