package com.shop.shopHive.service;

import com.shop.shopHive.dto.OrderRequest;
import com.shop.shopHive.dto.UpdateCustomerProfileDto;
import com.shop.shopHive.models.Customer;
import com.shop.shopHive.models.ServiceProvider;
import com.shop.shopHive.service.exception.CustomerAlreadyExistsException;
import com.shop.shopHive.service.exception.ServiceProviderNotFoundException;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.text.ParseException;
import java.util.List;

public interface CustomerService {
    void create_account(Customer registrationDto) throws CustomerAlreadyExistsException;

    void verify_account();

    Customer getLoggedInUser();

    void updateProfile(UpdateCustomerProfileDto updateCustomerProfileDto);

    List<ServiceProvider> findAllServiceProvidersByCity(String city) throws ServiceProviderNotFoundException;

    List<Customer> findAllCustomerByDate(String date) throws ServiceProviderNotFoundException,
            ParseException, MissingServletRequestParameterException;

    void placeOrder(OrderRequest orderRequest);

    //get All my orders

    //reset password
}
