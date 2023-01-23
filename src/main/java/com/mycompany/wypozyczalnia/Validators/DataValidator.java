package com.mycompany.wypozyczalnia.Validators;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataValidator {

    private static final String DATE_FORMAT = "dd/MM/yyyy";

    public static boolean DataValidate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setLenient(false);
        try {
            Date parsedDate = sdf.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parsedDate);
            /*
            int year = calendar.get(Calendar.YEAR);
            if (year % 4 == 0) {
                if (year % 100 == 0) {
                    if (year % 400 == 0) {
                        return true;
                    }
                } else {
                    return true;
                }
            }
            return false;
             */
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}