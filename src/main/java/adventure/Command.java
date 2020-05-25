package adventure;

import java.util.ArrayList;

/* TODO add a static data structure or another enum class
that lists all the valid commands.  Then add methods for validating
commands 

You may add other methods to this class if you wish*/

public class Command {
    private String action;
    private String noun;

    private static ArrayList<String> commands = new ArrayList<String>() {{
        add("look");
        add("go");
        add("take");
        add("exit");
        add("inventory");
    }};

    private static ArrayList<String> directions = new ArrayList<String>() {{
        add("n");
        add("e");
        add("s");
        add("w");
        add("up");
        add("down");
    }};

  /**
     * Create a command object with default values.  
     * both instance variables are set to null
     * 
     */
    public Command() throws InvalidCommandException {
        this(null, null);
    }



  /**
     * Create a command object given only an action.  this.noun is set to null
     *
     * @param command The first word of the command. 
     * 
     */
    public Command(String command) throws InvalidCommandException{

        //TODO validate the action word here and throw an exception if it isn't
        // a single-word action
        this(command, null);
    }

    /**
     * Create a command object given both an action and a noun
     *
     * @param command The first word of the command. 
     * @param what      The second word of the command.
     */
    public Command(String command, String what) throws InvalidCommandException{
        //TODO validate the command here and ensure that the noun provided
        // is a legitimate second word for the command
        // throw an exception if not
        if(!validCommand(command)) {
            throw new InvalidCommandException(command + " is not a valid command.");
        } else if(command.equals("go") && !validDirection(what)) {
            throw new InvalidCommandException("You must provide a valid direction (N,E,S,W,Up,Down).");
        } else if(command.equals("take") && what == null) {
            throw new InvalidCommandException("You must provide the item to be picked up.");
        } else if(command.equals("inventory") && what != null) {
            throw new InvalidCommandException("To see your inventory, type \"inventory\".");
        }
        this.action = command;
        this.noun = what;
        
    }

    /**
     * Return the command word (the first word) of this command. If the
     * command was not understood, the result is null.
     *
     * @return The command word.
     */
    public String getActionWord() {
        return this.action;
    }

    /**
     * @return The second word of this command. Returns null if there was no
     * second word.
     */
    public String getNoun() {
        return this.noun;
    }

    /**
     * @param actionWord 
     * this method sets the first word of the command
     */
    public void setActionWord(String actionWord) throws InvalidCommandException {
        if(validCommand(actionWord)) {
            this.action = actionWord;
        } else {
            throw new InvalidCommandException("Not a valid action word.");
        }
    }

    /**
     * @param newNoun
     * newNoun must be valid!!!!!!!
     * there is no way to check because it could be an item
     */
    public void setNoun(String newNoun) {
        this.noun = newNoun;
    }



    /**
     * @return true if the command has a second word.
     */
    public boolean hasSecondWord() {
        return (noun != null);
    }

    /**
     * @param command the first word of the command
     * @return true if the command is valid
     */
    private static boolean validCommand(String command) {
        for(String tempCommand : commands) {
            if(tempCommand.equals(command)) {
                return(true);
            }
        }
        return(false);
    }

    /**
     * @param direction the direction the user wants to go
     * @return true if the direction is valid
     */
    public static boolean validDirection(String direction) {
        for(String tempDirection : directions) {
            if(tempDirection.equals(direction)) {
                return(true);
            }
        }
        return(false);
    }

    /**
     * @return the list of valid command
     */
    public ArrayList<String> getCommands() {
        return(commands);
    }

    /**
     * @return the action word and noun as a string
     */
    public String toString() {
        return(action + " " + noun);
    }
}
