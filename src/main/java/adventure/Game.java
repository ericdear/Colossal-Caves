package adventure;
import java.util.Scanner;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;


/**
 * @author Eric Dearing
 * Last Updated: May 26/20
 */
public class Game implements java.io.Serializable {
    /* this is the class that runs the game.
    You may need some member variables */
    private static final long serialVersionUID = -6682124909469554063L;

    public static void main(String[] args) {
        Game theGame = new Game();
        Scanner scnr = new Scanner(System.in);

        //create adventure room and player
        Adventure adventure = theGame.gameIntro(args);//make this and player a private variable and then save Game
        Player player = theGame.setPlayer(adventure, scnr);
        adventure.setPlayer(player);
        boolean running = true;
        String inputLine;
        
        while(running) {
            //propt the user for a command
            inputLine = scnr.nextLine();
            inputLine = inputLine.toLowerCase();

            theGame.checkCommand(inputLine, player);
            running = theGame.exit(scnr, inputLine, adventure, player);
            System.out.println("");
        }
        scnr.close();
    }

    /**
     * exit determines if the user wants to keep playing the game or not
     * @param scnr - the game scanner to scan input
     * @param inputLine - the line the user inputed
     * @param adventure - the adventure object
     * @param player - the player object
     * @return true to keep playing or false to exit the game
     */
    public boolean exit(Scanner scnr, String inputLine, Adventure adventure, Player player) {
        if(inputLine.equals("exit") || inputLine.equals("quit")) {
            String answer = "";
            while(!answer.equals("yes") && !answer.equals("no")) {
                System.out.println("Are you sure you want to exit? (yes/no)\n");
                answer = scnr.nextLine();
            }
            if(answer.equals("yes")) {
                askUserToSave(scnr, adventure, player);
                return(false);
            } else {
                System.out.println("You are back in the game!");
                return(true);
            }
        }
        return(true);
    }

    /**
     * asks the user to save the game
     * @param scnr - the game scanner to scan the user input from system.in
     * @param adventure - the adventure object
     * @param player - the player object
     */
    public void askUserToSave(Scanner scnr, Adventure adventure, Player player) {
        String answer = "";
        while(!answer.equals("yes") && !answer.equals("no")) {
            System.out.println("Would you like to save? (yes/no)\n");
            answer = scnr.nextLine();
            answer.toLowerCase();
            if(answer.equals("yes")) {
                System.out.println("What would you like to name your saved game?\n");
                String filename = scnr.nextLine();
                player.setSaveGameName(filename);
                saveGame(adventure, player, filename);
            } else if(answer.equals("no")) {
                System.out.println("Thanks for Playing!");
            }
        }
    }

    /**
     * saveGame serializes the adventure object with the player object inside
     * @param adventure - the adventure object
     * @param player - the player object
     * @param filename - the name you want the file to have
     */
    public void saveGame(Adventure adventure, Player player, String filename) {
        adventure.setPlayer(player);
        try {
            FileOutputStream outPutStream = new FileOutputStream(filename);
            ObjectOutputStream outPutDest = new ObjectOutputStream(outPutStream);
            
            outPutDest.writeObject(adventure);

            outPutDest.close();
            outPutStream.close();
            System.out.println("Game Saved as \"" + filename + "\"");
        } catch (IOException e) {
            System.out.println("File could not be saved");
        }
    }

    /**
     * loadGame loads the game ( when -l is used )
     * exits the program if the file is not found
     * @param filename - the name of the saved file
     * @return the adventure object
     */
    public Adventure loadGame(String filename) {
        Adventure adventure = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileInputStream);

