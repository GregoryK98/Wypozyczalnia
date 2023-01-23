package com.mycompany.wypozyczalnia.Validators;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataValidator {

    private static final String DATE_FORMAT = "dd/MM/yyyy";

    public static boolean DataValidate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setLenient(false);
        try {
            sdf.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
