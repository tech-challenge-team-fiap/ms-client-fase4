package br.com.fiap.ms.client;

import br.com.fiap.ms.client.application.dto.ClientDto;
import br.com.fiap.ms.client.domain.exception.client.ClientNotFoundException;
import br.com.fiap.ms.client.domain.exception.client.InvalidClientProcessException;
import br.com.fiap.ms.client.domain.model.Client;
import br.com.fiap.ms.client.external.infrastructure.entities.ClientDB;
import br.com.fiap.ms.client.external.infrastructure.gateway.ClientGatewayImpl;
import br.com.fiap.ms.client.external.infrastructure.repositories.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClientGatewayImplTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientGatewayImpl clientGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerClientSuccessfully() throws InvalidClientProcessException {
        // Arrange
        Client client = new Client(); // Populate the client object as needed
        ClientDB clientDB = new ClientDB(); // Populate the clientDB object as needed
        when(clientRepository.save(any(ClientDB.class))).thenReturn(clientDB);

        // Act
        ClientDto result = clientGateway.register(client);

        // Assert
        assertNotNull(result);
        verify(clientRepository, times(1)).save(any(ClientDB.class));
    }

    @Test
    public void testUpdateClient() throws InvalidClientProcessException {
        // Dado
        ClientDto clientDto = new ClientDto("1", "John Doe", "123456789", "john@example.com", "1234567890");

        ClientDB existingClientDB = new ClientDB("1", "Old Name", "123456789", "old@example.com", "9876543210", "2024-05-18T12:00:00");

        when(clientRepository.findByCpf(clientDto.getCpf())).thenReturn(Optional.of(existingClientDB));

        // Quando
        clientGateway.update(clientDto);

        // Então
        verify(clientRepository, times(1)).findByCpf(clientDto.getCpf());
        verify(clientRepository, times(1)).save(any(ClientDB.class));
    }

    @Test
    public void testUpdateNonExistentClient() {
        // Dado
        ClientDto clientDto = new ClientDto("1", "John Doe", "123456789", "john@example.com", "1234567890");

        when(clientRepository.findByCpf(clientDto.getCpf())).thenReturn(Optional.empty());

        // Quando & Então
        assertThrows(ClientNotFoundException.class, () -> clientGateway.update(clientDto));
        verify(clientRepository, times(1)).findByCpf(clientDto.getCpf());
        verify(clientRepository, never()).save(any(ClientDB.class));
    }

    @Test
    public void testDeleteExistingClient() throws ClientNotFoundException {
        // Given
        String cpf = "123456789";
        ClientDB existingClientDB = new ClientDB("1", "Old Name", "123456789", "old@example.com", "9876543210", "2024-05-18T12:00:00");
        when(clientRepository.findByCpf(cpf)).thenReturn(Optional.of(existingClientDB));

        // When
        ClientDto result = clientGateway.delete(cpf);

        // Then
        assertNotNull(result);
        assertEquals("1", result.getId()); // Assuming ID is "1"
        verify(clientRepository, times(1)).findByCpf(cpf);
        verify(clientRepository, times(1)).delete(existingClientDB);
    }

    @Test
    public void testDeleteNonExistentClient() {
        // Given
        String cpf = "123456789";
        when(clientRepository.findByCpf(cpf)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(ClientNotFoundException.class, () -> clientGateway.delete(cpf));
        verify(clientRepository, times(1)).findByCpf(cpf);
        verify(clientRepository, never()).delete(any(ClientDB.class));
    }

    @Test
    void findByCpfThrowsClientNotFoundException() {
        // Arrange
        String cpf = "12345678900";
        when(clientRepository.findByCpf(cpf)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ClientNotFoundException.class, () -> clientGateway.findByDocument(cpf));
    }

    // Add more tests for listFindAll, findByCpf, and findByDocument as needed.
}