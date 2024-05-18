package br.com.fiap.ms.client;

import br.com.fiap.ms.client.domain.exception.InvalidProcessException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.lang.reflect.Field;

public class InvalidProcessExceptionTest {

    @Test
    public void testExceptionProperties() throws NoSuchFieldException, IllegalAccessException {
        String expectedTitle = "Error Title";
        String expectedMessage = "This is an error message.";

        InvalidProcessException exception = new InvalidProcessException(expectedTitle, expectedMessage);

        // Since the fields are private and there are no getters, use reflection to access them
        Field titleField = InvalidProcessException.class.getDeclaredField("tittle"); // Note the misspelling in the original class
        titleField.setAccessible(true); // Make the field accessible
        String actualTitle = (String) titleField.get(exception);

        Field messageField = InvalidProcessException.class.getDeclaredField("message");
        messageField.setAccessible(true); // Make the field accessible
        String actualMessage = (String) messageField.get(exception);

        assertEquals(expectedTitle, actualTitle, "The title was not set correctly.");
        assertEquals(expectedMessage, actualMessage, "The message was not set correctly.");
    }
}