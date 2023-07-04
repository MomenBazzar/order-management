package com.momenbazzar.order.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate bornAt;
}


