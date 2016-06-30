package br.com.cardgameshare.exception;

public class ExcecaoNegocial extends Exception {

    public ExcecaoNegocial(String message) {
        super(message);
    }

    public ExcecaoNegocial(String message, Throwable cause) {
        super(message, cause);
    }

}
