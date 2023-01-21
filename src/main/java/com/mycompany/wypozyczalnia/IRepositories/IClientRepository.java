package com.mycompany.wypozyczalnia.IRepositories;

import com.mycompany.wypozyczalnia.Models.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface IClientRepository {

    public void create(Client client) throws SQLException;

    public void update(Client client) throws SQLException;

    public List<Client> getAll() throws SQLException;
}
