package br.com.fiap.ms.client.domain.exception.client;

import br.com.fiap.ms.client.domain.exception.InvalidProcessException;

public class InvalidClientProcessException extends InvalidProcessException {

    public InvalidClientProcessException(String tittle, String message) {
        super(tittle, message);
    }
}
