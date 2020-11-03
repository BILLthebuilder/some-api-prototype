package events.api.exceptions;

public class BadRequestException extends RuntimeException
{
    public BadRequestException(String message)
    {
        super("Error From Events-api: " + message);
    }
}
