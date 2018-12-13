package org.lukebillington.university.sharesbroker.data.models;

import org.bson.Document;

import java.time.Instant;

public class SharePrice {
    private String _currency;
    private double _value;
    private Instant _lastUpdated;

    public SharePrice(Document sharePrice) {
        _currency = sharePrice.getString("currency");
        _value = sharePrice.getDouble("value");

        // todo: try using instant, date gets serialized to a ulong
        Document lastUpdated = (Document) sharePrice.get("lastUpdated");
        int epochSeconds = lastUpdated.getInteger("epochSecond");
        int nanos = lastUpdated.getInteger("nano");

        _lastUpdated = Instant.ofEpochSecond(epochSeconds, nanos);
    }

    public SharePrice(String currency, double price) {
        _currency = currency;
        _value = price;
        _lastUpdated = Instant.now();
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

    public Instant getLastUpdated() {
        return _lastUpdated;
    }

    public void setLastUpdated(Instant _lastUpdated) {
        this._lastUpdated = _lastUpdated;
    }
}
