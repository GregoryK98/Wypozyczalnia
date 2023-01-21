package com.mycompany.wypozyczalnia.IProvider;

import com.mycompany.wypozyczalnia.Models.Client;

public interface IClientProvider {
    public Client readClient(boolean readId);
}
