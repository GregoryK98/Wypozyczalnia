package com.mycompany.wypozyczalnia.IRepositories;

import com.mycompany.wypozyczalnia.Models.Equipment;

import java.sql.SQLException;
import java.util.List;

public interface IEquipmentRepository {

    public void createEq(Equipment equipment) throws SQLException;

    public void updateEq(Equipment equipment) throws SQLException;

    public List<Equipment> getAllEq() throws SQLException;
}
