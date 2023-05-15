package com.shop.shopHive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffRegistrationDto {

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
