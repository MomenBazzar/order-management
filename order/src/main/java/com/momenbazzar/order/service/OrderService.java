package com.momenbazzar.order.service;

import com.momenbazzar.order.exception.NotFoundException;
import com.momenbazzar.order.model.*;
import com.momenbazzar.order.repository.CustomerRepository;
import com.momenbazzar.order.repository.OrderRepository;
import com.momenbazzar.order.repository.ProductOrderRepository;
import com.momenbazzar.order.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductOrderRepository productOrderRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository,
                        ProductOrderRepository productOrderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productOrderRepository = productOrderRepository;
        this.productRepository = productRepository;
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found with ID: " + orderId));
    }

    public List<ProductOrder> getProductsByOrderId(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new NotFoundException("Order not found with ID: " + orderId);
        }
        return productOrderRepository.findAllProductOrdersByOrderId(orderId);
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

    public ProductOrder addProductToOrder(Long orderId,Long productId, int quantity) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found"));

        // Create a new ProductOrder
        ProductOrder productOrder = new ProductOrder();
        productOrder.setOrder(order);
        productOrder.setProduct(product);
        productOrder.setQuantity(quantity);
        productOrder.setPrice(product.getPrice() * quantity);
        productOrder.setVat(product.getVat() * quantity);

        // Save the ProductOrder
        return productOrderRepository.save(productOrder);
    }
}
