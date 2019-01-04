package org.lukebillington.university.sharesbroker.data;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.lukebillington.university.sharesbroker.data.models.CompanyShare;
import org.lukebillington.university.sharesbroker.data.mongo.MongoConnectionManager;
import org.lukebillington.university.sharesbroker.utils.ObjectMapperHelper;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Sorts.descending;

public class CompanySharesRepository implements ICompanySharesRepository {
    private MongoClient _mongoClient;

    public CompanySharesRepository() {
        _mongoClient = MongoConnectionManager.Instance().getClient();
    }

    /**
     * Internal helper for getting shares database and collection.
     * @return Shares collection in MongoDb, located in the Shares database.
     */
    private MongoCollection<Document> getSharesCollection() {
        return _mongoClient.getDatabase("Shares")
                           .getCollection("Shares");
    }

    private ArrayList<CompanyShare> toCompanySharesArrayList(FindIterable<Document> documentsIterable) {
        ArrayList<CompanyShare> companyShares = new ArrayList<>();
        for (Document document : documentsIterable) {
            companyShares.add(new CompanyShare(document));
        }

        return companyShares;
    }

    /**
     * Takes no parameters.
     * Default method for retrieving shares.
     * Since this has no query, this will return the top 10 shares.
     * @return List containing top 10 company shares with the most shares available.
     */
    @Override
    public List<CompanyShare> getShares() {
        FindIterable<Document> top10Shares = getSharesCollection().find()
                .sort(descending("numberOfShares"))
                .limit(10);

        return toCompanySharesArrayList(top10Shares);
    }

    /**
     * Method for getting shares which includes a query.
     * @param companyShareCriteria - A query for MongoDb which filters the returned shares.
     * @return Returns a list of the CompanyShares which match the query.
     */
    @Override
    public List<CompanyShare> getShares(Bson companyShareCriteria) {
        FindIterable<Document> sharesMatchingQuery = getSharesCollection().find(companyShareCriteria);
        return toCompanySharesArrayList(sharesMatchingQuery);
    }

    /**
     * Method for getting one share that matches a query.
     * @param query - a query for MongoDb used as a filter.
     * @return Returns the first CompanyShare that matches the query.
     */
    @Override
    public CompanyShare getShare(Bson query) {
        Document firstMatchingCompanyShare = getSharesCollection().find(query).first();

        if (firstMatchingCompanyShare == null) {
            return null;
        }

        return new CompanyShare(firstMatchingCompanyShare);
    }

    @Override
    public void updateShare(String companySymbol, CompanyShare companyShare) {
        Document updatedShare = ObjectMapperHelper.MapToDocument(companyShare);

        getSharesCollection().replaceOne(
                Filters.eq("companySymbol", companySymbol),
                updatedShare
        );
    }

    /**
     * A method for inserting a new share. If a share with the same company symbol
     * already exists, the insertion will not be executed.
     * @param companyShare The new share being inserted.
     * @return A boolean representing whether the insert was successful.
     */
    @Override
    public boolean insertShare(CompanyShare companyShare) {
        CompanyShare existingShare = getShare(
                Filters.eq("companySymbol", companyShare.getCompanySymbol())
        );

        if (existingShare != null) {
            return false;
        }

        Document newShare = ObjectMapperHelper.MapToDocument(companyShare);
        getSharesCollection().insertOne(newShare);

        return true;
    }

}
