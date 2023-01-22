package com.mycompany.wypozyczalnia.Validators;

import com.mycompany.wypozyczalnia.Models.Rental;

public class RentalValidator {
    public boolean validate(Rental rental){
        if (rental.DataWypozyczenia.isEmpty()
            || rental.DataZwrotu.isEmpty())
        return false;
    return true;
    }
}
