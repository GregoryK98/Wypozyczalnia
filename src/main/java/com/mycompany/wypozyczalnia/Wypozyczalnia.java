package com.mycompany.wypozyczalnia;

import com.mycompany.wypozyczalnia.ConsoleProviders.ClientConsoleProvider;
import com.mycompany.wypozyczalnia.ConsoleProviders.EquipmentConsoleProvider;
import com.mycompany.wypozyczalnia.ConsoleProviders.RentalConsoleProvider;
import com.mycompany.wypozyczalnia.IProvider.IEquipmentProvider;
import com.mycompany.wypozyczalnia.IRepositories.IEquipmentRepository;
import com.mycompany.wypozyczalnia.Models.Rental;
import com.mycompany.wypozyczalnia.Repositories.ClientRepository;
import com.mycompany.wypozyczalnia.Repositories.EquipmentRepository;
import com.mycompany.wypozyczalnia.Repositories.RentalRepository;
import com.mycompany.wypozyczalnia.Services.ClientService;
import com.mycompany.wypozyczalnia.Services.EquipmentService;
import com.mycompany.wypozyczalnia.Services.RentalService;

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
            EquipmentConsoleProvider equipmentConsoleProvider = new EquipmentConsoleProvider((scanner));
            EquipmentRepository equipmentRepository = new EquipmentRepository(conn);
            EquipmentService equipmentService = new EquipmentService(equipmentConsoleProvider, equipmentRepository); // IEquipmentRepository nie wiem dlaczego każe to tu dodać
            RentalConsoleProvider rentalConsoleProvider = new RentalConsoleProvider(scanner);
            RentalRepository rentalRepository = new RentalRepository(conn);
            RentalService rentalService = new RentalService(rentalConsoleProvider, rentalRepository);

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
                        showEquipment(equipmentService);
                        break;
                    case 3:
                        showRental(rentalService);
                        break;
                    case 4:
                        addClient(clientService);
                        break;
                    case 5:
                        addEquipment(equipmentService);
                        break;
                    case 6:
                        addRental(rentalService);
                        break;
                    case 7:
                        updateClient(clientService);
                        break;
                    case 8:
                        updateEquipment(equipmentService);
                        break;
                    case 9:
                        updateRental(rentalService);
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

    private static void showEquipment(EquipmentService equipmentService) {
        System.out.println(equipmentService.showAllEq());
    }
    private static void showRental(RentalService rentalService) {
        System.out.println(rentalService.showAll());
    }
    private static void addEquipment(EquipmentService equipmentService) {
        System.out.println(equipmentService.addEquipment());
    }
    private static void addClient(ClientService clientService) {
        System.out.println(clientService.addClient());
    }

    private static void addRental(RentalService rentalService) {
        System.out.println(rentalService.addRental());
    }

    private static void updateClient(ClientService clientService) {
        System.out.println(clientService.updateClient());
    }

    private static void updateEquipment(EquipmentService equipmentService) {
        System.out.println(equipmentService.updateEquipment());
    }
    private static void updateRental(RentalService rentalService) {
        System.out.println(rentalService.updateRental());
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