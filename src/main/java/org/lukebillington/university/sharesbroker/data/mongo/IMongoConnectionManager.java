package org.lukebillington.university.sharesbroker.data.mongo;

import com.mongodb.MongoClient;

public interface IMongoConnectionManager {
    MongoClient getClient();
}
