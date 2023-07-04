package com.momenbazzar.order.controller;

import com.momenbazzar.order.dto.OrderDTO;
import com.momenbazzar.order.model.Order;
import com.momenbazzar.order.service.CustomerService;
import com.momenbazzar.order.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/customers/{customerId}/orders")
public class OrderController {
    private final OrderService orderService;
    private final CustomerService customerService;
    private final ModelMapper modelMapper;

    public OrderController(OrderService orderService, CustomerService customerService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@PathVariable Long customerId) {
        Order createdOrder = orderService.createOrder(customerId);
        OrderDTO orderDTO = modelMapper.map(createdOrder, OrderDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDTO);
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getOrdersByCustomerId(@PathVariable Long customerId) {
        List<Order> orders = customerService.getAllOrdersByCustomerId(customerId);
        List<OrderDTO> orderDTOs = orders.stream()
                .map(order -> modelMapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderDTOs);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long orderId) {
        Order order = orderService.getOrderById(orderId);
        OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
        return ResponseEntity.ok(orderDTO);
    }

}
