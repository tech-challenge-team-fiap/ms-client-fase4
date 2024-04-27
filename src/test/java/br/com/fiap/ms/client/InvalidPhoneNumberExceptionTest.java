package br.com.fiap.ms.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.fiap.ms.client.domain.exception.client.InvalidPhoneNumberException;

public class InvalidPhoneNumberExceptionTest {

    @Test
    public void testInvalidPhoneNumberExceptionMessage() {
        // Define the invalid phone number to test
        String invalidPhoneNumber = "123456789";

        // Assert that the exception is thrown and the message is as expected
        InvalidPhoneNumberException exception = assertThrows(InvalidPhoneNumberException.class, () -> {
            throw new InvalidPhoneNumberException(invalidPhoneNumber);
        });

        // Verify that the message of the exception is formatted correctly
        assertEquals("The phone number 123456789 is invalid", exception.getMessage());
    }
}