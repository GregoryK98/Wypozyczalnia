package com.mycompany.wypozyczalnia.Validators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DataValidatorTest {
    //        System.out.println(isValid("31/02/2022")); // false
//        System.out.println(isValid("28/02/2022")); // true
    @Test
    void date_valid() {
        String data = "31/01/2022";
        DataValidator dataValidator = new DataValidator();
        assertTrue(dataValidator.DataValidate(data));
    }

    @Test
    void date_when_day_is_missing() {
        String data = "/01/2022";
        DataValidator dataValidator = new DataValidator();
        assertFalse(dataValidator.DataValidate(data));
    }

    @Test
    void date_when_mounth_is_missing() {
        String data = "31//2022";
        DataValidator dataValidator = new DataValidator();
        assertFalse(dataValidator.DataValidate(data));
    }

    @Test
    void date_when_year_is_missing() {
        String data = "31/01/";
        DataValidator dataValidator = new DataValidator();
        assertFalse(dataValidator.DataValidate(data));
    }

    @Test
    void date_when_backslash_missing() {
        String data = "3101/2022";
        DataValidator dataValidator = new DataValidator();
        assertFalse(dataValidator.DataValidate(data));
    }
}
