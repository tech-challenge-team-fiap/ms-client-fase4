package br.com.fiap.ms.client;

import br.com.fiap.ms.client.domain.exception.client.InvalidEmailException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InvalidEmailExceptionTest {

    @Test
    void testInvalidEmailExceptionMessage() {
        // Given
        String invalidEmail = "invalid@example.com";
        String expectedMessage = "The Email invalid@example.com is invalid";

        // When
        InvalidEmailException exception = new InvalidEmailException(invalidEmail);

        // Then
        assertEquals("Invalid Email", exception.getTittle()); // Assuming getTitle() method exists
        assertEquals(expectedMessage, exception.getMessage()); // Assuming getMessage() is overridden or provided by InvalidClientProcessException
    }
}