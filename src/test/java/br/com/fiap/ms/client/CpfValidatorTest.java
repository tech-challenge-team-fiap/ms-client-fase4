package br.com.fiap.ms.client;


import br.com.fiap.ms.client.domain.validation.CpfValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CpfValidatorTest {

    @Test
    public void testValidCpf() {
        // Example of a valid CPF (Brazilian individual taxpayer registry identification)
        String validCpf = "111.444.777-35";
        Assertions.assertTrue(CpfValidator.isValidCpf(validCpf), "The CPF should be valid");
    }

    @Test
    public void testInvalidCpfWithInvalidDigits() {
        // Example of an invalid CPF due to incorrect check digits
        String invalidCpf = "111.444.777-34";
        Assertions.assertFalse(CpfValidator.isValidCpf(invalidCpf), "The CPF should be invalid due to incorrect check digits");
    }

    @Test
    public void testInvalidCpfWithLetters() {
        // Example of an invalid CPF because it contains letters
        String invalidCpf = "111.444.777-3A";
        Assertions.assertFalse(CpfValidator.isValidCpf(invalidCpf), "The CPF should be invalid because it contains letters");
    }

    @Test
    public void testInvalidCpfWithSpecialCharacters() {
        // Example of an invalid CPF because it contains special characters
        String invalidCpf = "111$444$777$35";
        Assertions.assertFalse(CpfValidator.isValidCpf(invalidCpf), "The CPF should be invalid because it contains special characters");
    }

    @Test
    public void testInvalidCpfWithIncorrectLength() {
        // Example of an invalid CPF due to incorrect length
        String invalidCpf = "111.444.777-355";
        Assertions.assertFalse(CpfValidator.isValidCpf(invalidCpf), "The CPF should be invalid due to incorrect length");
    }
}