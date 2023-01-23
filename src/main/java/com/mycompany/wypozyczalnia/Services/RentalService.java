package com.mycompany.wypozyczalnia.Services;

import com.mycompany.wypozyczalnia.IProvider.IClientProvider;
import com.mycompany.wypozyczalnia.IProvider.IRentalProvider;
import com.mycompany.wypozyczalnia.IRepositories.IClientRepository;
import com.mycompany.wypozyczalnia.IRepositories.IRentalRepository;
import com.mycompany.wypozyczalnia.Models.Client;
import com.mycompany.wypozyczalnia.Models.Rental;
import com.mycompany.wypozyczalnia.Repositories.RentalRepository;
import com.mycompany.wypozyczalnia.Validators.ClientValidator;
import com.mycompany.wypozyczalnia.Validators.RentalValidator;

public class RentalService {
    private IRentalProvider _rentalProvider;
    private IRentalRepository _rentalRepository;

    public RentalService(IRentalProvider rentalProvider, IRentalRepository rentalRepository) {
        _rentalProvider = rentalProvider;
        _rentalRepository = (RentalRepository) rentalRepository;
    }

    public String addRental() {
        Rental rental = _rentalProvider.readRental(false);
        RentalValidator rentalValidator = new RentalValidator();
        if (!rentalValidator.validate(rental))
            return "Rental cannot be added because is not valid.\n";
        try {
            _rentalRepository.create(rental);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Rental added.\n";
    }

    public String updateRental() {
        Rental rental = _rentalProvider.readRental(true);
        RentalValidator rentalValidator = new RentalValidator();
        if (!rentalValidator.validate(rental))
            return "Rental cannot be updated because new rentals data are not not valid.\n";
        try {
            _rentalRepository.update(rental);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Rental updated.\n";
    }

    public String showAll() {
        try {
            _rentalProvider.writeRentals(_rentalRepository.getAll());
            return "";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
