package com.jobboard.controllers;

import com.jobboard.dao.UserDao;
import com.jobboard.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userDao.getUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userDao.getUserById(id);
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        userDao.createUser(user);
    }
}
