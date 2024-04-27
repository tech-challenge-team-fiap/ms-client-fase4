package br.com.fiap.ms.client.external.infrastructure.gateway;

import br.com.fiap.ms.client.adapter.gateways.ClientGatewayInterface;
import br.com.fiap.ms.client.application.dto.ClientDto;
import br.com.fiap.ms.client.domain.exception.client.ClientAlreadyExistsException;
import br.com.fiap.ms.client.domain.exception.client.ClientNotFoundException;
import br.com.fiap.ms.client.domain.exception.client.InvalidClientProcessException;
import br.com.fiap.ms.client.domain.model.Client;
import br.com.fiap.ms.client.domain.utils.ValidCPF;
import br.com.fiap.ms.client.external.infrastructure.entities.ClientDB;
import br.com.fiap.ms.client.external.infrastructure.repositories.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class ClientGatewayImpl implements ClientGatewayInterface {

    private static final Logger logger = LoggerFactory.getLogger(ClientGatewayImpl.class);

    private final ClientRepository clientRepository;

    @Autowired
    public ClientGatewayImpl(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public ClientDto register(Client client) throws InvalidClientProcessException {
        try {
            ClientDB clientDb = client.build();

            clientDb.setId();

            clientRepository.save(clientDb);
            logger.info("client registered successfully");

            return new ClientDto(clientDb.getId().toString());
        } catch (Exception e) {
            logger.error("Error registering new client", e);
            throw new RuntimeException(e);
        }
    }

    public ClientDto update(ClientDto clientDto) throws InvalidClientProcessException {
        Optional<ClientDB> clientUpdateDB = clientRepository.findByCpf(clientDto.getCpf());

        if (!clientUpdateDB.isPresent()) {
            throw new ClientNotFoundException(clientDto.getCpf());
        }

        clientRepository.save(
                new ClientDB(
                        clientUpdateDB.get().getId(),
                        clientDto.getName(),
                        clientUpdateDB.get().getCpf(),
                        clientDto.getEmail(),
                        clientDto.getPhone(),
                        LocalDateTime.now().toString()
                )
        );
        logger.info("[CLIENT] Update client with successfully");
        return new ClientDto(clientUpdateDB.get().getId().toString());
    }

    public ClientDto delete(String cpf) throws ClientNotFoundException {
        Optional<ClientDB> client = clientRepository.findByCpf(cpf);

        if (!client.isPresent()) {
            throw new ClientNotFoundException(cpf);
        }

        clientRepository.delete(client.get());
        logger.info("[CLIENT] Remove client with successfully");

        return new ClientDto(client.get().getId().toString());
    }

    public List<ClientDto> listFindAll() {
        return this.clientRepository.listFindAll();
    }

    public ClientDto findByCpf(String cpf) throws InvalidClientProcessException {
        ValidCPF.validateCpf(cpf);

        Optional<ClientDB> clientDB = clientRepository.findByCpf(cpf);

        if (clientDB.isPresent()) {
            String message = String.format("Client with CPF %s already has a registration!", cpf);
            logger.info(message);
            throw new ClientAlreadyExistsException(cpf);
        } else {
            logger.info(String.format("Client with cpf %s not exists so creating it.", cpf));
            return new ClientDto();
        }
    }

    public ClientDto findByDocument(String cpf) throws ClientNotFoundException{
        Optional<ClientDB> clientDB = clientRepository.findByCpf(cpf);

        if (!clientDB.isPresent()) {
            throw new ClientNotFoundException(cpf);
        }

        return new ClientDto(clientDB.get().getName(), clientDB.get().getCpf(), clientDB.get().getEmail(), clientDB.get().getPhone(), clientDB.get().getDateRegister().toString());
    }
}