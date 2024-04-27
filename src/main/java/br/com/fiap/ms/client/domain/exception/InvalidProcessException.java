package br.com.fiap.ms.client.domain.exception;

import lombok.Getter;

@Getter
public class InvalidProcessException extends Exception {

    private final String tittle;
    private final String message;

    public InvalidProcessException (String tittle, String message) {
        this.tittle = tittle;
        this.message = message;
    }
}
