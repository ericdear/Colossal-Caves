package adventure;

import java.util.Scanner;
import java.util.ArrayList;

public class Parser {

    /**
     * initializes Parser
     */
    public Parser() {}

    /**
     * @param userCommand
     * this method creates a command from the line
     * @return the command
     */
    public Command parseUserCommand(String userCommand) throws InvalidCommandException {
        Scanner inputScanner = new Scanner(userCommand);

        if(!inputScanner.hasNext()) {
            inputScanner.close();
            throw new InvalidCommandException("You must enter a command");
        }

        Command command = createCommand(inputScanner);
        
        inputScanner.close();
        return(command);
    }

    /**
     * creates the command
     * @param inputScanner
     * @return a command
     * @throws InvalidCommandException
     */
    private Command createCommand(Scanner inputScanner) throws InvalidCommandException {
        String action = inputScanner.next();
        if(inputScanner.hasNext()) {
            String noun = inputScanner.nextLine();
            return(new Command(action, noun.trim()));
        } else {
            return(new Command(action));
        }
    }

    /**
     * this method 
     * @return 's all the valid commands
     */
    public String allCommands() {
        String allCommands = "Commands:\n";
        try {
            Command command = new Command("look");
            ArrayList<String> commandList = command.getCommands();
            for(String tempCommand : commandList) {
                allCommands = allCommands + tempCommand + "\n";
            }
        } catch(InvalidCommandException e) {

        }
        return(allCommands);
    }

    /**
     * @return a message for what the class is since there is no variables to print out
     */
    public String toString() {
        return("Parsing Class used for user input");
    }
}
