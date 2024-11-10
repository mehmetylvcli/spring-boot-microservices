package com.mehmetyalvacli.microservice.service;

import com.mehmetyalvacli.microservice.models.Product;
import com.mehmetyalvacli.microservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    public Product createProduct(Product product, Long userId) {
        product.setUser(userService.getUserById(userId));
        return productRepository.save(product);
    }

    public Product updateProduct(Long productId, Product updatedProduct, Long userId) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        if (!existingProduct.getUser().getId().equals(userId)) {
            throw new RuntimeException("Not allowed to modify this product");
        }
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setTaxRate(updatedProduct.getTaxRate());
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long productId, Long userId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        if (!product.getUser().getId().equals(userId)) {
            throw new RuntimeException("Not allowed to delete this product");
        }
        productRepository.delete(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByUserId(Long userId) {
        return productRepository.findProductsByUser_id(userId);
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
    }


}
