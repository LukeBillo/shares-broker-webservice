package org.lukebillington.university.sharesbroker.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.lukebillington.university.sharesbroker.data.models.User;
import org.lukebillington.university.sharesbroker.data.mongo.MongoConnectionManager;
import org.lukebillington.university.sharesbroker.utils.ObjectMapperHelpers;


public class UsersRepository implements IUsersRepository {
    private MongoClient _mongoClient;

    private MongoCollection<Document> getUsersCollection() {
        return _mongoClient.getDatabase("Shares")
                .getCollection("Users");
    }

    public UsersRepository() {
        _mongoClient = MongoConnectionManager.Instance().getClient();
    }

    @Override
    public User getUser(String username) {
        Document foundUser = getUsersCollection().find(Filters.eq("username", username)).first();

        if (foundUser == null) {
            return null;
        }

        return new User(foundUser);
    }

    @Override
    public void updateUser(User user) {
        Document userDocument = ObjectMapperHelpers.MapToDocument(user);

        getUsersCollection().replaceOne(
                Filters.eq("Username", user.getUsername()),
                userDocument);
    }
}
