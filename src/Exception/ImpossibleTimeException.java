package Exception;

/**
 * Given that scheduling is an important feature of this product, an exception should be raised whenever a user tries to
 * schedule something in the past
 */
public class ImpossibleTimeException extends Exception {

    public ImpossibleTimeException() {
        super("ImpossibleTimeException thrown");
        getCause();
        getStackTrace();
    }

    public ImpossibleTimeException(String message) {
        super(message);
        getStackTrace();
    }

}
