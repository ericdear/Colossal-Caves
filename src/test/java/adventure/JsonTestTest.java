package adventure;

import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.Before;


public class JsonTestTest{
    private ArrayList<Room> correctRooms = new ArrayList<Room>();
    private ArrayList<Room> nonCorrectRooms = new ArrayList<Room>();
    private Room room1;
    private Room room2;
    private Room room3;
    private Room room4;
    private JsonTest jsonTest; 

    @Before
    public void setup(){
        newRooms();
        setCorrectRooms();
        correctRooms = addRooms();
        newRooms();
        setNonCorrectRooms();
        nonCorrectRooms = addRooms();
    }

    private void newRooms() {
        room1 = new Room();
        room2 = new Room();
        room3 = new Room();
        room4 = new Room();
    }

    private ArrayList<Room> addRooms() {
        ArrayList<Room> rooms = new ArrayList<Room>();
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
        rooms.add(room4);
        return(rooms);
    }

    //set room 1 and 2
    private void setCorrectRooms() {
        room1.setRoomEntrance("e", room2);
        room1.setRoomEntrance("s", room3);

        room2.setRoomEntrance("w", room1);
        room2.setRoomEntrance("s", room4);

        room3.setRoomEntrance("n", room1);
        room3.setRoomEntrance("e", room4);

        room4.setRoomEntrance("n", room2);
        room4.setRoomEntrance("w", room3);
    }

    private void setNonCorrectRooms() {
        room1.setRoomEntrance("e", room2);
        room1.setRoomEntrance("s", room3);

        room2.setRoomEntrance("w", null);
        room2.setRoomEntrance("s", room4);

        room3.setRoomEntrance("n", room1);
        room3.setRoomEntrance("e", room4);

        room4.setRoomEntrance("n", room2);
        room4.setRoomEntrance("w", room3);
    }

    

    @Test
    public void testWithMissingRoom() {
        System.out.println("Testing missing room");
        jsonTest = new JsonTest();
        jsonTest.setMissingRoom(true);
        assertTrue(jsonTest.test().equals("There is a missing room.\n"));
    }

    @Test
    public void testWithMissingItem() {
        System.out.println("Testing missing item");
        jsonTest = new JsonTest();
        jsonTest.setMissingItem(true);
        assertTrue(jsonTest.test().equals("There is an loot without a corresponding item.\n"));
    }

    @Test
    public void testWithNoExits() {
        System.out.println("Testing no exits");
        jsonTest = new JsonTest();
        jsonTest.setNoExits(true);
        assertTrue(jsonTest.test().equals("There is a room with no exits.\n"));
    }

    @Test
    public void testWithEqualExits() {
        System.out.println("Testing equal exits");
        jsonTest = new JsonTest();
        jsonTest.setEqualExits(true);
        assertTrue(jsonTest.test().equals("There is a room without correct exits.\n"));
    }

    @Test
    public void testEqualExitsWithValidRooms() {
        System.out.println("Test equal exits with valid rooms");
        jsonTest = new JsonTest();
        jsonTest.testEntrances(correctRooms);
        assertTrue(jsonTest.test().equals(""));
    }

    @Test
    public void testNotEqualExits() {
        System.out.println("Test equal exits with non valid rooms");
        jsonTest = new JsonTest();
        jsonTest.testEntrances(nonCorrectRooms);
        System.out.println(jsonTest.test());
        assertTrue(jsonTest.test().equals("There is a room without correct exits.\n"));
    }
}