package org.lukebillington.university.sharesbroker.data.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoConnectionManager {
    private static final MongoClientURI AtlasConnectionString = new MongoClientURI("mongodb://SharesAdmin:VP1duQrkCX3Tyd2D@shares-mongodb-shard-00-00-b1dc9.azure.mongodb.net:27017,shares-mongodb-shard-00-01-b1dc9.azure.mongodb.net:27017,shares-mongodb-shard-00-02-b1dc9.azure.mongodb.net:27017/test?ssl=true&replicaSet=shares-mongodb-shard-0&authSource=admin&retryWrites=true");
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
