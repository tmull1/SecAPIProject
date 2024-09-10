package org.example;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private User[] users = {
            new User("admin", "admin", new String[] {"USER", "ADMIN"}),
            new User("user", "user", new String[] {"USER"})
    };

    public User findByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;  // If found, return the user
            }
        }
        return null;  // If no user is found, return null
    }

    public User addUser(User user) {
        User[] newUsers = new User[users.length + 1];
        System.arraycopy(users, 0, newUsers, 0, users.length);
        newUsers[users.length] = user;
        users = newUsers;
        return user;
    }
}




