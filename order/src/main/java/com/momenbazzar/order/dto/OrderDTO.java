package com.momenbazzar.order.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private Long CustomerId;
    private LocalDateTime orderedAt;
}
