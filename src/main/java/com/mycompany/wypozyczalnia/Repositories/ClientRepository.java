package com.mycompany.wypozyczalnia.Repositories;

import com.mycompany.wypozyczalnia.Models.Client;

import java.sql.*;
import java.util.Scanner;

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
        try (PreparedStatement stmt = _conn.prepareStatement("INSERT INTO db.Klienci (Imie, Nazwisko, Adres, Numer_telefonu) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, client.Imie);
            stmt.setString(2, client.Nazwisko);
            stmt.setString(3, client.Adres);
            stmt.setString(4, client.NumerTelefonu);
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) affected.");
        } catch (SQLException e) {
            System.out.println("An error occurred while executing the query: " + e.getMessage());
        }

    }
    public void getAll(Client client) {
        try (Statement stmt = _conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM db.Klienci");
            while (rs.next()) {
                int id = rs.getInt("ID_klienta");
                String imie = rs.getString("Imie");
                String nazwisko = rs.getString("Nazwisko");
                String adres = rs.getString("Adres");
                String numer_telefonu = rs.getString("Numer_telefonu");
                System.out.println("ID: " + id + ", Imie: " + imie + ", Nazwisko: " + nazwisko + ", Adres: " + adres + ", Numer telefonu: " + numer_telefonu);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while executing the query: " + e.getMessage());
        }
    }
    private void deleteClient(Client client) {
        try (PreparedStatement stmt = _conn.prepareStatement("DELETE FROM db.Klienci WHERE ID_klienta = ?")) {
            stmt.setInt(1, client.ID);
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) affected.");
        } catch (SQLException e) {
            System.out.println("An error occurred while executing the query: " + e.getMessage());
        }
    }

}
