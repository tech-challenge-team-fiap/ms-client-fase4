package br.com.fiap.ms.client;

import br.com.fiap.ms.client.application.dto.ClientFormDto;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientFormDtoTest {

    @Test
    public void testClientFormDto() {
        // Prepare data
        String name = "John Doe";
        String cpf = "123.456.789-00";
        String email = "johndoe@example.com";
        String phone = "1234567890";
        LocalDateTime dateRegister = LocalDateTime.now();

        // Create an instance of ClientFormDto using the all args constructor
        ClientFormDto clientFormDto = new ClientFormDto(name, cpf, email, phone, dateRegister);

        // Assert that the getters return the expected values
        assertEquals(name, clientFormDto.getName());
        assertEquals(cpf, clientFormDto.getCpf());
        assertEquals(email, clientFormDto.getEmail());
        assertEquals(phone, clientFormDto.getPhone());
        assertEquals(dateRegister, clientFormDto.getDateRegister());

        // Test setters
        String newName = "Jane Doe";
        clientFormDto.setName(newName);
        assertEquals(newName, clientFormDto.getName());
    }
}
