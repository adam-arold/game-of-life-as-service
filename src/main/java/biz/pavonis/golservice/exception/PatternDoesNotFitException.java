package biz.pavonis.golservice.exception;

public class PatternDoesNotFitException extends RuntimeException {

    private static final long serialVersionUID = -1801131706265452410L;

    public PatternDoesNotFitException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

}
