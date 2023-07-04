package com.momenbazzar.order.repository;

import com.momenbazzar.order.model.Order;
import com.momenbazzar.order.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT o.* FROM _order o JOIN Product_Order po ON o.id = po.order_id WHERE po.product_id = :productId", nativeQuery = true)
    List<Order> findOrdersByProductId(Long productId);

}
