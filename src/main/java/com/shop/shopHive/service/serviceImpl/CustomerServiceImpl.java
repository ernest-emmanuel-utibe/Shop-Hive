package com.shop.shopHive.service.serviceImpl;

import com.shop.shopHive.dto.OrderRequest;
import com.shop.shopHive.dto.UpdateCustomerProfileDto;
import com.shop.shopHive.models.*;
import com.shop.shopHive.repository.*;
import com.shop.shopHive.security.authfacade.AuthenticationFacade;
import com.shop.shopHive.service.CustomerService;
import com.shop.shopHive.service.exception.*;
import com.shop.shopHive.utils.ConvertStringToEnum;
import com.shop.shopHive.utils.DateConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    AuthenticationFacade authenticationFacade;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ServiceProviderRepository serviceProviderRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    MealRepository mealRepository;

    @Autowired
    CustomerOrderRepository customerOrderRepository;

    @Override
    public void create_account(Customer registrationDto) throws CustomerAlreadyExistsException {

        log.info("Customer registration request ---> {}", registrationDto);

        if(customerRepository.findByEmail(registrationDto.getEmail()) == null){

            log.info("Customer is not registered ");

            AppUser appUser = new AppUser();
            appUser.setEmail(registrationDto.getEmail());
            appUser.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
            appUser.addRole(Role.CUSTOMER);
            appUser.setIsVerified(true);

            appUserRepository.save(appUser);

            log.info("App user saved ---> {}", appUser);

            registrationDto.setAppUser(appUser);
            registrationDto.setPassword(appUser.getPassword());

            log.info("Customer before saving --> {}", registrationDto);

            customerRepository.save(registrationDto);

            log.info("After saving customer details to db --->{}", registrationDto);

            //send verification token to email

        } else {
            throw new CustomerAlreadyExistsException("a customer with email "+ registrationDto.getEmail() +" already exist!");
        }

    }

    @Override
    public void verify_account() {

    }


    @Override
    public Customer getLoggedInUser() {

        String name = authenticationFacade.getAuthentication().getName();
        log.info("Authentication facade --> {}", name);

        return customerRepository.findByEmail(name);
    }

    @Override
    public void updateProfile(UpdateCustomerProfileDto updateCustomerProfileDto) {

        log.info("update customer profile request --------> {}", updateCustomerProfileDto);

        // Customer savedCustomer = customerRepository.findByEmail(updateCustomerProfileDto.getEmail());
        Customer savedCustomer = getLoggedInUser();

        if(savedCustomer != null){
            savedCustomer.setFirstName(updateCustomerProfileDto.getFirstName());
            savedCustomer.setLastName(updateCustomerProfileDto.getLastName());

            customerRepository.save(savedCustomer);
            log.info("after updating customer profile ------> {}", savedCustomer);
        } else {

            //authentication exception
            throw new CustomerDoesNotExistException("customer with email "+ savedCustomer.getEmail() +" does not exist");
        }

    }

    @Override
    public List<ServiceProvider> findAllServiceProvidersByCity(String city) throws ServiceProviderNotFoundException {

        log.info("getting all service providers in "+ city);
        City newCity = ConvertStringToEnum.convertStringToEnum(city); //issue

        //City newCity = City.getByName(city);

        //City newCity = CityType.getCityType(city); //issue

        List<ServiceProvider> serviceProviderList = serviceProviderRepository.findAllByCity(newCity);
        log.info("list of service providers in "+ city + " :"+ serviceProviderList);

//        if(serviceProviderList == null){
//            throw new ServiceProviderNotFoundException("No service providers found in  "+ city);
//        }
        if(serviceProviderList != null){
            return serviceProviderList;
            //throw new ResourceNotFoundException("No service provider found in "+ city);
        } else {
            throw new ServiceProviderNotFoundException("No service provider found in "+ city);
        }
//        return serviceProviderList;
    }

    @Override
    public List<Customer> findAllCustomerByDate(String date) throws ResourceNotFoundException, ParseException {

        LocalDate newDate = DateConverter.StringToDateConverter(date);

        List<Customer> customers = customerRepository.findByDateCreated(newDate);

        log.info("list of all customers created on "+date +"  ----> {}", customers);

        if(customers == null) {
            throw new ResourceNotFoundException("There was no customer created on "+ date);
        }
        return customers;
    }

    @Override
    public void placeOrder(OrderRequest orderRequest) {

        log.info("before saving customer order ----->"+ orderRequest);

        ServiceProvider serviceProvider = serviceProviderRepository.findByName(orderRequest.getServiceProviderName());

        Meal meal = mealRepository.findByName(orderRequest.getMeal());

        City city = ConvertStringToEnum.convertStringToEnum(orderRequest.getCity());

        if(serviceProvider != null){
            if(meal != null){

                CustomerOrder customerOrder = new CustomerOrder();
                customerOrder.setMeal(orderRequest.getMeal());
                customerOrder.setServiceProvider(orderRequest.getServiceProviderName());
                customerOrder.setAddress(orderRequest.getAddress());
                customerOrder.setPhoneNumber(orderRequest.getPhoneNumber());
                customerOrder.setCity(city);

                customerOrderRepository.save(customerOrder);

                log.info("after saving customer order ----->"+ customerOrder);

            } else {
                throw new MealNotFoundException("No meal found "+ orderRequest.getMeal());
            }
        }else {
            throw new ServiceProviderNotFoundException("No service provider found in "+ orderRequest.getCity());
        }

    }
}
