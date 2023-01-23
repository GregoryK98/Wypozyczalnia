package com.mycompany.wypozyczalnia.IRepositories;

import com.mycompany.wypozyczalnia.Models.Client;
import com.mycompany.wypozyczalnia.Models.Rental;

import java.sql.SQLException;
import java.util.List;

public interface IRentalRepository {
    public void create(Rental rental) throws SQLException;

    public void update(Rental rental) throws SQLException;

    public List<Rental> getAll() throws SQLException;
}
