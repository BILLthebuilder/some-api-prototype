package events.api.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
            super("Error From Events-api: " + message);
    }
}