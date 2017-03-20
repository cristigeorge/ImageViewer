package org.proj.app.service;

import org.proj.app.domain.User;
import org.proj.app.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Override
    public User createUser(String username, String password, String firstName, String lastName) {
        User newUser = new User();
        newUser.setName(lastName);
        newUser.setPassword(passwordEncoder.encode(password));

        newUser.setUsername(username);
        userRepository.save(newUser);
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }

    @Override
    public void deleteUser(User u) {
            userRepository.delete(u);
            log.debug("Deleted User: {}", u);
    }

    @Override
    public User createUser(User i) {
        i.setPassword(passwordEncoder.encode(i.getPassword()));
        System.out.println(i.getPassword() + " " + passwordEncoder.encode(i.getPassword()));
        return userRepository.save(i);
    }

    public User findById(Long id) {
        return userRepository.findById(id);
    }

    public User findByUsername(String username) {
        return userRepository.findOneByUsername(username);
    }


    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    @Override
    public Set<User> getUsers() {
        return new HashSet<>(userRepository.findAll());
    }

    @Override
    public boolean UserExists(User u) {
        User user = userRepository.findOneByUsername(u.getUsername());
        if (user != null) {
            return true;
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOneByUsername(username);
        if (user==null) {
            throw new  UsernameNotFoundException("goteem");
        }
        return user;
    }
}
