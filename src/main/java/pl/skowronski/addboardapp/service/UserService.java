package pl.skowronski.addboardapp.service;

import pl.skowronski.addboardapp.model.User;

public interface UserService {

    void saveUser(User user);

    void updateUser(User user);
}
