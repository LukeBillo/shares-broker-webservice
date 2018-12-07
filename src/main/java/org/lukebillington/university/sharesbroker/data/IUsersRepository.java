package org.lukebillington.university.sharesbroker.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.lukebillington.university.sharesbroker.data.models.User;

public interface IUsersRepository {
    User getUser(String username);
    void updateUser(User user) throws JsonProcessingException;
}
