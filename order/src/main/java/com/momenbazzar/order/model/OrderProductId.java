package com.momenbazzar.order.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class OrderProductId implements Serializable {
    private Long productId;
    private Long orderId;
}
