package br.com.fiap.ms.client.external.api;

import br.com.fiap.ms.client.adapter.controller.ClientController;
import br.com.fiap.ms.client.application.dto.ClientDto;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.ThrowableProblem;

@RestController
@RequestMapping("/clients")
public class ClientApi {

    private final ClientController clientController;

    @Autowired
    public ClientApi(ClientController clientController){
        this.clientController = clientController;
    }

    @GetMapping
    public ResponseEntity<?> healthCheckApi(){
        return ResponseEntity.ok("Api UP");
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
