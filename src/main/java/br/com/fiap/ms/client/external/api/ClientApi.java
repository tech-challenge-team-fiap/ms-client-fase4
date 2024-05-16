package br.com.fiap.ms.client.external.api;

import br.com.fiap.ms.client.adapter.controller.ClientController;
import br.com.fiap.ms.client.application.dto.ClientDto;
import br.com.fiap.ms.client.external.configuration.DynamoDBConfiguration;
import org.apache.http.HttpStatus;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.ThrowableProblem;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;
import software.amazon.awssdk.services.ssm.model.GetParameterResponse;

@RestController
@RequestMapping("/clients")
public class ClientApi {

    private final ClientController clientController;

    private static final Logger logger = LoggerFactory.getLogger(ClientApi.class);


    @Value("${amazon.aws.dynamodb.endpoint}")
    private String awsEndpoint;
    @Value("${amazon.aws.access-key}")
    private String awsAccessKey;
    @Value("${amazon.aws.secret-key}")
    private String awsSecretKey;
    @Value("${amazon.aws.region}")
    private String awsRegion;

    @Autowired
    public ClientApi(ClientController clientController){
        this.clientController = clientController;
    }

    @GetMapping("check")
    public ResponseEntity<?> healthCheckApi(){
        return ResponseEntity.ok("Api UP:" + DateTime.now());
    }
    @PostMapping
    public ResponseEntity<?> register(@RequestBody ClientDto clientDto) throws ThrowableProblem {
        return clientController.register(clientDto);
    }

    @PutMapping
    public ResponseEntity<?> edit(@RequestBody ClientDto clientDto) {
        clientDto.setCpf(clientDto.getCpf());
        return clientController.edit(clientDto);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<?> remove(@PathVariable String cpf) {
        return clientController.remove(cpf);
    }

    @GetMapping
//    @Transactional
    public ResponseEntity<?> findAll() {
        return clientController.findAll();
    }

    @GetMapping("/{cpf}")
//    @Transactional
    public ResponseEntity<?> findByDocument(@PathVariable String cpf) {
        return clientController.findByDocument(cpf);
    }
}
