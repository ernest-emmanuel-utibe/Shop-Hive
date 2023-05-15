package com.shop.shopHive.dto;

import com.shop.shopHive.models.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRegistrationDto {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private AppUser appUser;
}
