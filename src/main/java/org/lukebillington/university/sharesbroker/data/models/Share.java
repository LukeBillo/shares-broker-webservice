package org.lukebillington.university.sharesbroker.data.models;

import java.util.Date;

public class Share {
    private String _currency;
    private float _value;
    private Date _lastUpdated;

    public String getCurrency() {
        return _currency;
    }

    public void setCurrency(String _currency) {
        this._currency = _currency;
    }

    public float getValue() {
        return _value;
    }

    public void setValue(float _value) {
        this._value = _value;
    }

    public Date getLastUpdated() {
        return _lastUpdated;
    }

    public void setLastUpdated(Date _lastUpdated) {
        this._lastUpdated = _lastUpdated;
    }
}
