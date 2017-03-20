package org.proj.app.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.proj.app.domain.User;

import java.util.Set;


@Service
public interface UserService extends UserDetailsService{
    User createUser(String username, String password, String firstName, String lastName);
    void deleteUser(User u);
    User createUser(User i);
    void deleteAllUsers();
    Set<User> getUsers();
    boolean UserExists(User u);
    User findById(Long id);
    User findByUsername(String username);
}
