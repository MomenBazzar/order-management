package com.momenbazzar.order.dto;

import com.momenbazzar.order.model.Product;
import lombok.Data;

@Data
public class ProductOrderAddDTO {
    private Long productId;
    private int quantity;
}

