package adventure;
import java.util.Scanner;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Game{

    /* this is the class that runs the game.
    You may need some member variables */

    public static void main(String[] args) {
        Game theGame = new Game();
        Scanner scnr = new Scanner(System.in);

        //create adventure room and player
        Adventure adventure = theGame.gameIntro(args);
        Player player = theGame.setPlayer(adventure);
        Room room = player.getCurrentRoom();

        boolean running = true;
        String inputLine;
        
        while(running) {
            room = player.getCurrentRoom();
            //propt the user for a command
            inputLine = scnr.nextLine();
            inputLine = inputLine.toLowerCase();

            theGame.checkCommand(inputLine, player);
            System.out.println("");

            //if the user wants to exit
            if(inputLine.equals("exit")) {
                running = false;
                scnr.close();
            }
        }
    }

    /**
     * @param adventure
     * sets up the player.
     * @return player object
     */
    public Player setPlayer(Adventure adventure) {
        Room room = adventure.getCurrentRoom();
        Player player = new Player("Eric",room, adventure.listAllRooms());
        displayStartingRoom(room);
        return(player);
    }

    public void displayStartingRoom(Room room) {
        System.out.println("You are in " + room.getName() + ", " + room.getShortDescription() + ".");
        printItems(room);
        //System.out.println("");
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

    /**
     * @param obj from the file
     * @return the adventure object created
     */
    public Adventure generateAdventure(JSONObject obj) {
        //make the adventure and jsonobject arrayLists
        Adventure adventure = new Adventure();
        ArrayList<JSONObject> rooms = new ArrayList();
        ArrayList<JSONObject> items = new ArrayList();
        
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
        adventure.setRoomList(rooms,items);
        return(adventure);
    }

    /**
     * @param scnr 
     * @return the file name that the user wants to load
     */
    public Adventure gameIntro(String[] args) {
        String file = "";
        JSONObject adventureObject = null;
        if(args.length >= 2 && args[0].equals("-l")) {
            file = args[1];
            //call a function to load the games state
        } else if(args.length >= 2 && args[0].equals("-a")) {
            file = args[1];
            adventureObject = loadAdventureJson(file);
        } else {
            //load the default adventure json file
            adventureObject = loadAdventureJson("src/main/java/adventure/default_adventure.json");
        }
        if(adventureObject == null) {
            System.out.println("The file you provided does not exist or is empty");
            System.exit(0);
        }
        return(generateAdventure(adventureObject));
    }

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

    public static void printItems(Room room) {
        //print items in the room
        ArrayList<Item> itemList = room.listItems();
        for(Item tempItem : itemList) {
            System.out.println("There is a " + tempItem.getName() + " here.");
        }
        System.out.println("");
    }

    public String toString() {
        return("The Game Class");
    }

}
