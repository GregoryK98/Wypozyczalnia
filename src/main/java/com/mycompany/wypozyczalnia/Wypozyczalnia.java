package com.mycompany.wypozyczalnia;

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
    Scanner scanner = new Scanner(System. in );

    System.out.println("Connecting database...");
    boolean exit = false;

    try (Connection conn = DriverManager.getConnection(url, username, password)) {
      System.out.println("Database connected!");

      while (!exit) {
        System.out.println("");
        System.out.println("1. Show clients");
        System.out.println("2. Show equipment");
        System.out.println("3. Show rentals");
        System.out.println("4. Add client");
        System.out.println("5. Add equipment");
        System.out.println("6. Add rental");
        System.out.println("7. Update client");
        System.out.println("8. Update equipment");
        System.out.println("9. Update rental");
        System.out.println("10. Delete client");
        System.out.println("11. Delete equipment");
        System.out.println("12. Delete rental");
        System.out.println("13. Exit");
        System.out.print("Choose an option: ");
        int option = scanner.nextInt();
        System.out.println("");

        switch (option) {
        case 1:
          showClients(conn);
          break;
        case 2:
          showEquipment(conn);
          break;
        case 3:
          showRentals(conn);
          break;
        case 4:
          addClient(conn, scanner);
          break;
        case 5:
          addEquipment(conn, scanner);
          break;
        case 6:
          addRental(conn, scanner);
          break;
        case 7:
          updateClient(conn, scanner);
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
    } catch(SQLException e) {
      throw new IllegalStateException("Cannot connect the database!", e);
    }

  }

  private static void showClients(Connection conn) {
    try (Statement stmt = conn.createStatement()) {
      ResultSet rs = stmt.executeQuery("SELECT * FROM Klienci");
      while (rs.next()) {
        int id = rs.getInt("ID_klienta");
        String imie = rs.getString("Imie");
        String nazwisko = rs.getString("Nazwisko");
        String adres = rs.getString("Adres");
        String numer_telefonu = rs.getString("Numer_telefonu");
        System.out.println("ID: " + id + ", Imie: " + imie + ", Nazwisko: " + nazwisko + ", Adres: " + adres + ", Numer telefonu: " + numer_telefonu);
      }
    } catch(SQLException e) {
      System.out.println("An error occurred while executing the query: " + e.getMessage());
    }
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
    } catch(SQLException e) {
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
    } catch(SQLException e) {
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
    } catch(SQLException e) {
      System.out.println("An error occurred while executing the query: " + e.getMessage());
    }
  }

  private static void addClient(Connection conn, Scanner scanner) {
    System.out.print("Imie: ");
    String imie = scanner.next();
    System.out.print("Nazwisko: ");
    String nazwisko = scanner.next();
    System.out.print("Adres: ");
    String adres = scanner.next();
    System.out.print("Numer telefonu: ");
    String numer_telefonu = scanner.next();
    try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO Klienci (Imie, Nazwisko, Adres, Numer_telefonu) VALUES (?, ?, ?, ?)")) {
      stmt.setString(1, imie);
      stmt.setString(2, nazwisko);
      stmt.setString(3, adres);
      stmt.setString(4, numer_telefonu);
      int rowsAffected = stmt.executeUpdate();
      System.out.println(rowsAffected + " row(s) affected.");
    } catch(SQLException e) {
      System.out.println("An error occurred while executing the query: " + e.getMessage());
    }
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
    } catch(SQLException e) {
      System.out.println("An error occurred while executing the query: " + e.getMessage());
    }
  }

  private static void updateClient(Connection conn, Scanner scanner) {
    System.out.print("ID Klienta: ");
    int id_klienta = scanner.nextInt();
    System.out.print("New Imie: ");
    String imie = scanner.next();
    System.out.print("New Nazwisko: ");
    String nazwisko = scanner.next();
    System.out.print("New Adres: ");
    String adres = scanner.next();
    System.out.print("New Numer telefonu: ");
    String numer_telefonu = scanner.next();
    try (PreparedStatement stmt = conn.prepareStatement("UPDATE Klienci SET Imie = ?, Nazwisko = ?, Adres = ?, Numer_telefonu = ? WHERE ID_klienta = ?")) {
      stmt.setString(1, imie);
      stmt.setString(2, nazwisko);
      stmt.setString(3, adres);
      stmt.setString(4, numer_telefonu);
      stmt.setInt(5, id_klienta);
      int rowsAffected = stmt.executeUpdate();
      System.out.println(rowsAffected + " row(s) affected.");
    } catch(SQLException e) {
      System.out.println("An error occurred while executing the query: " + e.getMessage());
    }
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
    } catch(SQLException e) {
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
    } catch(SQLException e) {
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
    } catch(SQLException e) {
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
    } catch(SQLException e) {
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
        } catch(SQLException e) {
      System.out.println("An error occurred while executing the query: " + e.getMessage());
    }
    try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM Wypozyczenia WHERE ID_wypozyczenia = ?")) {
      stmt.setInt(1, id_wypozyczenia);
      int rowsAffected = stmt.executeUpdate();
      System.out.println(rowsAffected + " row(s) affected.");
 
         
    } catch(SQLException e) {
      System.out.println("An error occurred while executing the query: " + e.getMessage());
    }
  }

}