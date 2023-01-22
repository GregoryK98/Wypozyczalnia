package com.mycompany.wypozyczalnia.ConsoleProviders;


import com.mycompany.wypozyczalnia.IProvider.IEquipmentProvider;
import com.mycompany.wypozyczalnia.Models.Equipment;

import java.util.List;
import java.util.Scanner;

public class EquipmentConsoleProvider implements IEquipmentProvider {
        private Scanner _scanner;

        public EquipmentConsoleProvider(Scanner scanner) {
            _scanner = scanner;
        }

        public Equipment readEquipment(boolean readId) {
            Equipment equipment = new Equipment();
            if (readId) {
                System.out.print("Id: ");
                equipment.ID_Sprzetu = _scanner.nextInt();
            }
            System.out.print("Nazwa sprzętu: ");
            equipment.Nazwa = _scanner.next();
            System.out.print("Typ sprzętu: ");
            equipment.Typ = _scanner.next();
            System.out.print("Dostępność: ");
            equipment.Dostepnosc = Boolean.parseBoolean(_scanner.next());
            return equipment;
        }

            public void writeEquipment(List<Equipment> equipments) {
                for(Equipment equipment : equipments) {
                    System.out.println("ID: " + equipment.ID_Sprzetu
                            + ", Nazwa: " + equipment.Nazwa
                            + ", Typ: " + equipment.Typ
                            + ", Dostępność: " + equipment.Dostepnosc);
                }
            }
        }
