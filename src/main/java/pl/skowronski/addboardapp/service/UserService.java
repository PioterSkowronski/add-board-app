package pl.skowronski.addboardapp.service;

import pl.skowronski.addboardapp.model.User;

public interface UserService {

    public void saveUser(User user);

    public boolean isUserAlreadyPresent(User user);
}
