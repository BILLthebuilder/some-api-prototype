package events.api.exceptions;

public class AddNewUserException extends RuntimeException
{
    public AddNewUserException(String message)
    {
        super("Error From Events-api: " + message);
    }
}