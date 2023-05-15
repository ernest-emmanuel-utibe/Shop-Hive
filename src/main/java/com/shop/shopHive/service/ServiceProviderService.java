package com.shop.shopHive.service;

import com.shop.shopHive.dto.AddMealRequest;
import com.shop.shopHive.dto.UpdateMealRequest;
import com.shop.shopHive.dto.UpdateServiceProviderProfileDto;
import com.shop.shopHive.models.ServiceProvider;
import com.shop.shopHive.service.exception.ServiceProviderAlreadyExistException;

public interface ServiceProviderService {
    void create(ServiceProvider serviceProvider) throws ServiceProviderAlreadyExistException;

    void addFood(AddMealRequest addMealRequest);

    public ServiceProvider getLoggedInUser();

    void updateProfile(UpdateServiceProviderProfileDto updateCustomerProfileDto);

    void deleteMeal(String mealName);

    void editMeal(UpdateMealRequest updateMealRequest);

    //delete a meal

    //get All the Customers that has ordered from me

    //get All the Customers that has ordered from me By City
}
