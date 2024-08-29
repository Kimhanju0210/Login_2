package org.example.login.controller;


import org.example.login.entity.User;
import org.example.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.Optional;

//@RestController
//@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/join")
    public String join(@RequestBody User user) {
        userService.saveUser(user);
        return "Join success";
    }

    @PostMapping
    public String login(@RequestBody User user) {
        Optional<User> dbUser = userService.findByUsername(user.getUsername());
        if (dbUser.isPresent() && new BCryptPasswordEncoder().matches(user.getPassword(), dbUser.get().getPassword())) {
            return "Login success";
        }
    }



}
