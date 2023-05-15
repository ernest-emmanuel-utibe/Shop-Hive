package com.shop.shopHive.repository;

import com.shop.shopHive.models.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Long> {
    Meal findByName(String name);
}
