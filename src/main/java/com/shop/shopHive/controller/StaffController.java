package com.shop.shopHive.controller;

import com.shop.shopHive.dto.UpdateStaffProfileDto;
import com.shop.shopHive.models.Customer;
import com.shop.shopHive.models.Staff;
import com.shop.shopHive.service.StaffService;
import com.shop.shopHive.service.exception.StaffAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("api/staff")
@Slf4j
public class StaffController {
    @Autowired
    StaffService staffService;

    @PostMapping("/registration")
    ResponseEntity<?> registration(@RequestBody Staff registrationDto){

        try {
            staffService.create_account(registrationDto);
        } catch (StaffAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return ResponseEntity.ok().body("registration successful");
    }

    @GetMapping("/get-logged-in-staff")
    public ResponseEntity<Staff> getLoggedInUser(){

        log.info("Get logged in user called");
        Staff staff = staffService.getLoggedInUser();

        log.info("Object found --> {}", staff);
        return ResponseEntity.ok().body(staff);

    }

    @GetMapping("/list_All_Customers")
    ResponseEntity<?> getAllCustomers(){

        List<Customer> savedCustomers;
        try {
            savedCustomers =  staffService.getAllCustomers();
        } catch (StaffAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return ResponseEntity.ok().body(savedCustomers);
    }

    @PostMapping("/update_profile")
    ResponseEntity<?> updateStaffProfile(@RequestBody @NotNull UpdateStaffProfileDto updateStaffProfileDto){

        try {
            staffService.updateProfile(updateStaffProfileDto);
        } catch (StaffAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return ResponseEntity.ok().body("staff info successfully updated!");
    }
}
