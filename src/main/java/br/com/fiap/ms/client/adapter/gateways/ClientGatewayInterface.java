package br.com.fiap.ms.client.adapter.gateways;

import br.com.fiap.ms.client.application.dto.ClientDto;
import br.com.fiap.ms.client.domain.exception.client.ClientNotFoundException;
import br.com.fiap.ms.client.domain.exception.client.InvalidClientProcessException;
import br.com.fiap.ms.client.domain.model.Client;
import br.com.fiap.ms.client.external.infrastructure.entities.ClientDB;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientGatewayInterface {

    ClientDto register(Client client) throws InvalidClientProcessException;

    ClientDto update(ClientDto clientDto) throws InvalidClientProcessException;

    ClientDto delete(String cpf) throws ClientNotFoundException;

    List<ClientDto> listFindAll();

    ClientDto findByCpf(String cpf) throws InvalidClientProcessException;

    ClientDto findByDocument(String cpf) throws ClientNotFoundException;

}