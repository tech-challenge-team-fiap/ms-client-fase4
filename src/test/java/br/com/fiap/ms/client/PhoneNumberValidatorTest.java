package br.com.fiap.ms.client;

import br.com.fiap.ms.client.domain.validation.PhoneNumberValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PhoneNumberValidatorTest {

    @Test
    public void testValidPhoneNumberWithNine() {
        assertTrue(PhoneNumberValidator.isValidPhoneNumber("+5511999999999"));
    }

    @Test
    public void testValidPhoneNumberWithoutNine() {
        assertTrue(PhoneNumberValidator.isValidPhoneNumber("+551198765432"));
    }

    @Test
    public void testInvalidPhoneNumberShort() {
        assertFalse(PhoneNumberValidator.isValidPhoneNumber("+55119"));
    }

    @Test
    public void testInvalidPhoneNumberLong() {
        assertFalse(PhoneNumberValidator.isValidPhoneNumber("+55119999999999"));
    }

    @Test
    public void testInvalidPhoneNumberWithLetters() {
        assertFalse(PhoneNumberValidator.isValidPhoneNumber("+55119ABCDEF"));
    }

    @Test
    public void testInvalidPhoneNumberWithoutPlusSign() {
        assertFalse(PhoneNumberValidator.isValidPhoneNumber("5511987654321"));
    }

    @Test
    public void testInvalidPhoneNumberWithExtraCharacters() {
        assertFalse(PhoneNumberValidator.isValidPhoneNumber("+55-11-987654321"));
    }

    @Test
    public void testInvalidCountryCode() {
        assertFalse(PhoneNumberValidator.isValidPhoneNumber("+4411987654321"));
    }
}