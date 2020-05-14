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
import java.util.Iterator;

public class Game{

    /* this is the class that runs the game.
    You may need some member variables */

    private int room = 0;

    public static void main(String[] args) {

        /* You will need to instantiate an object of type
        game as we're going to avoid using static methods
        for this assignment */
        Game theGame = new Game();
        Scanner scnr = new Scanner(System.in);
        String input = "";
        String file;
        boolean running = true;

        // 1. Print a welcome message to the user
        System.out.println("Welcome to Eric's Colossal Caves!");

        // 2. Ask the user if they want to load a json file.
        while(!input.equals("yes") && !input.equals("no")) {
            System.out.println("Would you like to load a json file?");
            input = scnr.nextLine();
        }
        
        if(input.equals("yes")) {
            System.out.println("Please enter the full name of the file");
            file = scnr.nextLine();
        } else {
            file = "src/main/java/adventure/example_adventure.json";
        }

        /* 3. Parse the file the user specified to create the
        adventure, or load your default adventure*/
        JSONObject adventureObject = theGame.loadAdventureJson(file);

        Adventure adventure = theGame.generateAdventure(adventureObject);
        Room room = adventure.getCurrentRoom();
        System.out.println("You are in " + room.getName());

        // 4. Print the beginning of the adventure
        String inputLine;
        String item;
        // 5. Begin game loop here
        while(running) {
            //propt the user for a command
            inputLine = scnr.nextLine();
            Scanner inputScanner = new Scanner(inputLine);
            input = inputScanner.next();
            

            //check what command they entered
            if(input.equals("look")) {
                //if they said look at an item
                if(inputScanner.hasNext()) {
                    item = inputScanner.next();
                    //they are looking at an item
                    //check to see if that matches an item in the room
                } else {//if they just typed look
                    System.out.println(room.getLongDescription());
                }
            }
        }

        // 6. Get the user input. You'll need a Scanner 

        /* 7+. Use a game instance method to parse the user
        input to learn what the user wishes to do*/

        //use a game instance method to execute the users wishes*/

        /* if the user doesn't wish to quit,
        repeat the steps above*/
    }

    /* you must have these instance methods and may need more*/

    public JSONObject loadAdventureJson(String filename){
        JSONObject jsonObject = new JSONObject();
        JSONObject adventure = new JSONObject();
        Scanner scnr = new Scanner(System.in);
        boolean fileFound = false;
        while(fileFound == false) {
            try {
            
                JSONParser parser = new JSONParser();
                Reader reader = new FileReader(filename);
                fileFound = true;
                jsonObject  = (JSONObject) parser.parse(reader);
                
                //System.out.println(jsonObject);
    
                //String name = (String) jsonObject.get("name");
                //System.out.println(name);
    
                // A JSON array. JSONObject supports java.util.List interface.
                
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
        //make the adventure and json object
        Adventure adventure = new Adventure();
        JSONObject currentRoom = new JSONObject();

        //get all the rooms in an array
        JSONArray roomList = new JSONArray();
        JSONArray itemList = new JSONArray();
        roomList = (JSONArray) obj.get("room");
        itemList = (JSONArray) obj.get("item");
        ArrayList <JSONObject> rooms = new ArrayList();
        ArrayList <JSONObject> items = new ArrayList();

        try {
            Iterator<JSONObject> roomIterator = roomList.iterator();
            Iterator<JSONObject> itemIterator = itemList.iterator();
            // goes through the rooms, passes the room array and the starting room to adventure
			while (roomIterator.hasNext()) {
                currentRoom = roomIterator.next();
                rooms.add(currentRoom);
                items.add(itemIterator.next());
            }
            adventure.setRoomList(rooms,items);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return(adventure);
    }

}
