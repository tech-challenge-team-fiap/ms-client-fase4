package br.com.fiap.ms.client;

import br.com.fiap.ms.client.adapter.controller.ClientController;
import br.com.fiap.ms.client.application.dto.ClientDto;
import br.com.fiap.ms.client.domain.exception.InvalidProcessException;
import br.com.fiap.ms.client.domain.exception.client.InvalidClientProcessException;
import br.com.fiap.ms.client.domain.interfaces.ClientUseCaseInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.zalando.problem.Problem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {

    @Mock
    private ClientUseCaseInterface clientUseCaseInterface;
    private ClientController controller;

    @BeforeEach
    void setup() {
        controller = new ClientController(clientUseCaseInterface);
    }

    @Test
    void shouldRegister() throws InvalidClientProcessException {
        ClientDto clientDto = new ClientDto("123");
        when(clientUseCaseInterface.register(clientDto)).thenReturn(clientDto);

        var response = controller.register(clientDto);
        assertNotNull(response);
    }

    @Test
    void shouldEdit() {
        ClientDto clientDto = new ClientDto("123");

        var response = controller.edit(clientDto);
        assertNotNull(response);
    }

    @Test
    void shouldThrowOnEdit() throws InvalidClientProcessException {
        ClientDto clientDto = new ClientDto("123");

        when(clientUseCaseInterface.edit(clientDto)).thenThrow(InvalidClientProcessException.class);

        var response = controller.edit(clientDto);
        assertNotNull(response);
    }

    @Test
    void shouldRemove() {
        var response = controller.remove("11111111111");
        assertNotNull(response);
    }

    @Test
    void shouldFindAll() {
        var response = controller.findAll();
        assertNotNull(response);
    }

    @Test
    void shouldFindByDocument() {
        var response = controller.findByDocument("11111111111");
        assertNotNull(response);
    }

    @Test
    public void testEditClientWithValidData() {
        // Given
        ClientDto clientDto = new ClientDto("1", "John Doe", "123456789", "john@example.com", "1234567890");

        // When
        ResponseEntity<?> responseEntity = controller.edit(clientDto);

        // Then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
