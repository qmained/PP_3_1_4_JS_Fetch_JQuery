package ru.kata.spring.boot_security.demo.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.Collections;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    UserRepository userRepository;

//    @Autowired
//    public void setBCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean add(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return false;
        }
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
        userRepository.flush();
    }

    @Override
    public void removeById(long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        }
    }

    @Override
    public User getUser(long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public List<User> getListUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails getUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User %s not found", username));
        }
        return user;
    }

    private User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }



}
