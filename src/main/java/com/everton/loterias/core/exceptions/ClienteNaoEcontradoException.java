package com.everton.loterias.core.exceptions;

public class ClienteNaoEcontradoException extends RuntimeException {
    public ClienteNaoEcontradoException(String message) {
        super(message);
    }
}
