package com.mycompany.wypozyczalnia.Repositories;

import com.mycompany.wypozyczalnia.Models.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientRepository {

    private Connection _conn;

    public ClientRepository(Connection conn) {
        _conn = conn;
    }

    public void create(Client client) throws SQLException {
        try (PreparedStatement stmt = _conn.prepareStatement("INSERT INTO db.klienci (Imie, Nazwisko, Adres, Numer_telefonu) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, client.Imie);
            stmt.setString(2, client.Nazwisko);
            stmt.setString(3, client.Adres);
            stmt.setString(4, client.NumerTelefonu);
            int rowsAffected = stmt.executeUpdate();
        } catch(SQLException e) {
            throw e;
        }
    }
    public void update(Client client) {

    }
    public void getAll(Client client) {

    }
}
