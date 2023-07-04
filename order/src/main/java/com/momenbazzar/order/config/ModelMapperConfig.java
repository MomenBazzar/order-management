package com.momenbazzar.order.config;

import com.momenbazzar.order.dto.CustomerDTO;
import com.momenbazzar.order.dto.OrderDTO;
import com.momenbazzar.order.dto.ProductDTO;
import com.momenbazzar.order.dto.ProductOrderDTO;
import com.momenbazzar.order.model.Customer;
import com.momenbazzar.order.model.Order;
import com.momenbazzar.order.model.Product;
import com.momenbazzar.order.model.ProductOrder;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Configure mappings
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // ================= Product ===================

        // Define mappings between entity and DTO
        modelMapper.typeMap(Product.class, ProductDTO.class)
                .addMappings(mapper -> {
                    mapper.map(Product::getId, ProductDTO::setId);
                    mapper.map(Product::getSlug, ProductDTO::setSlug);
                    mapper.map(Product::getName, ProductDTO::setName);
                    mapper.map(Product::getReference, ProductDTO::setReference);
                    mapper.map(Product::getPrice, ProductDTO::setPrice);
                    mapper.map(Product::getVat, ProductDTO::setVat);
                    mapper.map(Product::isStockable, ProductDTO::setStockable);
                    mapper.map(Product::getStockQuantity, ProductDTO::setStockQuantity);
                });

        // Define mappings between DTO and entity (opposite direction)
        modelMapper.typeMap(ProductDTO.class, Product.class)
                .addMappings(mapper -> {
                    mapper.map(ProductDTO::getSlug, Product::setSlug);
                    mapper.map(ProductDTO::getName, Product::setName);
                    mapper.map(ProductDTO::getReference, Product::setReference);
                    mapper.map(ProductDTO::getPrice, Product::setPrice);
                    mapper.map(ProductDTO::getVat, Product::setVat);
                    mapper.map(ProductDTO::isStockable, Product::setStockable);
                    mapper.map(ProductDTO::getStockQuantity, Product::setStockQuantity);
                });

        // ================= Customer ===================

        // Define mappings between entity and DTO
        modelMapper.typeMap(Customer.class, CustomerDTO.class)
                .addMappings(mapper -> {
                    mapper.map(Customer::getId, CustomerDTO::setId);
                    mapper.map(Customer::getFirstName, CustomerDTO::setFirstName);
                    mapper.map(Customer::getLastName, CustomerDTO::setLastName);
                    mapper.map(Customer::getBornAt, CustomerDTO::setBornAt);
                });

        // Define mappings between DTO and entity (opposite direction)
        modelMapper.typeMap(CustomerDTO.class, Customer.class)
                .addMappings(mapper -> {
                    mapper.map(CustomerDTO::getFirstName, Customer::setFirstName);
                    mapper.map(CustomerDTO::getLastName, Customer::setLastName);
                    mapper.map(CustomerDTO::getBornAt, Customer::setBornAt);
                });

        // ================= ProductOrder ===================

        // Define mappings between entity and DTO
        modelMapper.typeMap(ProductOrder.class, ProductOrderDTO.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getProduct().getId(), ProductOrderDTO::setProductId);
                    mapper.map(src -> src.getOrder().getId(), ProductOrderDTO::setOrderId);
                    mapper.map(ProductOrder::getQuantity, ProductOrderDTO::setQuantity);
                    mapper.map(ProductOrder::getPrice, ProductOrderDTO::setPrice);
                    mapper.map(ProductOrder::getVat, ProductOrderDTO::setVat);
                });

        // ================= Order ===================

        // Define mappings between entity and DTO
        modelMapper.typeMap(Order.class, OrderDTO.class)
                .addMappings(mapper -> {
                    mapper.map(Order::getId, OrderDTO::setId);
                    mapper.map(src -> src.getCustomer().getId(), OrderDTO::setCustomerId);
                    mapper.map(Order::getOrderedAt, OrderDTO::setOrderedAt);
                });


        return modelMapper;
    }
}
