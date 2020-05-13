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
        
        theGame.loadAdventureJson(file);

        


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
        try {
            
            JSONParser parser = new JSONParser();
            Reader reader = new FileReader("example_adventure.json");

            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            System.out.println(jsonObject);

            String name = (String) jsonObject.get("name");
            System.out.println(name);

            long age = (Long) jsonObject.get("age");
            System.out.println(age);

            // loop array
            JSONArray msg = (JSONArray) jsonObject.get("messages");
            Iterator<String> iterator = msg.iterator();
            while (iterator.hasNext()) {
            System.out.println(iterator.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (jsonObject);
    }
    /*public Adventure generateAdventure(JSONObject obj) {
        
    }*/

}
