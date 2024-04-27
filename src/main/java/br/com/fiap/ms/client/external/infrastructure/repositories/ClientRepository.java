package br.com.fiap.ms.client.external.infrastructure.repositories;

import br.com.fiap.ms.client.application.dto.ClientDto;
import br.com.fiap.ms.client.external.infrastructure.entities.ClientDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ClientRepository {

    private DynamoDBMapper dynamoDBMapper;

    public ClientRepository(DynamoDBMapper dynamoDBMapper){
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public ClientDB save(ClientDB client) {
        dynamoDBMapper.save(client);
        return client;
    }

    public Optional<ClientDB> findByCpf(String cpf) {
        return Optional.ofNullable(dynamoDBMapper.load(ClientDB.class, cpf));
    }

    public List<ClientDto> listFindAll() {
        return dynamoDBMapper.scan(ClientDB.class, new DynamoDBScanExpression())
                .stream()
                .map(ClientDto::toDto)
                .collect(Collectors.toList());
    }

    public void delete(ClientDB clientDB) {
        dynamoDBMapper.delete(clientDB);
    }
}