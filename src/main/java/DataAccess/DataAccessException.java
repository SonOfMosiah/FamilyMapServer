package DataAccess;

/**
 * Exception Class for Errors connecting to the Database
 */
public class DataAccessException extends Exception {
    private final String message;
    DataAccessException(String message) {
        super(message);
        this.message = message;
    }

    public String toString() {
        return message;
    }
}
