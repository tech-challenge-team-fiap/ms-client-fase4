package br.com.fiap.ms.client;

import br.com.fiap.ms.client.domain.exception.client.ClientNotFoundException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientNotFoundExceptionTest {

    @Test
    public void testClientNotFoundExceptionMessage() {
        String cpf = "12345678900";
        ClientNotFoundException exception = new ClientNotFoundException(cpf);

        String expectedMessage = "The client 12345678900 not found";
        assertEquals("Client not found", exception.getTittle()); // Assuming getTitle() method exists and retrieves the title.
        assertEquals(expectedMessage, exception.getMessage());
    }
}