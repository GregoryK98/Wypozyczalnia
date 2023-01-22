package com.mycompany.wypozyczalnia.Validators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class NumerTelefonuValidatorTest {

    @Test
    void numer_valid_if_ony_digits() {
        String numer = "123456";
        NumerTelefonuValidator numerTelefonuValidator = new NumerTelefonuValidator();
        assertTrue(numerTelefonuValidator.validate(numer));
    }
    @Test
    void numer_invalid_if_ony_digits_but_to_short() {
        String numer = "1";
        NumerTelefonuValidator numerTelefonuValidator = new NumerTelefonuValidator();
        assertTrue(!numerTelefonuValidator.validate(numer));
    }
    @Test
    void numer_invalid_if_empty() {
        String numer = "";
        NumerTelefonuValidator numerTelefonuValidator = new NumerTelefonuValidator();
        assertTrue(!numerTelefonuValidator.validate(numer));
    }
    @Test
    void numer_invalid_if_null() {
        String numer = null;
        NumerTelefonuValidator numerTelefonuValidator = new NumerTelefonuValidator();
        assertTrue(!numerTelefonuValidator.validate(numer));
    }
    @Test
    void numer_valid_if_starts_with_plus_and_follow_by_digits() {
        String numer = "+123456789";
        NumerTelefonuValidator numerTelefonuValidator = new NumerTelefonuValidator();
        assertTrue(numerTelefonuValidator.validate(numer));
    }
    @Test
    void numer_invalid_if_contains_no_digit() {
        String numer = "12345g6789";
        NumerTelefonuValidator numerTelefonuValidator = new NumerTelefonuValidator();
        assertTrue(!numerTelefonuValidator.validate(numer));
    }
}
