package com.shop.shopHive.service;

import com.shop.shopHive.dto.UpdateStaffProfileDto;
import com.shop.shopHive.models.Customer;
import com.shop.shopHive.models.Staff;
import com.shop.shopHive.service.exception.StaffAlreadyExistsException;

import java.util.List;

public interface StaffService {
    void create_account(Staff registrationDto) throws StaffAlreadyExistsException;

    public Staff getLoggedInUser();

    List<Customer> getAllCustomers();

    void updateProfile(UpdateStaffProfileDto updateStaffProfileDto);
}
