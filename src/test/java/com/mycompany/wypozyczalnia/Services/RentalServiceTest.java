package com.mycompany.wypozyczalnia.Services;

import com.mycompany.wypozyczalnia.IProvider.IRentalProvider;
import com.mycompany.wypozyczalnia.Models.Rental;
import com.mycompany.wypozyczalnia.Repositories.RentalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RentalServiceTest {
    @Mock
    private IRentalProvider rentalProviderMock;
    @Mock
    private RentalRepository rentalRepositoryMock;

    @Test
    void add_return_rental_added_if_everything_ok() throws SQLException {
        Rental rental = new Rental();
        rental.DataWypozyczenia = "20:12:2022";
        rental.DataZwrotu = "10:01:2013";

        assertNotNull(rentalRepositoryMock);
        assertNotNull(rentalProviderMock);
        when(rentalProviderMock.readRental(false)).thenReturn(rental);

        RentalService rentalService = new RentalService(rentalProviderMock, rentalRepositoryMock);
        assertTrue(rentalService.addRental().equals("Rental added.\n"));
    }

    @Test
    void add_return_rental_added_if_return_date_is_null() throws SQLException {
        Rental rental = new Rental();
        rental.DataWypozyczenia = "20:12:2022";
        rental.DataZwrotu = "";

        assertNotNull(rentalRepositoryMock);
        assertNotNull(rentalProviderMock);
        when(rentalProviderMock.readRental(false)).thenReturn(rental);

        RentalService rentalService = new RentalService(rentalProviderMock, rentalRepositoryMock);
        assertTrue(rentalService.addRental().equals("Rental cannot be added because is not valid.\n"));
    }
}