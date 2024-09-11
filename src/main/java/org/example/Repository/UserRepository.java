package org.example.Repository;

import org.example.Model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class UserRepository {

    private List<User> users = new ArrayList<>(Arrays.asList(
            new User("admin", "admin", new String[]{"USER", "ADMIN"}),
            new User("user", "user", new String[]{"USER"})
    ));

    public User findByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public User addUser(User user) {
        users.add(user);
        return user;
    }

    public void deleteByUsername(String username) {
        users.removeIf(user -> user.getUsername().equals(username));
    }
}





