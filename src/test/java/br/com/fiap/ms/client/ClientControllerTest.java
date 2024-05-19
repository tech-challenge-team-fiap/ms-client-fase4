package br.com.fiap.ms.client.adapter.controller;

import br.com.fiap.ms.client.application.dto.ClientDto;
import br.com.fiap.ms.client.domain.exception.InvalidProcessException;
import br.com.fiap.ms.client.domain.exception.client.InvalidClientProcessException;
import br.com.fiap.ms.client.domain.interfaces.ClientUseCaseInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.zalando.problem.ThrowableProblem;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {

    @Mock
    private ClientUseCaseInterface clientUseCaseInterface;
    private ClientController subject;

    @BeforeEach
    void setup() {
        subject = new ClientController(clientUseCaseInterface);
    }

    @Test
    void shouldRegister() throws InvalidClientProcessException {
        ClientDto clientDto = new ClientDto("123");
        Mockito.when(clientUseCaseInterface.register(clientDto)).thenReturn(clientDto);

        var response = subject.register(clientDto);
        assertNotNull(response);
    }

    @Test
    void shouldEdit() {
        ClientDto clientDto = new ClientDto("123");

        var response = subject.register(clientDto);
        assertNotNull(response);
    }

    @Test
    void shouldRemove() {
        var response = subject.remove("11111111111");
        assertNotNull(response);
    }

    @Test
    void shouldFindAll() {
        var response = subject.findAll();
        assertNotNull(response);
    }

    @Test
    void shouldFindByDocument() {
        var response = subject.findByDocument("11111111111");
        assertNotNull(response);
    }

}
