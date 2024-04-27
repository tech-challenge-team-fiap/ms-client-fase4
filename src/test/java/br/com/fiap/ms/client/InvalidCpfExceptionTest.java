package br.com.fiap.ms.client;

import br.com.fiap.ms.client.domain.exception.client.InvalidCpfException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InvalidCpfExceptionTest {

    @Test
    void testInvalidCpfExceptionMessage() {
        String invalidCpf = "12345678900";
        InvalidCpfException exception = new InvalidCpfException(invalidCpf);

        // Assuming InvalidClientProcessException provides a way to retrieve the message
        // and that it concatenates the title and message in some form.
        String expectedMessage = "The CPF 12345678900 is a invalid number";
        assertEquals(expectedMessage, exception.getMessage());
    }
}
