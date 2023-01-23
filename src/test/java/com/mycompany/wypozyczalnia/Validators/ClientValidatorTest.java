package com.mycompany.wypozyczalnia.Validators;

import com.mycompany.wypozyczalnia.Models.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ClientValidatorTest {

    @Test
    void client_valid_if_everything_is_ok() {
        Client client = new Client();
        client.Imie = "Quentin";
        client.Nazwisko = "Tarantino";
        client.Adres = "Hollywood";
        client.NumerTelefonu = "+48123456789";
        ClientValidator clientalidator = new ClientValidator();
        assertTrue(clientalidator.validate(client));
    }

    @Test
    void client_invalid_if_imie_is_empty() {
        Client client = new Client();
        client.Imie = "";
        client.Nazwisko = "Tarantino";
        client.Adres = "Hollywood";
        client.NumerTelefonu = "+48123456789";
        ClientValidator clientalidator = new ClientValidator();
        assertTrue(!clientalidator.validate(client));
    }

    @Test
    void client_invalid_if_nazwisko_is_empty() {
        Client client = new Client();
        client.Imie = "Quentin";
        client.Nazwisko = "";
        client.Adres = "Hollywood";
        client.NumerTelefonu = "+48123456789";
        ClientValidator clientalidator = new ClientValidator();
        assertTrue(!clientalidator.validate(client));
    }

    @Test
    void client_invalid_if_adres_is_empty() {
        Client client = new Client();
        client.Imie = "Quentin";
        client.Nazwisko = "Tarantino";
        client.Adres = "";
        client.NumerTelefonu = "+48123456789";
        ClientValidator clientalidator = new ClientValidator();
        assertTrue(!clientalidator.validate(client));
    }

    @Test
    void client_invalid_if_numer_is_empty() {
        Client client = new Client();
        client.Imie = "Quentin";
        client.Nazwisko = "Tarantino";
        client.Adres = "Hollywood";
        client.NumerTelefonu = "";
        ClientValidator clientalidator = new ClientValidator();
        assertTrue(!clientalidator.validate(client));
    }

    @Test
    void client_invalid_if_numer_is_invalid() {
        Client client = new Client();
        client.Imie = "Quentin";
        client.Nazwisko = "Tarantino";
        client.Adres = "Hollywood";
        client.NumerTelefonu = "+4578asdf45";
        ClientValidator clientalidator = new ClientValidator();
        assertTrue(!clientalidator.validate(client));
    }
}
