package com.shop.shopHive.controller;

import com.shop.shopHive.dto.AddMealRequest;
import com.shop.shopHive.dto.UpdateMealRequest;
import com.shop.shopHive.dto.UpdateServiceProviderProfileDto;
import com.shop.shopHive.models.ServiceProvider;
import com.shop.shopHive.service.ServiceProviderService;
import com.shop.shopHive.service.exception.ServiceProviderAlreadyExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("api/serviceProvider")
@Slf4j
public class ServiceProviderController {
    @Autowired
    ServiceProviderService serviceProviderService;

    @PostMapping("/create_account")
    public ResponseEntity<?> registration(@RequestBody ServiceProvider serviceProvider){

        try {
            serviceProviderService.create(serviceProvider);
        } catch (ServiceProviderAlreadyExistException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return ResponseEntity.ok().body("registration successful");
    }

    @PostMapping("/add_meal")
    ResponseEntity<?> addMeal(@RequestBody @NotNull AddMealRequest addMealRequest){

        try {
            serviceProviderService.addFood(addMealRequest);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getLocalizedMessage());
        }
        return ResponseEntity.ok().body("meal was successfully added");
    }

    @PostMapping("/update-profile")
    ResponseEntity<?> updateProfile(@RequestBody UpdateServiceProviderProfileDto updateServiceProviderProfileDto){

        try {
            serviceProviderService.updateProfile(updateServiceProviderProfileDto);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return ResponseEntity.ok().body("service provider profile successfully updated!");
    }

    @PostMapping("/delete_meal/{mealName}")
    ResponseEntity<?> deleteMeal(@PathVariable @NotNull String mealName){

        try {
            serviceProviderService.deleteMeal(mealName);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return ResponseEntity.ok().body("meal successfully deleted!");
    }

    @PostMapping("/edit_meal")
    ResponseEntity<?> editMeal(@RequestBody UpdateMealRequest updateMealRequest){

        try {
            serviceProviderService.editMeal(updateMealRequest);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return ResponseEntity.ok().body("meal successfully edited!");
    }
}
