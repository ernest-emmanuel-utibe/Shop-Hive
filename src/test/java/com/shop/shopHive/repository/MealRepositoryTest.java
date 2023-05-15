package com.shop.shopHive.repository;

import com.shop.shopHive.models.City;
import com.shop.shopHive.models.Meal;
import com.shop.shopHive.models.ServiceProvider;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Slf4j
@Tag("AddingAMealToCatalog")
@DisplayName("Service Provider should be")
class MealRepositoryTest {

    @Autowired
    ServiceProviderRepository serviceProviderRepository;

    @Autowired
    MealRepository mealRepository;

    private ServiceProvider serviceProvider;

    @BeforeEach
    void init(){
        log.info("inside the before each method...........");
        serviceProvider = new ServiceProvider();
    }


    @Test
    @DisplayName("be able to create an account")
    void canCreateAnAccount() {
        log.info("registration ..............");

        serviceProvider.setName("papa israel");
        serviceProvider.setEmail("israel@gmail.com");
        serviceProvider.setAddress("no 6 tokunbo str, lagos");
        serviceProvider.setCity(City.LAGOS);
        serviceProvider.setPhoneNumber("08187583765");

        assertThat(serviceProvider).isNotNull();

        assertAll(
                () ->  assertEquals("papa israel", serviceProvider.getName()),
                () ->  assertEquals("israel@gmail.com", serviceProvider.getEmail())
        );
    }


//    @Test
//    @DisplayName("able to add a meal to its catalog")
//    @Transactional
//    public void serviceProviderCanAddMeal(){
//        log.info("adding meal ..............");
//        ServiceProvider saved = serviceProviderRepository.save(serviceProvider);
//
//        Meal meal = new Meal();
//        meal.setName("Gelato");
//        meal.setPrice(23.00);
//        meal.setPreparationTime("1 hr 20 mins");
//        meal.setDescription("bread and tea");
//
//        saved.addMeal(meal);
//
//        assertThat(meal).isNotNull();
//        assertNotNull(serviceProvider.getListOfMeals());
//
////        assertEquals(1, serviceProvider.getListOfMeals().size());
////        assertEquals(23.00, meal.getPrice());
////        assertEquals("bread and tea", meal.getDescription());
//
//        assertAll(
//                () ->  assertEquals(1, serviceProvider.getListOfMeals().size()),
//                () ->  assertEquals(23.00, meal.getPrice())
//        );
//    }

//    //two meal cannot have same name
//    @Test
//    @DisplayName("throws an error ")
//    public void whenServiceProviderInputAMealName_throwExceptionWhenNameAlreadyExist(){
//
//        log.info("adding meal with the same name ..............");
//        ServiceProvider saved = serviceProviderRepository.save(serviceProvider);
//
//        Meal meal = new Meal();
//        meal.setName("Gelato");
//        meal.setPrice(54.00);
//        meal.setPreparationTime("2 hr 20 mins");
//        meal.setDescription("rice and spag");
//
//        saved.addMeal(meal);
//
//        assertThrows(Error.class, () ->  meal.setName("Gelato"));
//
//
//    }

    @Nested
    @DisplayName("return all meals")
    class MealAdded{

        @Test
        void whenThereAreNoMeal(){

            List<Meal> listOfMeals =  serviceProvider.getListOfMeals();
            assertEquals(0, listOfMeals.size());
        }

        @Test
        void whenThereSomeMeals(){
            Meal meal = new Meal("geloro", 23.90, "2 hrs 40 mins", "beans and yam");
            Meal meal1 = new Meal("saldo", 23.90, "2 hrs 40 mins", "beans and yam");
            Meal meal2 = new Meal("fakamo", 23.90, "2 hrs 40 mins", "beans and yam");

            List<Meal> listOfMeals = new ArrayList<>();
            listOfMeals.add(meal);
            listOfMeals.add(meal1);
            listOfMeals.add(meal2);

            serviceProvider.setListOfMeals(listOfMeals);

            assertEquals(3, serviceProvider.getListOfMeals());
        }
    }

    //delete a meal

    //edit a meal
}