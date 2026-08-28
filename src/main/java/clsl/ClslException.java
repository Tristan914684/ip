package clsl;

/**
 * Represents an error caused by an invalid command or task data.
 */
public class ClslException extends Exception {
    /**
     * Creates an exception with a message suitable for showing to the user.
     *
     * @param message explanation of the error
     */
    public ClslException(String message) {
        super(message);
    }
}
