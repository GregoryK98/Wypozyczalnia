package com.mycompany.wypozyczalnia.IProvider;

import com.mycompany.wypozyczalnia.Models.Client;

import java.util.List;

public interface IClientProvider {
    public Client readClient(boolean readId);
    public void writeClients(List<Client> clients);
}
