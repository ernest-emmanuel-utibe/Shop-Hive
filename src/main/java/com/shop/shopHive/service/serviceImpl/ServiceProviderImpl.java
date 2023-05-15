package com.shop.shopHive.service.serviceImpl;

import com.shop.shopHive.dto.AddMealRequest;
import com.shop.shopHive.dto.UpdateMealRequest;
import com.shop.shopHive.dto.UpdateServiceProviderProfileDto;
import com.shop.shopHive.models.*;
import com.shop.shopHive.repository.AppUserRepository;
import com.shop.shopHive.repository.MealRepository;
import com.shop.shopHive.repository.ServiceProviderRepository;
import com.shop.shopHive.security.authfacade.AuthenticationFacade;
import com.shop.shopHive.service.ServiceProviderService;
import com.shop.shopHive.service.exception.MealAlreadyExistException;
import com.shop.shopHive.service.exception.MealNotFoundException;
import com.shop.shopHive.service.exception.ServiceProviderAlreadyExistException;
import com.shop.shopHive.service.exception.ServiceProviderNotFoundException;
import com.shop.shopHive.utils.ConvertStringToEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ServiceProviderImpl implements ServiceProviderService {

    @Autowired
    AuthenticationFacade authenticationFacade;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    ServiceProviderRepository serviceProviderRepository;

    @Autowired
    MealRepository mealRepository;

    @Override
    public void create(ServiceProvider serviceProvider) throws ServiceProviderAlreadyExistException {

        log.info("service provider registration request --> {}", serviceProvider);

        if(serviceProviderRepository.findByEmailOrName(serviceProvider.getEmail(), serviceProvider.getName()) == null){

            log.info("Service Provider is not registered ");

            AppUser appUser = new AppUser();
            appUser.setEmail(serviceProvider.getEmail());
            appUser.setPassword(passwordEncoder.encode(serviceProvider.getPassword()));
            appUser.addRole(Role.SERVICE_PROVIDER);
            appUser.setIsVerified(true);

            appUserRepository.save(appUser);

            log.info("App user saved ---> {}", appUser);

            serviceProvider.setAppUser(appUser);
            serviceProvider.setPassword(appUser.getPassword());

            log.info("service provider before saving --> {}", serviceProvider);

            serviceProviderRepository.save(serviceProvider);

            log.info("After saving service provider to database ---> {}", serviceProvider);

        } else {
            throw new ServiceProviderAlreadyExistException("service provider with the name " +serviceProvider.getName()+ " or email "+ serviceProvider.getEmail() +" already exist");
        }

    }

    @Override
    public ServiceProvider getLoggedInUser() {

        String name = authenticationFacade.getAuthentication().getName();
        log.info("Authentication facade --> {}", name);

        return serviceProviderRepository.findByEmail(name);
    }

    @Override
    public void updateProfile(UpdateServiceProviderProfileDto updateServiceProviderProfileDto) {

        log.info("update service profile request --------> {}", updateServiceProviderProfileDto);

        ServiceProvider savedServiceProvider = getLoggedInUser();

        City city = ConvertStringToEnum.convertStringToEnum(updateServiceProviderProfileDto.getCity());

        if(savedServiceProvider != null){
            savedServiceProvider.setName(updateServiceProviderProfileDto.getName());
            savedServiceProvider.setCity(city);
            savedServiceProvider.setAddress(updateServiceProviderProfileDto.getAddress());
            savedServiceProvider.setPhoneNumber(updateServiceProviderProfileDto.getPhoneNumber());

            serviceProviderRepository.save(savedServiceProvider);
            log.info("after updating service provider profile ------> {}", savedServiceProvider);
        } else {
            throw new ServiceProviderNotFoundException("service provider with name "+ savedServiceProvider.getName() +" does not exist");
        }
    }

    @Override
    public void deleteMeal(String mealName) {

        log.info("Request to delete "+ mealName);

        Meal savedMeal = mealRepository.findByName(mealName);

        if(savedMeal != null){
            mealRepository.delete(savedMeal);

            log.info("meal successfully deleted!!!");

        } else {
            throw new MealNotFoundException("meal "+ mealName +" cannot be found in db ");
        }

    }

    @Override
    public void editMeal(UpdateMealRequest updateMealRequest) {

        log.info("Request to edit "+ updateMealRequest.getSavedMeal());

        Meal savedMeal = mealRepository.findByName(updateMealRequest.getSavedMeal());

        if(savedMeal != null){
            savedMeal.setName(updateMealRequest.getName());
            savedMeal.setPrice(updateMealRequest.getPrice());
            savedMeal.setDescription(updateMealRequest.getDescription());
            savedMeal.setPreparationTime(updateMealRequest.getPreparationTime());

            mealRepository.save(savedMeal);

            log.info("after updating meal ---->"+ savedMeal);

        } else {
            throw new MealNotFoundException("meal "+ updateMealRequest.getSavedMeal() +" cannot be found in db ");
        }
    }


    @Override
    public void addFood(AddMealRequest addMealRequest) {

        log.info("add food request ------> {}", addMealRequest);

        ServiceProvider serviceProvider = getLoggedInUser();
        log.info("logged-in service provider ------->{}", serviceProvider);

        Meal meal = mealRepository.findByName(addMealRequest.getName());

        if(meal == null){

            Meal meal1 = new Meal();
            meal1.setDescription(addMealRequest.getDescription());
            meal1.setName(addMealRequest.getName());
            meal1.setPreparationTime(addMealRequest.getPreparationTime());
            meal1.setPrice(addMealRequest.getPrice());

            mealRepository.save(meal1);

            log.info("after saving meal to db --> {}", meal1);

            serviceProvider.addMeal(meal1);

            // serviceProviderRepository.save()
            log.info("list of meals from the service provider  ---->{}", serviceProvider.getListOfMeals());

        } else {
            throw new MealAlreadyExistException("a meal with the name " +addMealRequest.getName() + " already exist");
        }
    }
}
