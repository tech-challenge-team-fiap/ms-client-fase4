package br.com.fiap.ms.client.external.configuration;

import br.com.fiap.ms.client.application.usecases.ClientUseCaseImpl;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;
import software.amazon.awssdk.services.ssm.model.GetParameterResponse;

@Configuration
public class DynamoDBConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(DynamoDBConfiguration.class);

    @Value("${amazon.aws.dynamodb.endpoint}")
    private String awsEndpoint;
    @Value("${amazon.aws.access-key}")
    private String awsAccessKey;
    @Value("${amazon.aws.secret-key}")
    private String awsSecretKey;
    @Value("${amazon.aws.region}")
    private String awsRegion;

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(awsAccessKey, awsSecretKey);
    }

    private void initParamsAWS() {
        SsmClient ssmClient = SsmClient.create();

        GetParameterRequest request = GetParameterRequest.builder().name(
                ParameterStoreEnum.REGION.getParameterName()
        ).build();
        this.awsRegion = ssmClient.getParameter(request).parameter().value();

        GetParameterRequest requestParameterDynamoURLAWS = GetParameterRequest.builder().name(
                ParameterStoreEnum.DYNAMO_ENDPOINT.getParameterName()
        ).build();
        this.awsEndpoint = ssmClient.getParameter(requestParameterDynamoURLAWS).parameter().value();

        GetParameterRequest requestParameterAccessKeyIdAWS = GetParameterRequest.builder().name(
                ParameterStoreEnum.ACCESS_KEY_ID.getParameterName()
        ).build();
        this.awsAccessKey = ssmClient.getParameter(requestParameterAccessKeyIdAWS).parameter().value();

        GetParameterRequest requestParameterSecretAccessKeyAWS = GetParameterRequest.builder().name(
                ParameterStoreEnum.SECRET_ACCESS_KEY.getParameterName()
        ).build();
        this.awsSecretKey = ssmClient.getParameter(requestParameterSecretAccessKeyAWS).parameter().value();
    }

    public AWSCredentialsProvider amazonAWSCredentialsProvider() {
        return new AWSStaticCredentialsProvider(amazonAWSCredentials());
    }
    @Bean
    public DynamoDBMapper dynamoDBMapperConfig() {
        return new DynamoDBMapper(amazonDynamoDB());
    }
    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        initParamsAWS();

        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(awsEndpoint, awsRegion))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
                .build();
    }

}
