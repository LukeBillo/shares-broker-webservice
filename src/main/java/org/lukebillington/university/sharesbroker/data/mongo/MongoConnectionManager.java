package org.lukebillington.university.sharesbroker.data.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;

@Service
public class MongoConnectionManager implements IMongoConnectionManager {
    private static final MongoClientURI AtlasConnectionString = new MongoClientURI("mongodb://DbAdmin:VP1duQrkCX3Tyd2D@127.0.0.1:27017");
    private static MongoClient _mongoClient;

    @Inject
    private MongoConnectionManager() {
        _mongoClient = new MongoClient(AtlasConnectionString);
    }

    @Override
    public MongoClient getClient() {
        return _mongoClient;
    }
}
