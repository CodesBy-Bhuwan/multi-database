package com.multidb.sql_sql.postgres;

import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/products")
    public class ProductController {

        private final ProductRepo repository;

        public ProductController(ProductRepo repository) {
            this.repository = repository;
        }

        @PostMapping
        public ProductEntity create(@RequestBody ProductEntity product) {
            return repository.save(product);
        }

        @GetMapping
        public List<ProductEntity> all() {
            return repository.findAll();
        }
    }

