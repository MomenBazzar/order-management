package com.momenbazzar.order.service;

import com.momenbazzar.order.exception.NotFoundException;
import com.momenbazzar.order.model.Order;
import com.momenbazzar.order.model.Product;
import com.momenbazzar.order.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found with ID: " + productId));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public void updateProduct(Product product) {
        if (!productRepository.existsById(product.getId())) {
            throw new NotFoundException("Product not found with ID: " + product.getId());
        }
        productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new NotFoundException("Product not found with ID: " + productId);
        }
        productRepository.deleteById(productId);
    }
}
