package cm.pak.training.exceptions;

public class TrainingException extends Exception{

    private String value ;

    public TrainingException() {
    }

    public TrainingException(String message) {
        super(message);
        this.value = message;
    }

    public TrainingException(String message, Throwable cause) {
        super(message, cause);
    }

    public TrainingException(Throwable cause) {
        super(cause);
    }

    public TrainingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
