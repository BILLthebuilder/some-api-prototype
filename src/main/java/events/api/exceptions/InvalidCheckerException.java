package events.api.exceptions;

public class InvalidCheckerException extends RuntimeException{
    public InvalidCheckerException(String message)
    {
        super("Error From Events-api: " + message);
    }
}
