package br.com.fiap.ms.client.external.infrastructure.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@DynamoDBTable(tableName = "clients")
public class ClientDB {

    private String id;

    @DynamoDBAttribute
    private String name;

    @DynamoDBHashKey
    private String cpf;

    @DynamoDBAttribute
    private String email;

    @DynamoDBAttribute
    private String phone;

    @DynamoDBAttribute
    private String dateRegister;

    public String getId() {
        return id;
    }

    public void setId(){
        this.id = UUID.randomUUID().toString();
    }


    public String getDateRegister(){
        return LocalDateTime.now().toString();
    }

    public ClientDB() {
        this.id = getId();
    }
}