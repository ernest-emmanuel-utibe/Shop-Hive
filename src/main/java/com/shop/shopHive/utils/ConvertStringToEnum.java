package com.shop.shopHive.utils;

import com.shop.shopHive.models.City;

public class ConvertStringToEnum {

    public static City convertStringToEnum(String city) {

        String cityUpperCase = city.toUpperCase();

        City enumCity = City.valueOf(cityUpperCase); //IMPLEMENT YOUR OWN valueOf()
        //String s = City.LAGOS.equals(enumCity) ? "Yes" : "No";
        //System.out.println(s);
        return enumCity;
    }

    public static String convertEnumToString(City city) {

        String stringCity = city.toString();
        return stringCity;
    }
}
