package com.mycompany.wypozyczalnia.IRepositories;

import com.mycompany.wypozyczalnia.Models.Equipment;

import java.sql.SQLException;
import java.util.List;

public interface IEquipmentRepository {

    public void create(Equipment equipment) throws SQLException;

    public void update(Equipment equipment) throws SQLException;

    public List<Equipment> getAllEq() throws SQLException;
}
