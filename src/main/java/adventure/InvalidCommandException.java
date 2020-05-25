package adventure;

public class InvalidCommandException extends Exception{
    
    /**
     * throws the exception with the message invalid command
     */
    public InvalidCommandException() {
        super("Invalid Command");
    }

    /**
     * @param message to throw when there is an exception thrown
     */
    public InvalidCommandException(String message) {
        super(message);
    }

    /**
     * @return a message for what the class is since there is really nothing to print out
     */
    public String toString() {
        return("Invalid Command Exception Class");
    }
}
