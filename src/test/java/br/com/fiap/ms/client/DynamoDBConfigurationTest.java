package br.com.fiap.ms.client;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import br.com.fiap.ms.client.external.configuration.DynamoDBConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
public class DynamoDBConfigurationTest {

    @InjectMocks
    private DynamoDBConfiguration dynamoDBConfiguration;

    @Mock
    private AmazonDynamoDB amazonDynamoDB;

    @BeforeEach
    void setUp() {
        // Set up your mock behavior here
        // For example, you can mock AWS SDK calls if needed
    }

    @Test
    public void testAmazonDynamoDBBean() {
        // Set properties to be used by the DynamoDBConfiguration
        ReflectionTestUtils.setField(dynamoDBConfiguration, "awsEndpoint", "http://localhost:8000");
        ReflectionTestUtils.setField(dynamoDBConfiguration, "awsAccessKey", "fakeAccessKey");
        ReflectionTestUtils.setField(dynamoDBConfiguration, "awsSecretKey", "fakeSecretKey");
        ReflectionTestUtils.setField(dynamoDBConfiguration, "awsRegion", "us-west-2");

        AmazonDynamoDB createdAmazonDynamoDB = dynamoDBConfiguration.amazonDynamoDB();

        assertNotNull(createdAmazonDynamoDB);
        // You can add more assertions here to validate the AmazonDynamoDB instance
    }
}