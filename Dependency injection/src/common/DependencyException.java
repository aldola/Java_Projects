package common;

/**
 * Created by alber on 29/05/2017.
 */
public class DependencyException extends Exception {
    public DependencyException(Exception cause) {
        super(cause);
    }

    public DependencyException(String message) {
        super(message);
    }
}