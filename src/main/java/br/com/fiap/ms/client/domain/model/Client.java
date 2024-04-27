package br.com.fiap.ms.client.domain.model;

import br.com.fiap.ms.client.application.dto.ClientDto;
import br.com.fiap.ms.client.application.dto.ClientFormDto;
import br.com.fiap.ms.client.external.infrastructure.entities.ClientDB;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Client {

    private String id;
    private String name;
    private String cpf;
    private String email;
    private String phone;
    private String dateRegister;

    public Client() {}

    public Client(ClientDto clientDto) {
        this.name = clientDto.getName();
        this.cpf = clientDto.getCpf();
        this.email = clientDto.getEmail();
        this.phone = clientDto.getPhone();
        this.dateRegister = clientDto.getDateRegister().toString();
    }

    public Client(ClientFormDto clientDto) {
        this.name = clientDto.getName();
        this.cpf = clientDto.getCpf();
        this.email = clientDto.getEmail();
        this.phone = clientDto.getPhone();
        this.dateRegister = clientDto.getDateRegister().toString();
    }

    public ClientDB build(){
        return ClientDB.builder()
                .id(getId())
                .name(getName())
                .cpf(getCpf())
                .email(getEmail())
                .phone(getPhone())
                .dateRegister(getDateRegister())
                .build();
    }
}
