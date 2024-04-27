package br.com.fiap.ms.client.application.dto;

import br.com.fiap.ms.client.external.infrastructure.entities.ClientDB;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDto {
    private String id;
    private String name;
    private String cpf;
    private String email;
    private String phone;
    private String dateRegister;

    public ClientDto(String id){
        this.id = id;
    }

    public ClientDto(String name, String cpf, String email, String phone, String dateRegister) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.phone = phone;
        this.dateRegister = dateRegister;
    }

    public static ClientDto toDto(ClientDB client) {
        return new ClientDto(
                client.getName(),
                client.getCpf(),
                client.getEmail(),
                client.getPhone(),
                client.getDateRegister().toString()
        );
    }
}