package adventure;

import java.util.Scanner;
import java.util.ArrayList;

public class Parser {
    public Parser() {}

    /**
     * @param userCommand
     * this method creates a command from the line
     * @return the command
     */
    public Command parseUserCommand(String userCommand) throws InvalidCommandException {
        if(userCommand.equals("")) {
            throw new InvalidCommandException("You must enter a command");
        }
        Scanner inputScanner = new Scanner(userCommand);
        String action = inputScanner.next();
        Command command;
        if(inputScanner.hasNext()) {
            String noun = inputScanner.nextLine();
            command = new Command(action, noun.trim());
        } else {
            command = new Command(action);
        }
        return(command);
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

    public String toString() {
        return("Parsing Class used for user input");
    }
}
