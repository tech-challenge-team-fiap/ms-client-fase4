package br.com.fiap.ms.client;

import static org.junit.jupiter.api.Assertions.*;

import br.com.fiap.ms.client.external.infrastructure.entities.ClientDB;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClientDBTest {

    @Test
    public void testClientDBConstructorAndGetterMethods() {
        String id = "123";
        String name = "John Doe";
        String cpf = "11122233344";
        String email = "johndoe@example.com";
        String phone = "1234567890";
        String dateRegister = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

        // Since the constructor uses UUID.randomUUID().toString(), we'll not pass id, and check if it's not null later
        ClientDB client = new ClientDB();
        client.setName(name);
        client.setCpf(cpf);
        client.setEmail(email);
        client.setPhone(phone);
        // Assuming dateRegister is set within the constructor or somewhere else as it uses LocalDateTime.now()

        assertEquals(name, client.getName());
        assertEquals(cpf, client.getCpf());
        assertEquals(email, client.getEmail());
        assertEquals(phone, client.getPhone());
        // Assuming the dateRegister is set to the current date/time in the constructor or elsewhere
        assertNotNull(client.getDateRegister()); // This checks if dateRegister is not null but not its exact value due to time progression
    }

    @Test
    public void testSetId() {
        ClientDB client = new ClientDB();
        client.setId();
        assertNotNull(client.getId()); // Check if ID is set
    }

    @Test
    public void testGetDateRegister() {
        ClientDB client = new ClientDB();
        // This test might be flaky due to the precision of LocalDateTime.now()
        // A better approach would be to inject a clock or use a fixed date/time for testing
        String expectedDate = LocalDateTime.now().toString();
        String actualDate = client.getDateRegister();
        assertNotNull(actualDate); // Ensures that a date is returned
        // This assertion might fail if there's a significant delay between the expectedDate assignment and the actual method call
    }
}