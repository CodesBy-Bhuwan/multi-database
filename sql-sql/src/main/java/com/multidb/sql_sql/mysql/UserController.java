package com.multidb.sql_sql.mysql;

import com.example.multidb.mysql.entity.User;
import com.example.multidb.mysql.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/users")
    public class UserController {

        private final UserRepository repository;

        public UserController(UserRepository repository) {
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
