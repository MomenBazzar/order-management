package com.momenbazzar.order.controller;

import com.momenbazzar.order.dto.ProductOrderDTO;
import com.momenbazzar.order.model.ProductOrder;
import com.momenbazzar.order.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers/{customerId}/orders/{orderId}/productItems")
public class ProductOrderController {
    private final ProductService productService;
    private final ModelMapper modelMapper;

    public ProductOrderController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<ProductOrderDTO> getAllProductItems(@PathVariable Long customerId, @PathVariable Long orderId) {
        List<ProductOrder> productOrders = productOrderService.getAllProductOrders(customerId, orderId);

        // Map the list of ProductOrder entities to a list of ProductOrderDTOs
        Type listType = new TypeToken<List<ProductOrderDTO>>() {}.getType();
        List<ProductOrderDTO> productOrderDTOs = modelMapper.map(productOrders, listType);

        return productOrderDTOs;
    }

    // Other methods for creating, updating, deleting product items, etc.
    // ...
}
