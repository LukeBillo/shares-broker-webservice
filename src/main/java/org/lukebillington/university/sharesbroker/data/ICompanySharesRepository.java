package org.lukebillington.university.sharesbroker.data;

import org.bson.conversions.Bson;
import org.lukebillington.university.sharesbroker.data.models.CompanyShare;

import java.util.List;

public interface ICompanySharesRepository {
    List<CompanyShare> getShares(int limit);
    List<CompanyShare> getShares(Bson query);
    CompanyShare getShare(Bson query);
    void updateShare(String companySymbol, CompanyShare updatedShare);
    boolean insertShare(CompanyShare newShare);
}
