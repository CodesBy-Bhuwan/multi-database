package com.multidb.sql_sql.mysql;

import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/users")
    public class UserController {

        private final UserRepo repository;

        public UserController(UserRepo repository) {
            this.repository = repository;
        }

        @PostMapping
        public User create(@RequestBody User user) {
            return repository.save(user);
        }

        @GetMapping
        public List<User> all() {
            return repository.findAll();
        }
    }
