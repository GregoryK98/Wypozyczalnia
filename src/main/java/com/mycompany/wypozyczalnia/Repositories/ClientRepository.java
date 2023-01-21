package com.mycompany.wypozyczalnia.Repositories;

import com.mycompany.wypozyczalnia.IRepositories.IClientRepository;
import com.mycompany.wypozyczalnia.Models.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository implements IClientRepository {

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
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void update(Client client) throws SQLException {
        try (PreparedStatement stmt = _conn.prepareStatement("UPDATE Klienci SET Imie = ?, Nazwisko = ?, Adres = ?, Numer_telefonu = ? WHERE ID_klienta = ?")) {
            stmt.setString(1, client.Imie);
            stmt.setString(2, client.Nazwisko);
            stmt.setString(3, client.Adres);
            stmt.setString(4, client.NumerTelefonu);
            stmt.setString(5, String.valueOf(client.ID));

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<Client> getAll() throws SQLException {
        List<Client> clients = new ArrayList<Client>();
        try (Statement stmt = _conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM Klienci");
            while (rs.next()) {
                Client client = new Client();
                client.ID = rs.getInt("ID_klienta");
                client.Imie = rs.getString("Imie");
                client.Nazwisko = rs.getString("Nazwisko");
                client.Adres = rs.getString("Adres");
                client.NumerTelefonu = rs.getString("Numer_telefonu");
                clients.add(client);
            }
        } catch (SQLException e) {
            throw e;
        }
        return clients;
    }
}
