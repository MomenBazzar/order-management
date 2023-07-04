package com.momenbazzar.order.controller;

import com.momenbazzar.order.dto.CustomerDTO;
import com.momenbazzar.order.exception.NotFoundException;
import com.momenbazzar.order.model.Customer;
import com.momenbazzar.order.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final ModelMapper modelMapper;

    public CustomerController(CustomerService customerService, ModelMapper modelMapper) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long customerId) {
        try {
            Customer customer = customerService.getCustomerById(customerId);
            CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
            return ResponseEntity.ok(customerDTO);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        Customer createdCustomer = customerService.createCustomer(customer);
        CustomerDTO createdCustomerDTO = modelMapper.map(createdCustomer, CustomerDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomerDTO);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDTO customerDTO) {
        try {
            Customer customer = modelMapper.map(customerDTO, Customer.class);
            Customer updatedCustomer = customerService.updateCustomer(customerId, customer);
            CustomerDTO updatedCustomerDTO = modelMapper.map(updatedCustomer, CustomerDTO.class);
            return ResponseEntity.ok(updatedCustomerDTO);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
        try {
            customerService.deleteCustomer(customerId);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
