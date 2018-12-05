package org.lukebillington.university.sharesbroker.data;

import org.lukebillington.university.sharesbroker.data.models.CompanyShare;

import java.util.List;

public interface ICompanySharesRepository {
    List<CompanyShare> getShares();
    CompanyShare getShares(String query);
}
