package org.lukebillington.university.sharesbroker.data;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.lukebillington.university.sharesbroker.data.models.CompanyShare;
import org.lukebillington.university.sharesbroker.data.mongo.MongoConnectionManager;


import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Sorts.descending;

public class CompanySharesRepository implements ICompanySharesRepository {
    private MongoClient _mongoClient;

    public CompanySharesRepository() {
        _mongoClient = MongoConnectionManager.Instance().getClient();
    }

    /**
     * Takes no parameters.
     * Default message for retrieving shares
     * with no data query, this will return the top 10 shares.
     * @return List containing top 10 company shares.
     */
    @Override
    public List<CompanyShare> getShares() {
        MongoCollection<Document> sharesDatabase = _mongoClient.getDatabase("Shares")
                                                            .getCollection("Shares");

        FindIterable<Document> top10Shares = sharesDatabase.find()
                .sort(descending("NumberOfShares"))
                .limit(10);

        ArrayList<CompanyShare> top10SharesList = new ArrayList<>();
        for (Document document : top10Shares) {
            top10SharesList.add(new CompanyShare(document));
        }

        return top10SharesList;
    }

    @Override
    public CompanyShare getShares(String query) {
        return null;
    }

}
