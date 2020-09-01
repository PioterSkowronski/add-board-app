package pl.skowronski.addboardapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.skowronski.addboardapp.model.Role;
import pl.skowronski.addboardapp.repository.RoleRepository;
import pl.skowronski.addboardapp.model.User;
import pl.skowronski.addboardapp.repository.UserRepository;
import pl.skowronski.addboardapp.service.UserService;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserServiceJpa implements UserService {


    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setStatus("VERIFIED");
        Role userRole = roleRepository.findByRole("SITE_USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public boolean isUserAlreadyPresent(User user) {
        return false;
    }
}
