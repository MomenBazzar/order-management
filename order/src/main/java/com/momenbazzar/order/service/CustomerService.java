package com.momenbazzar.order.service;

import com.momenbazzar.order.exception.NotFoundException;
import com.momenbazzar.order.model.Customer;
import com.momenbazzar.order.model.Order;
import com.momenbazzar.order.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long customerId, Customer updatedCustomer) {
        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("Customer not found"));

        existingCustomer.setFirstName(updatedCustomer.getFirstName());
        existingCustomer.setLastName(updatedCustomer.getLastName());
        existingCustomer.setBornAt(updatedCustomer.getBornAt());

        return customerRepository.save(existingCustomer);
    }

    public void deleteCustomer(Long customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new NotFoundException("Customer not found");
        }

        customerRepository.deleteById(customerId);
    }

    public List<Order> getAllOrdersByCustomerId(Long customerId) {
        return customerRepository.findAllOrdersByCustomerId(customerId);
    }
}
