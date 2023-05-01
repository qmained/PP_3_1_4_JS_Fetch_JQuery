package ru.kata.spring.boot_security.demo.utils;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Component
@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class)
public class Init {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    private void postConstruct() {
        if (!userService.containsUser("username")) {
            User user = new User("username", "password", "firstName", "lastName", 20, Collections.singleton(userService.findByName("ROLE_USER")));
            userService.add(user);
        }
        if (!userService.containsUser("admin")) {
            User admin = new User("admin", "root", "administrator", "programmer", 19
                    , new HashSet<>(Arrays.asList(userService.findByName("ROLE_USER"), userService.findByName("ROLE_ADMIN"))));
            userService.add(admin);
        }
    }

}
