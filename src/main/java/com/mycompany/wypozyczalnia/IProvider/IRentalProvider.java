package com.mycompany.wypozyczalnia.IProvider;

import com.mycompany.wypozyczalnia.Models.Client;
import com.mycompany.wypozyczalnia.Models.Rental;

import java.util.List;

public interface IRentalProvider {
    public Rental readRental(boolean readId);
    public void writeRentals(List<Rental> rentals);
}
