package br.com.fiap.ms.client;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.fiap.ms.client.domain.exception.client.ClientAlreadyExistsException;
import org.junit.jupiter.api.Test;

public class ClientAlreadyExistsExceptionTest {

    @Test
    public void testExceptionMessage() {
        // Given
        String cpf = "12345678900";
        String expectedMessage = "The client with CPF 12345678900 was already exists";

        // When
        ClientAlreadyExistsException exception = new ClientAlreadyExistsException(cpf);

        // Then
        assertEquals(expectedMessage, exception.getMessage());
    }
}