            adventure = (Adventure) in.readObject();
            String saveGameName = adventure.getPlayer().getSaveGameName();
            System.out.println("File \"" + saveGameName + "\"" + " Loaded!");
            displayStartingRoom(adventure.getPlayer().getCurrentRoom());
            in.close();
            return(adventure);
        } catch(IOException e) {
            System.out.println("File could not be opened");
        } catch(ClassNotFoundException e) {
            System.out.println("File could not be opened");
        }
        System.exit(0);
        return(null);
    }

    /**
     * @param adventure
     * sets up the player.
     * @return player object
     */
    public Player setPlayer(Adventure adventure, Scanner scnr) {
        Player player;
        if(adventure.getPlayer() == null) {
            Room room = adventure.getCurrentRoom();
            System.out.println("What would you like your player to be called?\n");
            String playerName = scnr.nextLine();
            player = new Player(playerName,room, adventure.listAllRooms());
            displayStartingRoom(room);
        } else {
            player = adventure.getPlayer();
        }
        
        return(player);
    }

    /**
     * @param room : the current room
     * outputs the room name and short description
     */
    public void displayStartingRoom(Room room) {
        System.out.println("You are in " + room.getName() + ", " + room.getShortDescription() + ".");
        printItems(room);
        System.out.println("");
    }

    /**
     * @param filename of the json file
     * @return the json object parsed from the json file
     */
    public JSONObject loadAdventureJson(String filename){
        JSONObject jsonObject = new JSONObject();
        JSONObject adventure = new JSONObject();
        
        //parse the file. return null if file cannot be parsed
        try {
            JSONParser parser = new JSONParser();
            Reader reader = new FileReader(filename);
            jsonObject  = (JSONObject) parser.parse(reader);
            adventure = (JSONObject) jsonObject.get("adventure");
            reader.close();
        } catch (IOException e) {
            return(null);

        } catch (ParseException e) {
            return(null);
        } 
        
        return(adventure);
    }

    /*public JSONObject loadAdventureJson(InputStream inputStream) {

    }*/

    /**
     * @param obj from the file
     * @return the adventure object created
     */
    public Adventure generateAdventure(JSONObject obj) {
        //make the adventure and jsonobject arrayLists
        Adventure adventure = new Adventure();
        ArrayList<JSONObject> rooms = new ArrayList<JSONObject>();
        ArrayList<JSONObject> items = new ArrayList<JSONObject>();
        
        //get the list of rooms
        JSONArray roomList = (JSONArray) obj.get("room");
        for(Object currentRoom : roomList) {
            rooms.add((JSONObject)currentRoom);
        }

        //get the list of items
        JSONArray itemList = (JSONArray) obj.get("item");
        for(Object currentItem : itemList) {
            items.add((JSONObject)currentItem);
        }
        
        //set the rooms in the adventure
        adventure.setAdventure(rooms,items);
        return(adventure);
    }

    /**
     * @param args : the arguments the user inputed on the command line
     * @return the file name that the user wants to load
     */
    public Adventure gameIntro(String[] args) {
        String file = "";
        JSONObject adventureObject = null;
        if(args.length >= 2 && args[0].equals("-l")) {
            file = args[1];
            //call a function to load the games state
            return(loadGame(file));
        } else if(args.length >= 2 && args[0].equals("-a")) {
            file = args[1];
            adventureObject = loadAdventureJson(file);
        } else {
            //load the default adventure json file
            //FIXME
            adventureObject = loadAdventureJson("src/main/java/adventure/default_adventure.json");
        }
        if(adventureObject == null) {
            System.out.println("The file you provided does not exist or is empty");
            System.exit(0);
        }
        return(generateAdventure(adventureObject));
    }

    /**
     * checkCommand creates a command
     * @param inputLine : the line that the user inputed
     * @param player : the player
     */
    public void checkCommand(String inputLine, Player player) {
        Room room = player.getCurrentRoom();
        
        try {
            Parser parser = new Parser();
            Command command = parser.parseUserCommand(inputLine);
            doCommand(command, player);
            if(room != player.getCurrentRoom()) {
                printItems(player.getCurrentRoom());
            }

        } catch(InvalidCommandException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * doCommand tells the player class what command to execute
     * @param command : the command the user entered
     * @param player : the player
     */
    public void doCommand(Command command, Player player) {
        switch(command.getActionWord()) {
            case "look":
                System.out.println(player.look(command));
                break;
            case "go":
                System.out.println(player.go(command));
                break;
            case "take":
                System.out.println(player.take(command));
                break;
            case "inventory":
                System.out.print(player.inventory(command));
                break;
            default:
        }
    }

    /**
     * printItem prints the items it a certain room
     * @param room : the current room
     */
    public static void printItems(Room room) {
        //print items in the room
        ArrayList<Item> itemList = room.listItems();
        for(Item tempItem : itemList) {
            System.out.println("There is a " + tempItem.getName() + " here.");
        }
    }

    /**
     * @return a string saying which class it is because there is no variables
     */
    public String toString() {
        return("The Game Class");
    }

}
