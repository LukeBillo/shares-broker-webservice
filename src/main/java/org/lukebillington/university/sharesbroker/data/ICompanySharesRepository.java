package org.lukebillington.university.sharesbroker.data;

import org.bson.conversions.Bson;
import org.lukebillington.university.sharesbroker.data.models.CompanyShare;

import java.util.List;

public interface ICompanySharesRepository {
    List<CompanyShare> getShares();
    List<CompanyShare> getShares(Bson query);
    CompanyShare getShare(Bson query);
    void updateShare(CompanyShare buyShare);
    boolean insertShare(CompanyShare newShare);
}
