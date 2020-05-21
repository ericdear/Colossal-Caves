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
        String file = theGame.gameIntro(scnr);
        boolean running = true;
        boolean fileFound = false;
        String inputLine;
        JSONObject adventureObject = null;

        //parse file
        while(!fileFound) {
            adventureObject = theGame.loadAdventureJson(file);
            if(adventureObject != null) {
                fileFound = true;
                System.out.println("Loading Adventure...");
            } else {
                System.out.println("Something wrong with the file you inputed");
                System.out.println("Please enter the full name of the file\n");
                file = scnr.nextLine();
            }
        }
        
        //create adventure room and player
        Adventure adventure = theGame.generateAdventure(adventureObject);
        Room room = adventure.getCurrentRoom();
        Player player = new Player("Eric",room, adventure.listAllRooms());
        
        //tell the user where they are and print items in the room
        System.out.println("You are in " + room.getName() + ", " + room.getShortDescription() + ".");
        printItems(room);
        System.out.println("");

        while(running) {
            room = player.getCurrentRoom();
            //propt the user for a command
            inputLine = scnr.nextLine();
            inputLine = inputLine.toLowerCase();

            theGame.doCommand(inputLine, player);
            System.out.println("");

            //if the user wants to exit
            if(inputLine.equals("exit")) {
                running = false;
                scnr.close();
            }
        }
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
    public String gameIntro(Scanner scnr) {
        String file;
        String input = "";
        // 1. Print a welcome message to the user
        System.out.println("Welcome to Eric's Colossal Caves!\n");

        // 2. Ask the user if they want to load a json file.
        while(!input.equals("yes") && !input.equals("no")) {
            System.out.println("Would you like to load a json file? (yes/no):\n");
            input = scnr.nextLine();
            input = input.toLowerCase();
        }
        
        // if the user said yes, ask them for the file name, if no, load the default json file
        if(input.equals("yes")) {
            System.out.println("Please enter the full name of the file\n");
            file = scnr.nextLine();
        } else {
            file = "src/main/java/adventure/default_adventure.json";
        }
        return(file);
    }

    public void doCommand(String inputLine, Player player) {
        Scanner inputScanner = new Scanner(inputLine);
        Room room = player.getCurrentRoom();
        Command command;
        String action = inputScanner.next();

        
        try {
            if(inputScanner.hasNext()) {
                String noun = inputScanner.nextLine();
                command = new Command(action, noun.trim());
            } else {
                command = new Command(action);
            }

            //if the user typed look
            if(command.getActionWord().equals("look")) {
                System.out.println(player.look(command));
            }

            //if the user typed go __
            if(command.getActionWord().equals("go")) {
                System.out.println(player.go(command));
                if(room != player.getCurrentRoom()) {
                    printItems(player.getCurrentRoom());
                }
            }
            //if the user typed take
            if(command.getActionWord().equals("take")) {
                System.out.println(player.take(command));
            }

            //if the user typed inventory
            if(command.getActionWord().equals("inventory")) {
                System.out.print(player.inventory(command));
            }

        } catch(InvalidCommandException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void printItems(Room room) {
        //print items in the room
        ArrayList<Item> itemList = room.listItems();
        for(Item tempItem : itemList) {
            System.out.println("There is a " + tempItem.getName() + " here.");
        }
    }

}
