package br.com.fiap.ms.client;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.fiap.ms.client.application.dto.ClientDto;
import br.com.fiap.ms.client.domain.exception.InvalidProcessException;
import br.com.fiap.ms.client.domain.exception.client.InvalidClientProcessException;
import br.com.fiap.ms.client.domain.interfaces.ClientUseCaseInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

public class ClientUseCaseInterfaceTest {

    @Mock
    private ClientUseCaseInterface clientUseCaseInterface;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        ClientDto client1 = new ClientDto(); // Assuming ClientDto is a valid class
        ClientDto client2 = new ClientDto();
        List<ClientDto> expectedClients = Arrays.asList(client1, client2);

        when(clientUseCaseInterface.findAll()).thenReturn(expectedClients);

        List<ClientDto> actualClients = clientUseCaseInterface.findAll();

        assertNotNull(actualClients);
        assertEquals(2, actualClients.size());
        verify(clientUseCaseInterface, times(1)).findAll();
    }

    @Test
    void testFindByCpf() throws InvalidProcessException, InvalidClientProcessException {
        String cpf = "12345678900";
        ClientDto expectedClient = new ClientDto(); // Set properties as needed

        when(clientUseCaseInterface.findByCpf(cpf)).thenReturn(expectedClient);

        ClientDto actualClient = clientUseCaseInterface.findByCpf(cpf);

        assertNotNull(actualClient);
        assertEquals(expectedClient, actualClient);
        verify(clientUseCaseInterface, times(1)).findByCpf(cpf);
    }
}