package br.com.acubeno.quarkus.exception;

public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -1737454816124521053L;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String format, Object...objects) {
        super(String.format(format, objects));
    }

}
