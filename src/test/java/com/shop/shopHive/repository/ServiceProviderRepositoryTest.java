package com.shop.shopHive.repository;

import com.shop.shopHive.models.City;
import com.shop.shopHive.models.ServiceProvider;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Tag("ServiceProviderRegistration")
@DisplayName("Service Provider should")
@SpringBootTest
class ServiceProviderRepositoryTest {

    @Autowired
    ServiceProviderRepository serviceProviderRepository;

    private ServiceProvider serviceProvider;

    private ServiceProvider serviceProvider1;

//    @BeforeAll
//    static void testClassSetUp(){
//        log.info("Before All.........");
//    }

    @BeforeEach
    void init(){
        log.info("Before each..............");

    }

    @Test
    @DisplayName("be able to create an account")
    void canCreateAnAccount() {
        log.info("registration ..............");
        serviceProvider = new ServiceProvider();
        serviceProvider.setName("mama k");
        serviceProvider1.setEmail("jjocha@gmail.com");
        serviceProvider.setAddress("no 3 saki street, yaba, lagos");
        serviceProvider.setCity(City.LAGOS);
        serviceProvider.setPhoneNumber("09087665434");

        assertAll(
                () ->  assertEquals("mama k", serviceProvider.getName()),
                () ->  assertEquals("jjocha@gmail.com", serviceProvider.getEmail())
        );
    }

    //throw exception when you register with an email that already exit
    //unique email
    @Test
    @DisplayName("throws a ResponseStatusException")
    Throwable throwsExceptionForAnAlreadyExistEmail(){
        log.info("throw exception here........");
        serviceProvider1 = new ServiceProvider();
        serviceProvider1.setName("Mr Cabs place");
        serviceProvider1.setEmail("jjocha@gmail.com");
        serviceProvider1.setAddress("no 2 tobi street, lekki, lagos");
        serviceProvider1.setCity(City.LAGOS);
        serviceProvider1.setPhoneNumber("0907654332");

        Throwable error =
                assertThrows(ResponseStatusException.class, () ->   serviceProvider1.setEmail("jjocha@gmail.com"));

        assertEquals("service provider with the name Mr Cabs place or email jjocha@gmail.com already exist", error.getMessage());
        return error;
    }

//    @AfterEach
//    void testTearDown(){
//        log.info("Tear down.................");
//    }

//    @AfterAll
//    void testTearDown1(){
//        log.info("After all.................");
//    }
}