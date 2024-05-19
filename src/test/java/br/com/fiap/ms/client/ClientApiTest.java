package br.com.fiap.ms.client.external.api;

import br.com.fiap.ms.client.adapter.controller.ClientController;
import br.com.fiap.ms.client.application.dto.ClientDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.zalando.problem.ThrowableProblem;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.junit.jupiter.api.Assertions.*;

class ClientApiTest {

    @Mock
    private ClientController clientController;

    @InjectMocks
    private ClientApi clientApi;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void healthCheckApi_ShouldReturnApiUp() {
        ResponseEntity<?> response = clientApi.healthCheckApi();
        assertEquals("Api UP", response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void register_ShouldInvokeController() throws ThrowableProblem {
        ClientDto clientDto = new ClientDto();
        when(clientController.register(clientDto)).thenReturn(ResponseEntity.ok().build());

        ResponseEntity<?> response = clientApi.register(clientDto);

        verify(clientController, times(1)).register(clientDto);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void edit_ShouldInvokeController() {
        ClientDto clientDto = new ClientDto();
        when(clientController.edit(clientDto)).thenReturn(ResponseEntity.ok().build());

        ResponseEntity<?> response = clientApi.edit(clientDto);

        verify(clientController, times(1)).edit(clientDto);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void remove_ShouldInvokeController() {
        String cpf = "12345678900";
        when(clientController.remove(cpf)).thenReturn(ResponseEntity.ok().build());

        ResponseEntity<?> response = clientApi.remove(cpf);

        verify(clientController, times(1)).remove(cpf);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void findAll_ShouldInvokeController() {
        when(clientController.findAll()).thenReturn(ResponseEntity.ok().build());

        ResponseEntity<?> response = clientApi.findAll();

        verify(clientController, times(1)).findAll();
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void findByDocument_ShouldInvokeController() {
        String cpf = "12345678900";
        when(clientController.findByDocument(cpf)).thenReturn(ResponseEntity.ok().build());

        ResponseEntity<?> response = clientApi.findByDocument(cpf);

        verify(clientController, times(1)).findByDocument(cpf);
        assertEquals(200, response.getStatusCodeValue());
    }

}