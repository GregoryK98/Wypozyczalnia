package com.mycompany.wypozyczalnia.Repositories;

import com.mycompany.wypozyczalnia.Models.Equipment;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EquipmentRepository {
    private Connection _conn;

    public EquipmentRepository(Connection conn) throws SQLException {
        _conn = conn;
    }

    public void createEq(Equipment equipment) throws SQLException {
        try (PreparedStatement stmt = _conn.prepareStatement("INSERT INTO db.Sprzet (Nazwa, Typ, Dostepnosc) VALUES (?, ?, ?)")) {
            stmt.setString(1, equipment.Nazwa);
            stmt.setString(2, equipment.Typ);
            stmt.setBoolean(3, equipment.Dostepnosc);
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) affected.");
        } catch (SQLException e) {
            System.out.println("An error occurred while executing the query: " + e.getMessage());
        }
    }

    public void updateEq(Equipment equipment) throws SQLException {
        try (PreparedStatement stmt = _conn.prepareStatement("UPDATE db.Sprzet SET Nazwa = ?, Typ = ?, Dostepnosc = ? WHERE ID_sprzetu = ?")) {
            stmt.setString(1, equipment.Nazwa);
            stmt.setString(2, equipment.Typ);
            stmt.setBoolean(3, equipment.Dostepnosc);
            stmt.setInt(4, equipment.ID_Sprzetu);
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) affected.");
        } catch (SQLException e) {
            System.out.println("An error occurred while executing the query: " + e.getMessage());
        }
    }

    public void showEq(Equipment equipment) {
        try (Statement stmt = _conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM db.Sprzet");
            while (rs.next()) {
                int id = rs.getInt("ID_sprzetu");
                String nazwa = rs.getString("Nazwa");
                String typ = rs.getString("Typ");
                boolean dostepnosc = rs.getBoolean("Dostepnosc");
                System.out.println("ID: " + id + ", Nazwa: " + nazwa + ", Typ: " + typ + ", Dostepnosc: " + dostepnosc);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while executing the query: " + e.getMessage());
        }
    }

    public void deleteEq(Equipment equipment) {
        try (PreparedStatement stmt = _conn.prepareStatement("DELETE FROM db.Sprzet WHERE ID_sprzetu = ?")) {
            stmt.setInt(1, equipment.ID_Sprzetu);
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) affected.");
        } catch (SQLException e) {
            System.out.println("An error occurred while executing the query: " + e.getMessage());
        }
    }
}

