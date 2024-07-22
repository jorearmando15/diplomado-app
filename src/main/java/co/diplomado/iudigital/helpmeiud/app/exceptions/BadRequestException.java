package co.diplomado.iudigital.helpmeiud.app.exceptions;

public class BadRequestException extends RestException { // 400

    private static final long serialVersionUID = 1L;

    public BadRequestException() {
        super();
    }

    public BadRequestException(ErrorDto errorDto) {
        super(errorDto);
    }

    public BadRequestException(String msg) {
        super(msg);
    }
}
