package com.mycompany.wypozyczalnia.Services;

import com.mycompany.wypozyczalnia.IProvider.IClientProvider;
import com.mycompany.wypozyczalnia.IRepositories.IClientRepository;
import com.mycompany.wypozyczalnia.Models.Client;
import com.mycompany.wypozyczalnia.Repositories.ClientRepository;
import com.mycompany.wypozyczalnia.Validators.ClientValidator;

public class ClientService {
    private IClientProvider _clientProvider;
    private IClientRepository _clientRepository;

    public ClientService(IClientProvider clientProvider, IClientRepository clientRepository) {
        _clientProvider = clientProvider;
        _clientRepository = clientRepository;
    }

    public String addClient() {
        Client client = _clientProvider.readClient(false);
        ClientValidator clientValidator = new ClientValidator();
        if (!clientValidator.validate(client))
            return "Client cannot be added because is not valid.\n";
        try {
            _clientRepository.create(client);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Client added.\n";
    }

    public String updateClient() {
        Client client = _clientProvider.readClient(true);
        ClientValidator clientValidator = new ClientValidator();
        if (!clientValidator.validate(client))
            return "Client cannot be updated because new clients data are not not valid.\n";
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
