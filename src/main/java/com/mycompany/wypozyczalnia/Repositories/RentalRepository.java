package com.mycompany.wypozyczalnia.Repositories;

import com.mycompany.wypozyczalnia.IRepositories.IRentalRepository;
import com.mycompany.wypozyczalnia.Models.Client;
import com.mycompany.wypozyczalnia.Models.Rental;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RentalRepository implements IRentalRepository {

    private Connection _conn;

    public RentalRepository(Connection conn) {
        _conn = conn;
    }


    public void create(Rental rental) throws SQLException {
        try (PreparedStatement stmt = _conn.prepareStatement("INSERT INTO db.wypozyczenia " +
                "(Imie, Nazwisko, Adres, Numer_telefonu) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, String.valueOf(rental.DataWypozyczenia));
            stmt.setString(2, String.valueOf(rental.DataZwrotu));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }


    public void update(Rental rental) throws SQLException {
        try (PreparedStatement stmt = _conn.prepareStatement("UPDATE Wypozyczenia SET " +
                "DataWypozyczenia = ?, DataZwrotu = ? WHERE ID_klienta = ?")) {
            stmt.setString(1, String.valueOf(rental.DataWypozyczenia));
            stmt.setString(2, String.valueOf(rental.DataZwrotu));
            stmt.setString(3, String.valueOf(rental.ID));

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }


    public List<Rental> getAll() throws SQLException {
        List<Rental> rentals = new ArrayList<Rental>();
        try (Statement stmt = _conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM Wypozyczenia");
            while (rs.next()) {
                Rental rental = new Rental();
                rental.ID = rs.getInt("ID_wypozyczenia");
                rental.IDKlienta = rs.getInt("ID_klienta");
                rental.IDSprzetu = rs.getInt("ID_sorzetu");
                rental.DataWypozyczenia = rs.getString("Adres");
                rentals.add(rental);
            }
        } catch (SQLException e) {
            throw e;
        }
        return rentals;
    }
}