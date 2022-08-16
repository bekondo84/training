package cm.pak.exceptions;

public class ModelServiceException extends Exception{

    public ModelServiceException() {
        super();
    }

    public ModelServiceException(String message) {
        super(message);
    }

    public ModelServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ModelServiceException(Throwable cause) {
        super(cause);
    }

    protected ModelServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
