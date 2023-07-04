package com.momenbazzar.order.repository;

import com.momenbazzar.order.model.Customer;
import com.momenbazzar.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT * FROM _Order WHERE customer_id = :customerId", nativeQuery = true)
    List<Order> findAllOrdersByCustomerId(Long customerId);

}


