package com.mycompany.wypozyczalnia.IRepositories;

import com.mycompany.wypozyczalnia.Models.Equipment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface IEquipmentRepository {

    public void create(Equipment equipment) throws SQLException;

    public void update(Equipment equipment) throws SQLException;

    public List<Equipment> getAll() throws SQLException;
}
