package org.lukebillington.university.sharesbroker.data;

import org.bson.types.ObjectId;
import org.lukebillington.university.sharesbroker.data.models.User;

public interface IUsersRepository {
    User getUser(String username);
    void replaceUser(User user);
    void updateUserShares(User user);
    void setUserPasswordHash(String username, String newHashedPassword);
    String getUserPasswordHash(String username);
}
