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
                "(data_wypozyczenia, data_zwrotu, id_klienta, id_sprzetu) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, String.valueOf(rental.DataWypozyczenia));
            stmt.setString(2, String.valueOf(rental.DataZwrotu));
            stmt.setString(3, String.valueOf(rental.IDKlienta));
            stmt.setString(4, String.valueOf(rental.IDSprzetu));

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }


    public void update(Rental rental) throws SQLException {
        try (PreparedStatement stmt = _conn.prepareStatement("UPDATE Wypozyczenia SET " +
                "Data_Wypozyczenia = ?, Data_Zwrotu = ?, ID_Klienta = ?, ID_Sprzetu = ? WHERE ID_wypozyczenia = ?")) {
            stmt.setString(1, String.valueOf(rental.DataWypozyczenia));
            stmt.setString(2, String.valueOf(rental.DataZwrotu));
            stmt.setString(3, String.valueOf(rental.IDKlienta));
            stmt.setString(4, String.valueOf(rental.IDSprzetu));
            stmt.setString(5, String.valueOf(rental.ID));

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
                rental.IDSprzetu = rs.getInt("ID_sprzetu");
                rental.DataWypozyczenia = rs.getString("Data_wypozyczenia");
                rental.DataZwrotu = rs.getString("Data_Zwrotu");
                rentals.add(rental);
            }
        } catch (SQLException e) {
            throw e;
        }
        return rentals;
    }
}