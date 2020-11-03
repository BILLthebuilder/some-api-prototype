package events.api.exceptions;

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException(String message)
    {
        super("Error From Events-api: " + message);
    }
}