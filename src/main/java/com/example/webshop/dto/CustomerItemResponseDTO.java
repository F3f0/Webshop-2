package com.example.webshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//DTO: Data to object
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerItemResponseDTO {
    private Long customerId;
    private Long itemId;
}
