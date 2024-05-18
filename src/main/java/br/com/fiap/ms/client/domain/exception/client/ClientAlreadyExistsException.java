package br.com.fiap.ms.client.domain.exception.client;

public class ClientAlreadyExistsException extends InvalidClientProcessException {
    private static final String tittle = "Client already exists";
    private static final String message = "The client with CPF %s was already exists";

    public ClientAlreadyExistsException(String cpf) {
        super(tittle, String.format(message, cpf));
    }
}
