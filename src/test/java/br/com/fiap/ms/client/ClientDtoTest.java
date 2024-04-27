package br.com.fiap.ms.client;

import br.com.fiap.ms.client.application.dto.ClientDto;
import br.com.fiap.ms.client.external.infrastructure.entities.ClientDB;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientDtoTest {

    @Test
    void testToDto() {
        // Prepare the data
        LocalDateTime testDate = LocalDateTime.now();
        ClientDB clientDB = new ClientDB();
        clientDB.setName("John Doe");
        clientDB.setCpf("123.456.789-00");
        clientDB.setEmail("johndoe@example.com");
        clientDB.setPhone("1234567890");
        clientDB.setDateRegister(String.valueOf(testDate));

        // Call the method under test
        ClientDto result = ClientDto.toDto(clientDB);

        // Assertions to verify the correct behavior
        assertEquals(clientDB.getName(), result.getName());
        assertEquals(clientDB.getCpf(), result.getCpf());
        assertEquals(clientDB.getEmail(), result.getEmail());
        assertEquals(clientDB.getPhone(), result.getPhone());
    }
}