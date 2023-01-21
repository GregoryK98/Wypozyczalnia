package com.mycompany.wypozyczalnia.Services;

import com.mycompany.wypozyczalnia.ConsoleProviders.ClientConsoleProvider;
import com.mycompany.wypozyczalnia.IProvider.IClientProvider;
import com.mycompany.wypozyczalnia.Models.Client;
import com.mycompany.wypozyczalnia.Repositories.ClientRepository;

import java.util.List;

public class ClientService {
    private IClientProvider _clientProvider;
    private ClientRepository _clientRepository;

    public ClientService(IClientProvider clientProvider, ClientRepository clientRepository) {
        _clientProvider = clientProvider;
        _clientRepository = clientRepository;
    }

    public String addClient() {
        Client client = _clientProvider.readClient(false);
        try {
            _clientRepository.create(client);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Client added.\n";
    }

    public String updateClient() {
        Client client = _clientProvider.readClient(true);
        try {
            _clientRepository.update(client);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Client updated.\n";
    }

    public String showAll() {
        try {
            _clientProvider.writeClients(_clientRepository.getAll());
            return "";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
