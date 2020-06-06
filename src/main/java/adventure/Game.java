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

import java.io.InputStream;
import java.io.InputStreamReader;


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

        theGame.running(scnr, adventure, player);
        
        scnr.close();
    }

    /**
     * the loop where the game is running
     * @param scnr - the input scanner
     * @param adventure - the adventure
     * @param player - the player
     */
    public void running(Scanner scnr, Adventure adventure, Player player) {
        boolean running = true;
        String inputLine;
        while(running) {
            //propt the user for a command
            inputLine = scnr.nextLine();
            inputLine = inputLine.toLowerCase();

            checkCommand(inputLine, player);
            running = exit(scnr, inputLine, adventure, player);
            System.out.println("");
        }
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
            String answer = confirmAnswer(scnr);
            return(doAnswer(answer, scnr, adventure, player));
        }
        return(true);
    }

    /**
     * either ask the user to save or put the user back in the game
     * @param answer - if the user typed yes or no
     * @param scnr - the input scanner
     * @param adventure - the adventure
     * @param player - the player
     * @return the boolean true to stay in the game and false to exit
     */
    private boolean doAnswer(String answer, Scanner scnr, Adventure adventure, Player player) {
        if(answer.equals("yes")) {
            askUserToSave(scnr, adventure, player);
            return(false);
        } else {
            System.out.println("You are back in the game!");
            return(true);
        }
    }

    /**
     * confirm that the user wants to exit
     * @param scnr - the input scanner
     * @return the answer once it is either yes or no
     */
    private String confirmAnswer(Scanner scnr) {
        String answer = "";
        while(!answer.equals("yes") && !answer.equals("no")) {
            System.out.println("Are you sure you want to exit? (yes/no)\n");
            answer = scnr.nextLine();
        }
        return(answer);
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
            saveAnswer(scnr, adventure, player, answer);
        }
    }

    /**
     * save or exit depending on answer being yes or no
     * @param scnr - the input scanner
     * @param adventure - the adventure
     * @param player - the player
     * @param answer - the users answer to "Would you like to save?"
     */
    private void saveAnswer(Scanner scnr, Adventure adventure, Player player, String answer) {
        if(answer.equals("yes")) {
            System.out.println("What would you like to name your saved game?\n");
            String filename = scnr.nextLine();
            player.setSaveGameName(filename);
            saveGame(adventure, player, filename);
        } else if(answer.equals("no")) {
            System.out.println("Thanks for Playing!");
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
    public Adventure tryLoadGame(String filename) {
        try {
            return(loadGame(filename));
        } catch(IOException e) {
            System.out.println("File could not be opened");
        } catch(ClassNotFoundException e) {
            System.out.println("File could not be opened");
        }
        System.exit(0);
        return(null);
    }

    /**
     * loads the game
     * @param filename - the name of the file the user wants to load
     * @return the advenutre object
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private Adventure loadGame(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(fileInputStream);

        Adventure adventure = (Adventure) in.readObject();
        String saveGameName = adventure.getPlayer().getSaveGameName();
        System.out.println("File \"" + saveGameName + "\"" + " Loaded!");
        displayStartingRoom(adventure.getPlayer().getCurrentRoom());
        in.close();
        return(adventure);
    }



    /**
     * sets up the player.
     * @param adventure - the adventure object
     * @param scnr - the scanner
     * @return player object
     */
    public Player setPlayer(Adventure adventure, Scanner scnr) {
        Player player;
        if(adventure.getPlayer() == null) {
            Room room = adventure.getCurrentRoom();
            System.out.println("What would you like your player to be called?\n");
            //String playerName = scnr.nextLine();
            player = new Player(scnr.nextLine(),room, adventure.listAllRooms());
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
     * this creates the json object from the .json file
     * @param filename of the json file
     * @return the json object parsed from the json file
     */
    public JSONObject loadAdventureJson(String filename){
        JSONObject adventure;
        JSONParser parser = new JSONParser();
        
        //parse the file. return null if file cannot be parsed
        try (Reader reader = new FileReader(filename)) {
            JSONObject jsonObject  = (JSONObject) parser.parse(reader);
            adventure = (JSONObject) jsonObject.get("adventure");
            reader.close();
        } catch (IOException | ParseException e) {
            return(null);
        }
        return(adventure);
    }

    /**
     * creates a json object from the inputstream of the default adventure
     * @param inputStream - the input stream of the default adventure
     * @return the created jsonobject
     */
    public JSONObject loadAdventureJson(InputStream inputStream) {
        JSONObject adventure;
        JSONParser parser = new JSONParser();

        try (InputStreamReader reader = new InputStreamReader(inputStream)) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            adventure = (JSONObject) jsonObject.get("adventure");
            reader.close();
        } catch (IOException | ParseException e) {
            return(null);
        }
        return(adventure);
    }

    /**
     * @param obj from the file
     * @return the adventure object created
     */
    public Adventure generateAdventure(JSONObject obj) {
        //make the adventure and jsonobject arrayLists
        Adventure adventure = new Adventure();
        
        //get the list of rooms
        ArrayList<JSONObject> rooms = setRoomList(obj);

        //get the list of items
        ArrayList<JSONObject> items = setItemList(obj);
        
        //set the rooms in the adventure
        adventure.setAdventure(rooms,items);
        return(adventure);
    }

    /**
     * set the arrayList of rooms
     * @param obj - the json object adventure
     * @return the arraylist of rooms
     */
    private ArrayList<JSONObject> setRoomList(JSONObject obj) {
        ArrayList<JSONObject> rooms = new ArrayList<JSONObject>();
        JSONArray roomList = (JSONArray) obj.get("room");
        for(Object currentRoom : roomList) {
            rooms.add((JSONObject)currentRoom);
        }
        return(rooms);
    }

    /**
     * set the item list
     * @param obj - the json object adventure
     * @return the arraylist of items
     */
    private ArrayList<JSONObject> setItemList(JSONObject obj) {
        ArrayList<JSONObject> items = new ArrayList<JSONObject>();
        JSONArray itemList = (JSONArray) obj.get("item");
        for(Object currentItem : itemList) {
            items.add((JSONObject)currentItem);
        }
        return(items);
    }

    /**
     * @param args : the arguments the user inputed on the command line
     * @return the adventure object the user loaded
     */
    public Adventure gameIntro(String[] args) {
        JSONObject adventureObject = null;
        if(args.length >= 2 && args[0].equals("-l")) {
            String file = args[1];
            //call a function to load the games state
            return(tryLoadGame(file));
        }
        adventureObject = getAdventureJson(args);
        checkNull(adventureObject);
        return(generateAdventure(adventureObject));
    }

    /**
     * check if the adventure object is null
     * @param adventureObject
     */
    private void checkNull(JSONObject adventureObject) {
        if(adventureObject == null) {
            System.out.println("The file you provided does not exist or is empty");
            System.exit(0);
        }
    }

    /**
     * load the json object from the json file
     * @param args
     * @return the json object
     */
    private JSONObject getAdventureJson(String[] args) {
        String file = "";
        if(args.length >= 2 && args[0].equals("-a")) {
            file = args[1];
            return(loadAdventureJson(file));
        } else {
            //load the default adventure json file
            InputStream inputStream = Game.class.getClassLoader().getResourceAsStream("default_adventure.json");
            return(loadAdventureJson(inputStream));
        }
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
            movedRooms(room, player);

        } catch(InvalidCommandException e) {
            System.out.println(e.getMessage());
        }
    }

    private void movedRooms(Room room, Player player) {
        if(room != player.getCurrentRoom()) {
            printItems(player.getCurrentRoom());
        }
    }

    /**
     * doCommand tells the player class what command to execute
     * @param command : the command the user entered
     * @param player : the player
     */
    public void doCommand(Command command, Player player) {
        if(command.getActionWord().equals("look")) {
            System.out.println(player.look(command));
        } else if(command.getActionWord().equals("go")) {
            System.out.println(player.go(command));
        } else if(command.getActionWord().equals("take")) {
            System.out.println(player.take(command));
        } else if(command.getActionWord().equals("inventory")) {
            System.out.print(player.inventory(command));
        }
    }

    /**
     * printItem prints the items it a certain room
     * @param room : the current room
     */
    public void printItems(Room room) {
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
