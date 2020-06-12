package adventure;

import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import org.junit.Test;
import org.json.simple.JSONObject;
import org.junit.Before;


public class JsonTestTest{
    private Game game;

    @Before
    public void setup(){
        game = new Game();
    }

    @Test
    public void testWithGoodJson() {
        System.out.println("Test with good Json file");
        InputStream inputStream = Game.class.getClassLoader().getResourceAsStream("default_adventure.json");
        JSONObject adventureObject = game.loadAdventureJson(inputStream);
        Adventure adventure = game.checkJsonErrors(game.generateAdventure(adventureObject));
        assertTrue(game.getErrorMessage().equals("") && adventure != null);
    }

    @Test
    public void testWithNoExits() {
        System.out.println("Test json file with no exits in one room");
        InputStream inputStream = Game.class.getClassLoader().getResourceAsStream("noExitAdventure.json");
        JSONObject adventureObject = game.loadAdventureJson(inputStream);
        Adventure adventure = game.checkJsonErrors(game.generateAdventure(adventureObject));
        assertTrue(game.getErrorMessage().equals("Problems with JSON file!\nThere is a room with no exits.\n") && adventure == null);
    }

    @Test
    public void testWithMissingItem() {
        System.out.println("Test json file with missing item");
        InputStream inputStream = Game.class.getClassLoader().getResourceAsStream("missingItemAdventure.json");
        JSONObject adventureObject = game.loadAdventureJson(inputStream);
        Adventure adventure = game.checkJsonErrors(game.generateAdventure(adventureObject));
        assertTrue(game.getErrorMessage().equals("Problems with JSON file!\nThere is an loot without a corresponding item.\n") && adventure == null);
    }

    @Test
    public void testWithNonEqualExit() {
        System.out.println("Test json file with non equal exits");
        InputStream inputStream = Game.class.getClassLoader().getResourceAsStream("nonEqualExitsAdventure.json");
        JSONObject adventureObject = game.loadAdventureJson(inputStream);
        Adventure adventure = game.checkJsonErrors(game.generateAdventure(adventureObject));
        assertTrue(game.getErrorMessage().equals("Problems with JSON file!\nThere is a room without correct exits.\n") && adventure == null);
    }

    @Test
    public void testWithMissingRoom() {
        System.out.println("Test json file with missing room");
        InputStream inputStream = Game.class.getClassLoader().getResourceAsStream("missingRoomAdventure.json");
        JSONObject adventureObject = game.loadAdventureJson(inputStream);
        Adventure adventure = game.checkJsonErrors(game.generateAdventure(adventureObject));
        assertTrue(game.getErrorMessage().equals("Problems with JSON file!\nThere is a room without correct exits.\nThere is a missing room.\n") && adventure == null);
    }


}