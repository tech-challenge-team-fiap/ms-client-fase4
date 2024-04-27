package br.com.fiap.ms.client;

import br.com.fiap.ms.client.domain.exception.client.InvalidClientProcessException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvalidClientProcessExceptionTest {

    @Test
    public void testExceptionMessage() {
        String expectedTitle = "Error Title";
        String expectedMessage = "This is a test error message.";
        InvalidClientProcessException exception = new InvalidClientProcessException(expectedTitle, expectedMessage);

        // Assuming InvalidProcessException or its parent class has getMessage() method that returns a formatted string containing the title and message
        String actualMessage = exception.getMessage();

        // Validate that the message is as expected. The exact assertion might need to be adjusted based on how InvalidProcessException formats the message.
        assertEquals(expectedMessage, actualMessage);
    }
}