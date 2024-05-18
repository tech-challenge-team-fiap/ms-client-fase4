package br.com.fiap.ms.client.adapter.controller;

import br.com.fiap.ms.client.application.dto.ClientDto;
import br.com.fiap.ms.client.domain.exception.InvalidProcessException;
import br.com.fiap.ms.client.domain.exception.client.ClientNotFoundException;
import br.com.fiap.ms.client.domain.interfaces.ClientUseCaseInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.zalando.problem.ThrowableProblem;

import static br.com.fiap.ms.client.domain.utils.ProblemAware.problemOf;

@Controller
public class ClientController {
    private final ClientUseCaseInterface clientUseCase;

    @Autowired
    public ClientController(ClientUseCaseInterface clientUseCase) {
        this.clientUseCase = clientUseCase;
    }

    public ResponseEntity<?> register(@RequestBody ClientDto clientDto) throws ThrowableProblem {
        try {
            return ResponseEntity.ok(clientUseCase.register(clientDto));
        } catch (InvalidProcessException ex) {
            return ResponseEntity.badRequest().body(problemOf(ex));
        }
    }

    public ResponseEntity<?> edit(@RequestBody ClientDto clientDto) {
        try {
            clientDto.setCpf(clientDto.getCpf());
            return ResponseEntity.ok(clientUseCase.edit(clientDto));
        } catch (InvalidProcessException ex) {
            return ResponseEntity.badRequest().body(problemOf(ex));
        }
    }

    public ResponseEntity<?> remove(@PathVariable String cpf) {
        try {
            return ResponseEntity.ok(clientUseCase.remove(cpf));
        } catch (InvalidProcessException ex) {
            return ResponseEntity.badRequest().body(problemOf(ex));
        }
    }

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(clientUseCase.findAll());
    }

    public ResponseEntity<?> findByDocument(@PathVariable String cpf) {
        try {
            return ResponseEntity.ok(clientUseCase.findByDocument(cpf));
        } catch (ClientNotFoundException ex) {
            return ResponseEntity.badRequest().body(problemOf(ex));
        }
    }
}