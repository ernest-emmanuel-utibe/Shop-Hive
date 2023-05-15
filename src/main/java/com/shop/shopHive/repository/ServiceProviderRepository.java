package com.shop.shopHive.repository;

import com.shop.shopHive.models.City;
import com.shop.shopHive.models.ServiceProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceProviderRepository extends JpaRepository<ServiceProvider, Long> {
    ServiceProvider findByEmail(String email);

    ServiceProvider findByEmailAndName(String email, String name);

    ServiceProvider findByEmailOrName(String email, String name);

    ServiceProvider findByName(String serviceProviderName);

    boolean existsByName(String serviceProviderName);

    // Optional<?> findByCity(City city);

    boolean existsByCity(City city); // String city


    ServiceProvider findByCity(City city);

    List<ServiceProvider> findAllByCity(City city);
}
