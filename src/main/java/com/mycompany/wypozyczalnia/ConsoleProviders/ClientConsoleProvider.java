package com.mycompany.wypozyczalnia.ConsoleProviders;

import com.mycompany.wypozyczalnia.IProvider.IClientProvider;
import com.mycompany.wypozyczalnia.Models.Client;

import java.util.Scanner;

public class ClientConsoleProvider implements IClientProvider {
    private Scanner _scanner;

    public ClientConsoleProvider(Scanner scanner) {
        _scanner = scanner;
    }

    public Client readClient(boolean readId) {
        Client client = new Client();
        if (readId) {
            System.out.print("Id: ");
            client.ID = _scanner.nextInt();
        }
        System.out.print("Imie: ");
        client.Imie = _scanner.next();
        System.out.print("Nazwisko: ");
        client.Nazwisko = _scanner.next();
        System.out.print("Adres: ");
        client.Adres = _scanner.next();
        System.out.print("Numer telefonu: ");
        client.NumerTelefonu = _scanner.next();

        return client;
    }
}
