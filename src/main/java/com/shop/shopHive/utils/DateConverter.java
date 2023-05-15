package com.shop.shopHive.utils;

import java.text.ParseException;
import java.time.LocalDate;

public class DateConverter {

    public static LocalDate StringToDateConverter(String date) throws ParseException {

        LocalDate newDate = LocalDate.parse(date);
        return newDate;

    }
}
