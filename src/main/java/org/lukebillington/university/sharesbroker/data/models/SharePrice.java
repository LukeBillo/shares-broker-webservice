package org.lukebillington.university.sharesbroker.data.models;

import org.bson.Document;

import java.util.Date;

public class SharePrice {
    private String _currency;
    private double _value;
    private Date _lastUpdated;

    public SharePrice(Document sharePrice) {
        _currency = sharePrice.getString("currency");
        _value = sharePrice.getDouble("value");

        // todo: try using instant, date gets serialized to a ulong
        _lastUpdated = sharePrice.getDate("lastUpdated");
    }

    public String getCurrency() {
        return _currency;
    }

    public void setCurrency(String _currency) {
        this._currency = _currency;
    }

    public double getValue() {
        return _value;
    }

    public void setValue(double _value) {
        this._value = _value;
    }

    public Date getLastUpdated() {
        return _lastUpdated;
    }

    public void setLastUpdated(Date _lastUpdated) {
        this._lastUpdated = _lastUpdated;
    }
}
