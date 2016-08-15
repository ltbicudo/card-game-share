package br.com.cardgameshare.importer.exception;

public class ImporterException extends Exception {

    public ImporterException(String message) {
        super(message);
    }

    public ImporterException(String message, Throwable cause) {
        super(message, cause);
    }

}
