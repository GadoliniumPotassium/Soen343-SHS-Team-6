package Exception;

public class TimeInValidException extends Exception{

    public TimeInValidException() {
        super("ImpossibleTimeException thrown");
        getCause();
        getStackTrace();
    }

    public TimeInValidException(String message) {
        super(message);
        getStackTrace();
    }
}
