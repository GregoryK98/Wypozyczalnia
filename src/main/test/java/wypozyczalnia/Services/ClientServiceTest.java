package com.mycompany.wypozyczalnia.Services;

import com.mycompany.wypozyczalnia.IProvider.IClientProvider;
import com.mycompany.wypozyczalnia.Models.Client;
import com.mycompany.wypozyczalnia.Repositories.ClientRepository;
import com.mycompany.wypozyczalnia.Validators.ClientValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
    @Mock
    private IClientProvider clientProviderMock;
    @Mock
    private ClientRepository clientRepositoryMock;

    @Test
    void add_return_client_added_if_everything_ok() throws SQLException {
        Client client = new Client();
        client.Imie = "Quentin";
        client.Nazwisko = "Tarantino";
        client.Adres = "Hollywood";
        client.NumerTelefonu = "+48123456789";

        assertNotNull(clientRepositoryMock);
        assertNotNull(clientProviderMock);
        when(clientProviderMock.readClient(false)).thenReturn(client);

        ClientService clientService = new ClientService(clientProviderMock, clientRepositoryMock);
        assertTrue(clientService.addClient().equals("Client added.\n"));
    }

    @Test
    void add_return_client_invalid_if_invalid_client_ok() throws SQLException {
        Client client = new Client();
        client.Imie = "Quentin";
        client.Nazwisko = "";
        client.Adres = "Hollywood";
        client.NumerTelefonu = "+48123456789";

        assertNotNull(clientRepositoryMock);
        assertNotNull(clientProviderMock);
        when(clientProviderMock.readClient(false)).thenReturn(client);

        ClientService clientService = new ClientService(clientProviderMock, clientRepositoryMock);
        assertTrue(clientService.addClient().equals("Client cannot be added because is not valid.\n"));
    }
}
