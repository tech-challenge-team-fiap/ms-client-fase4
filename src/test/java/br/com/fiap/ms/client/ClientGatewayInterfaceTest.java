package br.com.fiap.ms.client;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import br.com.fiap.ms.client.adapter.gateways.ClientGatewayInterface;
import br.com.fiap.ms.client.application.dto.ClientDto;
import br.com.fiap.ms.client.domain.exception.client.ClientNotFoundException;
import br.com.fiap.ms.client.domain.exception.client.InvalidClientProcessException;
import br.com.fiap.ms.client.domain.model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class ClientGatewayInterfaceTest {

    private ClientGatewayInterface clientGateway;
    private Client client;
    private ClientDto clientDto;

    @BeforeEach
    void setUp() {
        // Mock the ClientGatewayInterface
        clientGateway = mock(ClientGatewayInterface.class);
        // Setup your Client and ClientDto objects here
        client = new Client(); // Assuming a constructor exists
        clientDto = new ClientDto(); // Assuming a constructor exists
    }

    @Test
    void testRegister() throws InvalidClientProcessException {
        when(clientGateway.register(client)).thenReturn(clientDto);
        ClientDto result = clientGateway.register(client);
        assertNotNull(result);
        verify(clientGateway).register(client);
    }

    @Test
    void testUpdate() throws InvalidClientProcessException {
        when(clientGateway.update(clientDto)).thenReturn(clientDto);
        ClientDto result = clientGateway.update(clientDto);
        assertNotNull(result);
        verify(clientGateway).update(clientDto);
    }

    @Test
    void testDelete() throws ClientNotFoundException {
        String cpf = "12345678900";
        when(clientGateway.delete(cpf)).thenReturn(clientDto);
        ClientDto result = clientGateway.delete(cpf);
        assertNotNull(result);
        verify(clientGateway).delete(cpf);
    }

    @Test
    void testListFindAll() {
        List<ClientDto> clients = List.of(clientDto); // Assuming at least one clientDto
        when(clientGateway.listFindAll()).thenReturn(clients);
        List<ClientDto> result = clientGateway.listFindAll();
        assertFalse(result.isEmpty());
        verify(clientGateway).listFindAll();
    }

    @Test
    void testFindByCpf() throws InvalidClientProcessException {
        String cpf = "12345678900";
        when(clientGateway.findByCpf(cpf)).thenReturn(clientDto);
        ClientDto result = clientGateway.findByCpf(cpf);
        assertNotNull(result);
        verify(clientGateway).findByCpf(cpf);
    }

    @Test
    void testFindByDocument() throws ClientNotFoundException {
        String document = "12345678900";
        when(clientGateway.findByDocument(document)).thenReturn(clientDto);
        ClientDto result = clientGateway.findByDocument(document);
        assertNotNull(result);
        verify(clientGateway).findByDocument(document);
    }
}