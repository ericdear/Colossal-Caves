package adventure;

import java.util.ArrayList;

public class Command {

    private String action;
    private String noun;

    private static final ArrayList<String> COMMANDS = new ArrayList<String>();
    private static final ArrayList<String> DIRECTIONS = new ArrayList<String>();
    static {
        COMMANDS.add("look");
        COMMANDS.add("go");
        COMMANDS.add("take");
        COMMANDS.add("exit");
        COMMANDS.add("quit");
        COMMANDS.add("inventory");

        DIRECTIONS.add("n");
        DIRECTIONS.add("e");
        DIRECTIONS.add("s");
        DIRECTIONS.add("w");
        DIRECTIONS.add("up");
        DIRECTIONS.add("down");
    }

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
        this(command, null);
    }

    /**
     * Create a command object given both an action and a noun
     *
     * @param command The first word of the command. 
     * @param what      The second word of the command.
     */
    public Command(String command, String what) throws InvalidCommandException{
        command = command.toLowerCase();
        if(what != null) {
            what = what.toLowerCase();
        }
        validateCommand(command, what);
    }

    /**
     * check if the command is valid
     * @param command the first word of the command
     * @param what the second word of the command
     */
    public void validateCommand(String command, String what) throws InvalidCommandException {
        if(!validActionWord(command)) {
            throw new InvalidCommandException(command + " is not a valid command.");
        } else if(command.equals("go") && !validDirection(what)) {
            throw new InvalidCommandException("You must provide a valid direction (N,E,S,W,Up,Down).");
        } else if(command.equals("take") && what == null) {
            throw new InvalidCommandException("You must provide the item to be picked up.");
        } else if(command.equals("inventory") && what != null) {
            throw new InvalidCommandException("To see your inventory, type \"inventory\".");
        }
        setCommands(command, what);
    }

    /**
     * set the firs tand last word of the command
     * @param command - the first word of the command
     * @param what - the second word of the command
     */
    private void setCommands(String command, String what) {
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
    public void setAction(String actionWord) throws InvalidCommandException {
        validateCommand(actionWord, noun);
    }

    /**
     * set the noun
     * @param newNoun
     */
    public void setNoun(String newNoun) throws InvalidCommandException {
        validateCommand(action, newNoun);
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
    private boolean validActionWord(String command) {
        for(String tempCommand : COMMANDS) {
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
    private boolean validDirection(String direction) {
        for(String tempDirection : DIRECTIONS) {
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
        return(COMMANDS);
    }

    /**
     * @return the action word and noun as a string
     */
    public String toString() {
        return(action + " " + noun);
    }
}
