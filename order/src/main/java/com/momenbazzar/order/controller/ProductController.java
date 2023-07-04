package com.momenbazzar.order.controller;

import com.momenbazzar.order.dto.ProductDTO;
import com.momenbazzar.order.exception.NotFoundException;
import com.momenbazzar.order.model.Product;
import com.momenbazzar.order.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDTO> productDTOs = products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(productDTOs);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long productId) {
        try {
            Product product = productService.getProductById(productId);
            ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
            return ResponseEntity.ok(productDTO);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        productService.saveProduct(product);
        ProductDTO createdProductDTO = modelMapper.map(product, ProductDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProductDTO);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long productId, @RequestBody ProductDTO productDTO) {
        try {
            Product existingProduct = productService.getProductById(productId);
            modelMapper.map(productDTO, existingProduct);
            productService.updateProduct(existingProduct);
            ProductDTO updatedProductDTO = modelMapper.map(existingProduct, ProductDTO.class);
            return ResponseEntity.ok(updatedProductDTO);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        try {
            productService.deleteProduct(productId);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
