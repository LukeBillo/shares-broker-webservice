package org.lukebillington.university.sharesbroker.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.lukebillington.university.sharesbroker.data.models.User;
import org.lukebillington.university.sharesbroker.data.mongo.MongoConnectionManager;


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
        Document foundUser = getUsersCollection().find(Filters.eq("Username", username)).first();

        if (foundUser == null) {
            return null;
        }

        return new User(foundUser);
    }

    @Override
    public void updateUser(User user) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String userJson = mapper.writeValueAsString(user);

        Document userDocument = Document.parse(userJson);

        getUsersCollection().replaceOne(
                Filters.eq("Username", user.getUsername()),
                userDocument);
    }
}
