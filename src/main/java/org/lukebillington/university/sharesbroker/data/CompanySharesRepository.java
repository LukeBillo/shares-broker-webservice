package org.lukebillington.university.sharesbroker.data;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.jvnet.hk2.annotations.Service;
import org.lukebillington.university.sharesbroker.data.models.CompanyShare;
import org.lukebillington.university.sharesbroker.data.models.SharePrice;
import org.lukebillington.university.sharesbroker.data.mongo.IMongoConnectionManager;
import org.lukebillington.university.sharesbroker.services.IEXTrading.IIEXTTradingService;
import org.lukebillington.university.sharesbroker.services.IEXTrading.models.Quote;
import org.lukebillington.university.sharesbroker.utils.ObjectMapperHelper;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.mongodb.client.model.Sorts.descending;

@Service
public class CompanySharesRepository implements ICompanySharesRepository {
    private MongoClient _mongoClient;
    private static final int ThirtyMinutes = 30 * 60 * 1000;

    @Inject
    public CompanySharesRepository(IMongoConnectionManager mongoConnectionManager, IIEXTTradingService exchangeTradingService) {
        _mongoClient = mongoConnectionManager.getClient();

        Timer timer = new Timer();
        timer.schedule( new TimerTask() {
            public void run() {
                List<Quote> mostActiveShares = exchangeTradingService.getMostActive();

                for (Quote quote: mostActiveShares) {
                    CompanyShare share = getShare(Filters.eq("companySymbol", quote.getSymbol()));

                    if (share != null) {
                        share.setNumberOfShares(quote.getLatestVolume());
                        share.setSharePrice(new SharePrice("USD", quote.getLatestPrice()));

                        updateShare(share.getCompanySymbol(), share);
                        continue;
                    }

                    CompanyShare newShare = new CompanyShare(quote);
                    insertShare(newShare);
                }
            }
        }, 0, ThirtyMinutes);
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
     * This is essentially the "default" method for getting shares
     * when no criteria is given.
     * @param limit - Max number of CompanyShares to return in List.
     * @return Returns a list of the top n CompanyShares in share price.
     */
    @Override
    public List<CompanyShare> getShares(int limit) {
        FindIterable<Document> sharesWithLimit = getSharesCollection().find()
                .sort(descending("sharePrice.value"))
                .limit(limit);

        return toCompanySharesArrayList(sharesWithLimit);
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
