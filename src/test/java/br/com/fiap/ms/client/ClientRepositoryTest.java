package br.com.fiap.ms.client;

import br.com.fiap.ms.client.application.dto.ClientDto;
import br.com.fiap.ms.client.external.infrastructure.entities.ClientDB;
import br.com.fiap.ms.client.external.infrastructure.repositories.ClientRepository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ClientRepositoryTest {

    @Mock
    private DynamoDBMapper dynamoDBMapper;

    private ClientRepository clientRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        clientRepository = new ClientRepository(dynamoDBMapper);
    }

    @Test
    void save() {
        // Given
        ClientDB client = new ClientDB();
        doNothing().when(dynamoDBMapper).save(client);

        // When
        ClientDB savedClient = clientRepository.save(client);

        // Then
        assertNotNull(savedClient);
        verify(dynamoDBMapper, times(1)).save(client);
    }

    @Test
    void findByCpf() {
        String cpf = "12345678900";
        ClientDB client = new ClientDB();
        when(dynamoDBMapper.load(ClientDB.class, cpf)).thenReturn(client);
        Optional<ClientDB> foundClient = clientRepository.findByCpf(cpf);
        assertTrue(foundClient.isPresent());
        assertEquals(client, foundClient.get());
    }

    @Test
    void delete() {
        ClientDB client = new ClientDB();
        doNothing().when(dynamoDBMapper).delete(client);
        clientRepository.delete(client);
        verify(dynamoDBMapper, times(1)).delete(client);
    }
}