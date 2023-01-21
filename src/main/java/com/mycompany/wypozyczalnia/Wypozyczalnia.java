package com.mycompany.wypozyczalnia;

import com.mycompany.wypozyczalnia.ConsoleProviders.ClientConsoleProvider;
import com.mycompany.wypozyczalnia.Repositories.ClientRepository;
import com.mycompany.wypozyczalnia.Services.ClientService;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Wypozyczalnia {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        String url = "jdbc:mysql://localhost:3306/db";
        String username = "root";
        String password = "root";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Connecting database...");
        boolean exit = false;

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");

            ClientConsoleProvider clientConsoleProvider = new ClientConsoleProvider(scanner);
            ClientRepository clientRepository = new ClientRepository(conn);
            ClientService clientService = new ClientService(clientConsoleProvider, clientRepository);

            while (!exit) {
                System.out.println("");
                System.out.println("  Element  | Show | Add | Update | Delete");
                System.out.println(" Client    |  1   |  4  |   7    |   10  ");
                System.out.println(" Equipment |  2   |  5  |   8    |   11  ");
                System.out.println(" Rental    |  3   |  6  |   9    |   12  ");
                System.out.print("Press 13 to Exit or choose an option: ");
                int option = scanner.nextInt();
                System.out.println("");

                switch (option) {
                    case 1:
                        showClients(clientService);
                        break;
                    case 2:
                        showEquipment(conn);
                        break;
                    case 3:
                        showRentals(conn);
                        break;
                    case 4:
                        addClient(clientService);
                        break;
                    case 5:
                        addEquipment(conn, scanner);
                        break;
                    case 6:
                        addRental(conn, scanner);
                        break;
                    case 7:
                        updateClient(clientService);
                        break;
                    case 8:
                        updateEquipment(conn, scanner);
                        break;
                    case 9:
                        updateRental(conn, scanner);
                        break;
                    case 10:
                        deleteClient(conn, scanner);
                        break;
                    case 11:
                        deleteEquipment(conn, scanner);
                        break;
                    case 12:
                        deleteRental(conn, scanner);
                        break;
                    case 13:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid option.");
                        break;
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    private static void showClients(ClientService clientService) {
        System.out.println(clientService.showAll());
    }

    private static void showEquipment(Connection conn) {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM Sprzet");
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

    private static void showRentals(Connection conn) {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM Wypozyczenia");
            while (rs.next()) {
                int id = rs.getInt("ID_wypozyczenia");
                int id_klienta = rs.getInt("ID_klienta");
                int id_sprzetu = rs.getInt("ID_sprzetu");
                Date data_wypozyczenia = rs.getDate("Data_wypozyczenia");
                Date data_zwrotu = rs.getDate("Data_zwrotu");
                float koszt = rs.getFloat("Koszt");
                System.out.println("ID: " + id + ", ID Klienta: " + id_klienta + ", ID Sprzetu: " + id_sprzetu + ", Data wypozyczenia: " + data_wypozyczenia + ", Data zwrotu: " + data_zwrotu + ", Koszt: " + koszt);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while executing the query: " + e.getMessage());
        }
    }

    private static void addEquipment(Connection conn, Scanner scanner) {
        System.out.print("Nazwa: ");
        String nazwa = scanner.next();
        System.out.print("Typ: ");
        String typ = scanner.next();
        System.out.print("Dostepnosc (1-tak/0-nie): ");
        boolean dostepnosc = scanner.nextInt() == 1;
        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO Sprzet (Nazwa, Typ, Dostepnosc) VALUES (?, ?, ?)")) {
            stmt.setString(1, nazwa);
            stmt.setString(2, typ);
            stmt.setBoolean(3, dostepnosc);
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) affected.");
        } catch (SQLException e) {
            System.out.println("An error occurred while executing the query: " + e.getMessage());
        }
    }

    private static void addClient(ClientService clientService) {
        System.out.println(clientService.addClient());
    }

    private static void addRental(Connection conn, Scanner scanner) {
        System.out.print("ID Klienta: ");
        int id_klienta = scanner.nextInt();
        System.out.print("ID Sprzetu: ");
        int id_sprzetu = scanner.nextInt();
        System.out.print("Data wypozyczenia (yyyy-mm-dd): ");
        String data_wypozyczenia = scanner.next();
        System.out.print("Data zwrotu (yyyy-mm-dd): ");
        String data_zwrotu = scanner.next();
        System.out.print("Koszt: ");
        float koszt = scanner.nextFloat();
        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO Wypozyczenia (ID_klienta, ID_sprzetu, Data_wypozyczenia, Data_zwrotu, Koszt) VALUES (?, ?, ?, ?, ?)")) {
            stmt.setInt(1, id_klienta);
            stmt.setInt(2, id_sprzetu);
            stmt.setString(3, data_wypozyczenia);
            stmt.setString(4, data_zwrotu);
            stmt.setFloat(5, koszt);
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) affected.");
            // Update equipment availability
            try (PreparedStatement stmt2 = conn.prepareStatement("UPDATE Sprzet SET Dostepnosc = false WHERE ID_sprzetu = ?")) {
                stmt2.setInt(1, id_sprzetu);
                stmt2.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while executing the query: " + e.getMessage());
        }
    }

    private static void updateClient(ClientService clientService) {
        System.out.println(clientService.updateClient());
    }

    private static void updateEquipment(Connection conn, Scanner scanner) {
        System.out.print("ID Sprzetu: ");
        int id_sprzetu = scanner.nextInt();
        System.out.print("New Nazwa: ");
        String nazwa = scanner.next();
        System.out.print("New Typ: ");
        String typ = scanner.next();
        System.out.print("New Dostepnosc (1-tak/0-nie): ");
        boolean dostepnosc = scanner.nextInt() == 1;
        try (PreparedStatement stmt = conn.prepareStatement("UPDATE Sprzet SET Nazwa = ?, Typ = ?, Dostepnosc = ? WHERE ID_sprzetu = ?")) {
            stmt.setString(1, nazwa);
            stmt.setString(2, typ);
            stmt.setBoolean(3, dostepnosc);
            stmt.setInt(4, id_sprzetu);
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) affected.");
        } catch (SQLException e) {
            System.out.println("An error occurred while executing the query: " + e.getMessage());
        }
    }

    private static void updateRental(Connection conn, Scanner scanner) {
        System.out.print("ID Wypozyczenia: ");
        int id_wypozyczenia = scanner.nextInt();
        System.out.print("New ID Klienta: ");
        int id_klienta = scanner.nextInt();
        System.out.print("New ID Sprzetu: ");
        int id_sprzetu = scanner.nextInt();
        System.out.print("New Data wypozyczenia (yyyy-mm-dd): ");
        String data_wypozyczenia = scanner.next();
        System.out.print("New Data zwrotu (yyyy-mm-dd): ");
        String data_zwrotu = scanner.next();
        System.out.print("New Koszt: ");
        float koszt = scanner.nextFloat();
        try (PreparedStatement stmt = conn.prepareStatement("UPDATE Wypozyczenia SET ID_klienta = ?, ID_sprzetu = ?, Data_wypozyczenia = ?, Data_zwrotu = ?, Koszt = ? WHERE ID_wypozyczenia = ?")) {
            stmt.setInt(1, id_klienta);
            stmt.setInt(2, id_sprzetu);
            stmt.setString(3, data_wypozyczenia);
            stmt.setString(4, data_zwrotu);
            stmt.setFloat(5, koszt);
            stmt.setInt(6, id_wypozyczenia);
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) affected.");
        } catch (SQLException e) {
            System.out.println("An error occurred while executing the query: " + e.getMessage());
        }
    }

    private static void deleteClient(Connection conn, Scanner scanner) {
        System.out.print("ID Klienta: ");
        int id_klienta = scanner.nextInt();
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM Klienci WHERE ID_klienta = ?")) {
            stmt.setInt(1, id_klienta);
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) affected.");
        } catch (SQLException e) {
            System.out.println("An error occurred while executing the query: " + e.getMessage());
        }
    }

    private static void deleteEquipment(Connection conn, Scanner scanner) {
        System.out.print("ID Sprzetu: ");
        int id_sprzetu = scanner.nextInt();
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM Sprzet WHERE ID_sprzetu = ?")) {
            stmt.setInt(1, id_sprzetu);
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) affected.");
        } catch (SQLException e) {
            System.out.println("An error occurred while executing the query: " + e.getMessage());
        }
    }

    private static void deleteRental(Connection conn, Scanner scanner) {
        System.out.print("ID Wypozyczenia: ");
        int id_wypozyczenia = scanner.nextInt();
        // Update equipment availability
        try (PreparedStatement stmt2 = conn.prepareStatement("UPDATE Sprzet SET Dostepnosc = true WHERE ID_sprzetu = (SELECT ID_sprzetu FROM Wypozyczenia WHERE ID_wypozyczenia = ?)")) {
            stmt2.setInt(1, id_wypozyczenia);
            stmt2.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An error occurred while executing the query: " + e.getMessage());
        }
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM Wypozyczenia WHERE ID_wypozyczenia = ?")) {
            stmt.setInt(1, id_wypozyczenia);
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) affected.");


        } catch (SQLException e) {
            System.out.println("An error occurred while executing the query: " + e.getMessage());
        }
    }

}