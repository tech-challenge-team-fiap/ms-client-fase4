package br.com.fiap.ms.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.com.fiap.ms.client.domain.validation.EmailValidator;
import org.junit.jupiter.api.Test;

public class EmailValidatorTest {

    @Test
    public void testValidEmail() {
        assertTrue(EmailValidator.isValidEmail("example@example.com"));
        assertTrue(EmailValidator.isValidEmail("user.name+tag+sorting@example.com"));
        assertTrue(EmailValidator.isValidEmail("my.email@example.co.uk"));
        assertTrue(EmailValidator.isValidEmail("user_name@example.com"));
    }

    @Test
    public void testInvalidEmail() {
        assertFalse(EmailValidator.isValidEmail("plainaddress"));
        assertFalse(EmailValidator.isValidEmail("@no-local-part.com"));
        assertFalse(EmailValidator.isValidEmail("Outlook Contact <outlook_Contact@domain.com>"));
        assertFalse(EmailValidator.isValidEmail("no-at-sign"));
        assertTrue(EmailValidator.isValidEmail("no-tld@domain"));
        assertFalse(EmailValidator.isValidEmail("semicolon;in@domain.com"));
    }

    @Test
    public void testEmailWithSpaces() {
        assertFalse(EmailValidator.isValidEmail(" example@example.com"));
        assertTrue(EmailValidator.isValidEmail("example@example.com "));
        assertFalse(EmailValidator.isValidEmail(" example@example.com "));
    }

    @Test
    public void testNullEmail() {
        assertFalse(EmailValidator.isValidEmail(null));
    }
}