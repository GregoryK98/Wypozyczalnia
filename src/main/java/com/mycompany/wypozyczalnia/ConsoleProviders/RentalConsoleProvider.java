package com.mycompany.wypozyczalnia.ConsoleProviders;

import com.mycompany.wypozyczalnia.IProvider.IRentalProvider;
import com.mycompany.wypozyczalnia.Models.Client;
import com.mycompany.wypozyczalnia.Models.Rental;

import java.util.List;
import java.util.Scanner;

public class RentalConsoleProvider implements IRentalProvider {
    private Scanner _scanner;

    public RentalConsoleProvider(Scanner scanner) {
        _scanner = scanner;
    }

    public Rental readRental(boolean readId) {
        Rental rental = new Rental();
        if (readId) {
            System.out.print("Id: ");
            rental.ID = _scanner.nextInt();
        }
        System.out.print("Data wypozyczenia: ");
        rental.DataWypozyczenia = _scanner.next();
        System.out.print("Data zwrotu: ");
        rental.DataZwrotu = _scanner.next();


        return rental;
    }

    public void writeRentals(List<Rental> rentals) {
        for(Rental rental : rentals) {
            System.out.println("ID: " + rental.ID
                    + ", Data wypozyczenia: " + rental.DataWypozyczenia
                    + ", Data zwrotu: " + rental.DataZwrotu);
        }
    }
}
