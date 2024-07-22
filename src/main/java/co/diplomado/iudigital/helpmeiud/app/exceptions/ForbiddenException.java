package co.diplomado.iudigital.helpmeiud.app.exceptions;

public class ForbiddenException extends RestException {

    private static final long serialVersionUID = 1L;

    public ForbiddenException() {
        super();
    }

    public ForbiddenException(ErrorDto errorDto) {
        super(errorDto);
    }

    public ForbiddenException(String msg) {
        super(msg);
    }
}
