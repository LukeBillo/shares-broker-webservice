package org.lukebillington.university.sharesbroker.data.models;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String _username;
    private List<CompanyShare> _ownedShares;
    private boolean _isAdmin;

    public User(Document user) {
        _username = user.getString("Username");
        _isAdmin = user.getBoolean("IsAdmin");

        ArrayList<Document> ownedShares = (ArrayList<Document>) user.get("OwnedShares");
        _ownedShares = new ArrayList<>();
        for (Document share : ownedShares) {
            _ownedShares.add(new CompanyShare(share));
        }
    }

    public String getUsername() {
        return _username;
    }

    public void setUsername(String _username) {
        this._username = _username;
    }

    public List<CompanyShare> getOwnedShares() {
        return _ownedShares;
    }

    public void setOwnedShares(List<CompanyShare> _ownedShares) {
        this._ownedShares = _ownedShares;
    }

    public boolean isAdmin() {
        return _isAdmin;
    }

    public void setIsAdmin(boolean _isAdmin) {
        this._isAdmin = _isAdmin;
    }
}
