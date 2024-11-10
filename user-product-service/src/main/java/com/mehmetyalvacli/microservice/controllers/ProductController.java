package com.mehmetyalvacli.microservice.controllers;

import com.mehmetyalvacli.microservice.models.Product;
import com.mehmetyalvacli.microservice.service.ProductService;
import com.mehmetyalvacli.microservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Product> createProduct(@RequestBody Product product, @RequestHeader("Authorization") String token) {
        Long userId = userService.getUserIdFromToken(token);
        Product createdProduct = productService.createProduct(product, userId);
        return ResponseEntity.ok(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product, @RequestHeader("Authorization") String token) {
        Long userId = userService.getUserIdFromToken(token);
        Product updatedProduct = productService.updateProduct(id, product, userId);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        Long userId = userService.getUserIdFromToken(token);
        productService.deleteProduct(id, userId);
        return ResponseEntity.ok("Product deleted successfully");
    }




}