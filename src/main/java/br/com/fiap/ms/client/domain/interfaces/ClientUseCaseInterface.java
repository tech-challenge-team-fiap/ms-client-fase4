package br.com.fiap.ms.client.domain.interfaces;

import br.com.fiap.ms.client.application.dto.ClientDto;
import br.com.fiap.ms.client.domain.exception.InvalidProcessException;
import br.com.fiap.ms.client.domain.exception.client.ClientNotFoundException;
import br.com.fiap.ms.client.domain.exception.client.InvalidClientProcessException;

import java.util.List;

public interface ClientUseCaseInterface {

    List<ClientDto> findAll();

    ClientDto findByCpf(String cpf) throws InvalidProcessException, InvalidClientProcessException;

    ClientDto findByDocument(String cpf) throws ClientNotFoundException;

    ClientDto edit(final ClientDto clientDto) throws InvalidClientProcessException;

    ClientDto register(final ClientDto clientDto) throws InvalidClientProcessException;

    ClientDto remove(final String cpf) throws InvalidClientProcessException;
}