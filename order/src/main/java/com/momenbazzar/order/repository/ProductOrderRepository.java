package com.momenbazzar.order.repository;

import com.momenbazzar.order.model.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {

    @Query(value = "SELECT po.* FROM _order o JOIN product_Order po WHERE o.id = :orderId", nativeQuery = true)
    List<ProductOrder> findAllProductOrdersByOrderId(Long orderId);
}
