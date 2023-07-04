package com.momenbazzar.order.service;

import com.momenbazzar.order.dto.ProductOrderAddDTO;
import com.momenbazzar.order.exception.NotFoundException;
import com.momenbazzar.order.model.*;
import com.momenbazzar.order.repository.CustomerRepository;
import com.momenbazzar.order.repository.OrderRepository;
import com.momenbazzar.order.repository.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductOrderRepository productOrderRepository;
    private final ProductService productService;

    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository,
                        ProductOrderRepository productOrderRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productOrderRepository = productOrderRepository;
        this.productService = productService;
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found with ID: " + orderId));
    }
    
    public void deleteOrderById(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new NotFoundException("Order not found with ID: " + orderId);
        }
        orderRepository.deleteById(orderId);
    }

    public List<Product> getProductsByOrderId(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new NotFoundException("Order not found with ID: " + orderId);
        }
        return orderRepository.findProductsByOrderId(orderId);
    }

    public Order createOrder(Long customerId) {
        // Create a new order
        Order order = new Order();
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("Customer not found with ID: " + customerId));
        order.setCustomer(customer);
        order.setOrderedAt(LocalDateTime.now());
        orderRepository.save(order);

        return order;
    }
}
