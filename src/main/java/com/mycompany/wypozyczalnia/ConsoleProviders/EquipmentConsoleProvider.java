package com.mycompany.wypozyczalnia.ConsoleProviders;


import com.mycompany.wypozyczalnia.IProvider.IEquipmentProvider;
import com.mycompany.wypozyczalnia.Models.Equipment;

import java.util.Scanner;

public class EquipmentConsoleProvider implements IEquipmentProvider {
        private Scanner _scanner;

        public EquipmentConsoleProvider(Scanner scanner) {
            _scanner = scanner;
        }

        public Equipment readEquipment() {
           Equipment equipment = new Equipment();

            System.out.print("Nazwa sprzętu: ");
            equipment.Nazwa = _scanner.next();
            System.out.print("Typ sprzętu: ");
            equipment.Typ = _scanner.next();
            System.out.print("Dostępność: ");
            equipment.Dostepnosc = Boolean.parseBoolean(_scanner.next());
            return equipment;
        }
    }

