package br.com.fiap.ms.client;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.fiap.ms.client.domain.exception.BaseException;
import org.junit.jupiter.api.Test;

public class BaseExceptionTests {

    @Test
    public void testExceptionMessage() {
        String expectedMessage = "This is a test message";
        BaseException exception = new BaseException(expectedMessage);

        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage, "The message should match the expected message");
    }
}
