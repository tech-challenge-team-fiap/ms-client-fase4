package br.com.fiap.ms.client;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.fiap.ms.client.adapter.gateways.ClientGatewayInterface;
import br.com.fiap.ms.client.application.dto.ClientDto;
import br.com.fiap.ms.client.application.usecases.ClientUseCaseImpl;
import br.com.fiap.ms.client.domain.exception.InvalidProcessException;
import br.com.fiap.ms.client.domain.exception.client.InvalidClientProcessException;
import br.com.fiap.ms.client.domain.interfaces.ClientUseCaseInterface;
import br.com.fiap.ms.client.external.infrastructure.gateway.ClientGatewayImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ClientUseCaseInterfaceTest {

    @Mock
    ClientGatewayImpl clientGateway;

    private ClientUseCaseInterface clientUseCaseInterface;

    @BeforeEach
    void setUp() {
        clientUseCaseInterface = new ClientUseCaseImpl(clientGateway);
//        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        ClientDto client1 = new ClientDto("123"); // Assuming ClientDto is a valid class
        ClientDto client2 = new ClientDto("321");
        List<ClientDto> expectedClients = Arrays.asList(client1, client2);

        when(clientUseCaseInterface.findAll()).thenReturn(expectedClients);

        List<ClientDto> actualClients = clientUseCaseInterface.findAll();

        assertNotNull(actualClients);
        assertEquals(2, actualClients.size());
//        verify(clientUseCaseInterface, times(1)).findAll();
    }

    @Test
    void testFindByCpf() throws InvalidProcessException, InvalidClientProcessException {
        String cpf = "12345678900";
        ClientDto expectedClient = new ClientDto(); // Set properties as needed

        when(clientUseCaseInterface.findByCpf(cpf)).thenReturn(expectedClient);

        ClientDto actualClient = clientUseCaseInterface.findByCpf(cpf);

        assertNotNull(actualClient);
        assertEquals(expectedClient, actualClient);
//        verify(clientUseCaseInterface, times(1)).findByCpf(cpf);
    }
}
