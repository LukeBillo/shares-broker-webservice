package org.lukebillington.university.sharesbroker.data.models.builders;

import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;

import java.time.Instant;
import java.util.Date;
import java.util.regex.Pattern;

public class CompanyShareQueryBuilder {
    private Bson _query = null;

    public CompanyShareQueryBuilder() {}

    private Bson AppendToQuery(Bson query) {
        return _query == null ? query : Filters.and(_query, query);
    }

    public CompanyShareQueryBuilder WithCompanyName(String companyName) {
        if (companyName == null || companyName.isEmpty())
            return this;

        _query = AppendToQuery(
                Filters.eq("companyName", Pattern.compile(companyName))
        );

        return this;
    }

    public CompanyShareQueryBuilder WithCompanySymbol(String companySymbol) {
        if (companySymbol == null || companySymbol.isEmpty())
            return this;

        _query = AppendToQuery(
                Filters.eq("companySymbol", Pattern.compile(companySymbol))
        );

        return this;
    }

    public CompanyShareQueryBuilder WithAvailableShares(Integer lessThan, Integer moreThan) {
        if (lessThan == null && moreThan == null)
            return this;

        if (lessThan != null) {
            _query = AppendToQuery(
                    Filters.lte("numberOfShares", lessThan)
            );
        }

        if (moreThan != null) {
            _query = AppendToQuery(
                    Filters.gte("numberOfShares", moreThan)
            );
        }

        return this;
    }

    public CompanyShareQueryBuilder WithLastUpdated(Date before, Date after) {
        if (before == null && after == null)
            return this;

        if (before != null) {
            Instant beforeInstant = before.toInstant();

            _query = AppendToQuery(
                    Filters.lte("lastUpdated.epochSecond", beforeInstant.getEpochSecond())
            );
        }

        if (after != null) {
            Instant afterInstant = after.toInstant();

            _query = AppendToQuery(
                    Filters.gte("lastUpdated.epochSecond", afterInstant.getEpochSecond())
            );
        }

        return this;
    }

    public Bson build() {
        return _query;
    }
}
