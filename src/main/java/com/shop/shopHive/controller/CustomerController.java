package com.shop.shopHive.controller;

import com.shop.shopHive.dto.OrderRequest;
import com.shop.shopHive.dto.UpdateCustomerProfileDto;
import com.shop.shopHive.models.Customer;
import com.shop.shopHive.models.ServiceProvider;
import com.shop.shopHive.service.CustomerService;
import com.shop.shopHive.service.exception.CustomerAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("api/customer")
@Slf4j
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/registration")
    ResponseEntity<?> registration(@RequestBody Customer registrationDto) {
        try{
            customerService.create_account(registrationDto);
        } catch(CustomerAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return ResponseEntity.ok().body("registration successful");
    }

    @GetMapping("/get-logged-in-customer")
    public ResponseEntity<Customer> getLoggedInUser(){

        log.info("Get logged in user called");
        Customer customer = customerService.getLoggedInUser();

        log.info("Object found --> {}", customer);
        return ResponseEntity.ok().body(customer);

    }

    @PostMapping("/update-profile")
    ResponseEntity<?> updateProfile(@RequestBody UpdateCustomerProfileDto updateCustomerProfileDto) {

        try {
            customerService.updateProfile(updateCustomerProfileDto);
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return ResponseEntity.ok().body("customer profile successfully updated");
    }

    @GetMapping("/find-serviceProviders/{city}")
    ResponseEntity<?> getAllServiceProvidersInACity(@PathVariable @NotNull String city)  {

        List<ServiceProvider> serviceProviderList;
        try {
            serviceProviderList = customerService.findAllServiceProvidersByCity(city);
        } catch (IllegalArgumentException e)  {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No service provider found in "+ city);
        }
        return ResponseEntity.ok().body(serviceProviderList);
    }

    @GetMapping("/get-All-Customers-ByDate/{date}")
    ResponseEntity<?> getAllCustomersByDate(@PathVariable @NotNull String date) {

        List<Customer> customers;
        log.info("request to get All customers by date "+ date);

        try {
            customers = customerService.findAllCustomerByDate(date);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No customer was created on "+ date);
        }
        return ResponseEntity.ok().body(customers);
    }

    @PostMapping("/place-order")
    ResponseEntity<?> placeOrder(@RequestBody OrderRequest orderRequest) {

        try {
            customerService.placeOrder(orderRequest);
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return ResponseEntity.ok().body("order successfully created");
    }
}
