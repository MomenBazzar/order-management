package com.momenbazzar.order.repository;

import com.momenbazzar.order.model.Customer;
import com.momenbazzar.order.model.Order;
import com.momenbazzar.order.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT p.* FROM Product p JOIN Product_Order po ON p.id = po.product_id WHERE po.order_id = :orderId", nativeQuery = true)
    List<Product> findProductsByOrderId(Long orderId);

}
