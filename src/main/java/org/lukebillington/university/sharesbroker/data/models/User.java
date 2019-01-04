package org.lukebillington.university.sharesbroker.data.models;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String _username;
    private List<UserShare> _ownedShares;
    private boolean _isAdmin;

    public User(Document user) {
        _username = user.getString("username");
        _isAdmin = user.getBoolean("admin");

        ArrayList<Document> ownedShares = (ArrayList<Document>) user.get("ownedShares");
        _ownedShares = new ArrayList<>();
        for (Document share : ownedShares) {
            _ownedShares.add(new UserShare(share));
        }
    }

    public String getUsername() {
        return _username;
    }

    public void setUsername(String _username) {
        this._username = _username;
    }

    public List<UserShare> getOwnedShares() {
        return _ownedShares;
    }

    public void setOwnedShares(List<UserShare> _ownedShares) {
        this._ownedShares = _ownedShares;
    }

    public boolean isAdmin() {
        return _isAdmin;
    }

    public void setIsAdmin(boolean _isAdmin) {
        this._isAdmin = _isAdmin;
    }

    public UserShare getUserShareIfExists(String companySymbol) {
        for (UserShare share : _ownedShares) {
            if (share.getCompanySymbol().equals(companySymbol)) {
                return share;
            }
        }

        return null;
    }
}
