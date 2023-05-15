package com.shop.shopHive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMealRequest {

    private String name;

    private Double price;

    private String preparationTime;

    private String description;

    private String savedMeal;
}
