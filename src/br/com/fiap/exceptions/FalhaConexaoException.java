package br.com.fiap.exceptions;

public class FalhaConexaoException extends Exception {

    public FalhaConexaoException() {

    }

    public FalhaConexaoException(String message) {
        super(message);
    }

    public FalhaConexaoException(Throwable cause) {
        super(cause);
    }

    public FalhaConexaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
