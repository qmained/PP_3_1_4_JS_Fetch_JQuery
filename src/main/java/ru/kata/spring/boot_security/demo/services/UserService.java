package ru.kata.spring.boot_security.demo.services;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    boolean add(User user);

    Role addRole(Role role);

    void update(List<User> users);

    void removeById(long id);

    User getUser(long id);

    List<User> getListUsers();

    boolean containsUser(String username);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;


}
