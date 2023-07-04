package com.momenbazzar.order.dto;

import com.momenbazzar.order.model.Product;
import lombok.Data;

@Data
public class ProductOrderDTO {
    private Long productId;
    private Long orderId;
    private int quantity;
    private double price;
    private double vat;
}


