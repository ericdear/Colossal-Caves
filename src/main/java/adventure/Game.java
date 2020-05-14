package adventure;
import java.util.Scanner;

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
            file = "default.json";
        }

        /* 3. Parse the file the user specified to create the
        adventure, or load your default adventure*/
        JSONObject adventureObject = theGame.loadAdventureJson(file);

        Adventure adventure = theGame.generateAdventure(adventureObject);
        System.out.println("Room description: " + adventure.getCurrentRoomDescription());

        // 4. Print the beginning of the adventure

        // 5. Begin game loop here
        /*while(running) {

        }*/

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
        try {
            
            JSONParser parser = new JSONParser();
            Reader reader = new FileReader("./src/main/java/adventure/example_adventure.json");
            jsonObject  = (JSONObject) parser.parse(reader);
            
            //System.out.println(jsonObject);

            //String name = (String) jsonObject.get("name");
            //System.out.println(name);

            // A JSON array. JSONObject supports java.util.List interface.
            
            adventure = (JSONObject) jsonObject.get("adventure");
			
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return(adventure);
    }

    public Adventure generateAdventure(JSONObject obj) {
        //make the adventure and json object
        Adventure adventure = new Adventure();
        JSONObject temp = new JSONObject();
        //get all the rooms in an array
        JSONArray roomList = new JSONArray();
        roomList = (JSONArray) obj.get("room");
        try {
			
            Iterator<JSONObject> iterator = roomList.iterator();
            
            // goes through the rooms, passes the room array and the starting room to adventure
			while (iterator.hasNext()) {
                temp = iterator.next();
                if(temp.containsKey("start")) {
                    adventure.setRooms(roomList, temp);
                }
                //System.out.println("\n" + iterator.next());
            }
        } catch (Exception e) {}

        
        return(adventure);
    }

}
