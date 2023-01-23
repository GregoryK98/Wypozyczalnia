package com.mycompany.wypozyczalnia.Repositories;

import com.mycompany.wypozyczalnia.IRepositories.IEquipmentRepository;
import com.mycompany.wypozyczalnia.Models.Equipment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipmentRepository implements IEquipmentRepository {
    private Connection _conn;

    public EquipmentRepository(Connection conn) throws SQLException {
        _conn = conn;
    }

    public void create(Equipment equipment) throws SQLException {
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

    public void update(Equipment equipment) throws SQLException {
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

    public List<Equipment> getAllEq() throws SQLException {
        List<Equipment> equipments = new ArrayList<Equipment>();
        try (Statement stmt = _conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM db.Sprzet");
            while (rs.next()) {
                Equipment equipment = new Equipment();
                equipment.ID_Sprzetu = rs.getInt("ID_Sprzetu");
                equipment.Nazwa = rs.getString("Nazwa");
                equipment.Typ = rs.getString("Typ");
                equipment.Dostepnosc = Boolean.parseBoolean(rs.getString("Dostępność"));
                equipments.add(equipment);
            }
        } catch (SQLException e) {
            throw e;
        }
        return equipments;
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

