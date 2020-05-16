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

        /* You will need to instantiate an object of type
        game as we're going to avoid using static methods
        for this assignment */
        Game theGame = new Game();
        Scanner scnr = new Scanner(System.in);
        String file;
        file = theGame.gameIntro(scnr);
        boolean running = true;
        String inputLine;
        

        /* 3. Parse the file the user specified to create the
        adventure, or load your default adventure*/
        JSONObject adventureObject = theGame.loadAdventureJson(file);
        Adventure adventure = theGame.generateAdventure(adventureObject);
        Room room = adventure.getCurrentRoom();
        

        //tell the user where they are and print items in the room
        System.out.println("You are in " + room.getName() + ", " + room.getShortDescription() + ".");
        room.printRoomItems();

        while(running) {
            room = adventure.getCurrentRoom();
            //propt the user for a command
            inputLine = scnr.nextLine();
            inputLine = inputLine.toLowerCase();
            adventure = theGame.doCommand(adventure, inputLine);

            //if the user wants to exit
            if(inputLine.equals("exit")) {
                running = false;
            }
        }
    }

    public JSONObject loadAdventureJson(String filename){
        JSONObject jsonObject = new JSONObject();
        JSONObject adventure = new JSONObject();
        Scanner scnr = new Scanner(System.in);
        boolean fileFound = false;

        //ask the user for a file, if it doesnt parse then ask them again
        while(!fileFound) {
            try {
            
                JSONParser parser = new JSONParser();
                Reader reader = new FileReader(filename);
                fileFound = true;
                jsonObject  = (JSONObject) parser.parse(reader);
                adventure = (JSONObject) jsonObject.get("adventure");
                
            } catch (IOException e) {
                System.out.println("Something wrong with the file you inputed");
                System.out.println("Please enter the full name of the file");
                filename = scnr.nextLine();

            } catch (ParseException e) {
                e.printStackTrace();
            } 
        }
        return(adventure);
    }

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

    public String gameIntro(Scanner scnr) {
        String file;
        String input = "";
        // 1. Print a welcome message to the user
        System.out.println("Welcome to Eric's Colossal Caves!");

        // 2. Ask the user if they want to load a json file.
        while(!input.equals("yes") && !input.equals("no")) {
            System.out.println("Would you like to load a json file? (yes/no):");
            input = scnr.nextLine();
            input = input.toLowerCase();
        }
        
        // if the user said yes, ask them for the file name, if no, load the default json file
        if(input.equals("yes")) {
            System.out.println("Please enter the full name of the file");
            file = scnr.nextLine();
        } else {
            file = "src/main/java/adventure/default_adventure.json";
        }
        return(file);
    }

    public Adventure doCommand(Adventure adventure, String inputLine) {
        Scanner inputScanner = new Scanner(inputLine);
        String input = inputScanner.next();
        Room room = adventure.getCurrentRoom();
        String item;
        String direction;
        

        //check what command they entered
        if(input.equals("look")) {
        //if they said look at an item
            if(inputScanner.hasNext()) {
                item = inputScanner.next();

                //if the item is 2 words
                while(inputScanner.hasNext()) {
                    item = item.concat(" ");
                    item = item.concat(inputScanner.next());
                }
                
                //they are looking at an item
                //check to see if that matches an item in the room
                System.out.println(room.searchItemDescription(item) + ".");

            } else {//if they just typed look
                System.out.println(room.getLongDescription() + ".");
            }
            //if the user typed go
        } else if(input.equals("go")) {
            if(inputScanner.hasNext()) {
                direction = inputScanner.next();
                    
                //check if there is an entrance in that direction, if there is, go there
                if(adventure.checkDirection(direction) == null) {
                    System.out.println("There is no room in the direction " + direction + ".");
                } else {
                    room = adventure.changeRooms(direction);
                    System.out.println("You are in " + room.getName() + ", " + room.getShortDescription() + ".");

                    //print items in the room
                    room.printRoomItems();
                }

            } else {//if they just typed go
                System.out.println("You must provide a Direction.");
            }
        } else if(!input.equals("exit")) {
            System.out.println("Command " + input + " not found.");
        }
        return(adventure);
    }

}
