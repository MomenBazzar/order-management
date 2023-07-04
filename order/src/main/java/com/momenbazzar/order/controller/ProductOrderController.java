package com.momenbazzar.order.controller;

import com.momenbazzar.order.dto.ProductOrderAddDTO;
import com.momenbazzar.order.dto.ProductOrderDTO;
import com.momenbazzar.order.model.ProductOrder;
import com.momenbazzar.order.service.OrderService;
import com.momenbazzar.order.service.ProductService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers/{customerId}/orders/{orderId}/productItems")
public class ProductOrderController {
    private final OrderService orderService;
    private final ModelMapper modelMapper;

    public ProductOrderController(OrderService orderService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<ProductOrderDTO> getAllProductItems(@PathVariable Long orderId) {
        List<ProductOrder> productOrders = orderService.getProductsByOrderId(orderId);

        // Map the list of ProductOrder entities to a list of ProductOrderDTOs
        Type listType = new TypeToken<List<ProductOrderDTO>>() {}.getType();
        List<ProductOrderDTO> productOrderDTOs = modelMapper.map(productOrders, listType);

        return productOrderDTOs;
    }

    @PostMapping
    public ResponseEntity<ProductOrderDTO> addProductToOrder(
            @PathVariable Long customerId,
            @PathVariable Long orderId,
            @RequestBody ProductOrderAddDTO productOrderDTO) {
        int quantity = productOrderDTO.getQuantity();
        Long productId = productOrderDTO.getProduct().getId();
        ProductOrder addedProductOrder = orderService.addProductToOrder(orderId, productId, quantity);
        ProductOrderDTO addedProductOrderDTO = modelMapper.map(addedProductOrder, ProductOrderDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedProductOrderDTO);
    }

}
