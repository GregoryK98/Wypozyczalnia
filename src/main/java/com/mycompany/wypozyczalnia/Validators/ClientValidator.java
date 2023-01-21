package com.mycompany.wypozyczalnia.Validators;

import com.mycompany.wypozyczalnia.Models.Client;

public class ClientValidator {

    public boolean validate(Client client) {
        NumerTelefonuValidator numerTelefonuValidator = new NumerTelefonuValidator();
        if (client.Imie.isEmpty()
                || client.Nazwisko.isEmpty()
                || client.Adres.isEmpty()
                || !numerTelefonuValidator.validate(client.NumerTelefonu))
            return false;
        return true;
    }
}
