package org.lukebillington.university.sharesbroker.data.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoConnectionManager {
    private static final MongoClientURI AtlasConnectionString = new MongoClientURI("mongodb://DbAdmin:VP1duQrkCX3Tyd2D@127.0.0.1:27017");
    private static MongoClient _mongoClient;

    private MongoConnectionManager() {
        _mongoClient = new MongoClient(AtlasConnectionString);
    }

    private static class Singleton {
        private static final MongoConnectionManager Instance = new MongoConnectionManager();
    }

    public static MongoConnectionManager Instance() {
        return Singleton.Instance;
    }

    public MongoClient getClient() {
        return _mongoClient;
    }
}
