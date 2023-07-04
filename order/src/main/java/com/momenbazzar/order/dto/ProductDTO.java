package com.momenbazzar.order.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String slug;
    private String name;
    private String reference;
    private double price;
    private double vat;
    private boolean stockable;
    private int stockQuantity;
}

