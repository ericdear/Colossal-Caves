package adventure;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.Before;


public class RoomTest{
    private Room testRoom;
    private Room connectedRoom;

    @Before
    public void setup(){
        testRoom = new Room();
        connectedRoom = new Room();
    }

    @Test
    public void testNullEntrance() {
        System.out.println("Testing with a null entance");
        testRoom.setRoomEntrance("N",null);
        assertTrue(testRoom.getConnectedRoom("N") == null);
    }

    @Test
    public void testWrongDirection() {
        System.out.println("Testing with a wrong direction");
        testRoom.setRoomEntrance("W",null);
        assertTrue(testRoom.getConnectedRoom("J") == null);
    }

    @Test
    public void testWithSetName() {
        System.out.println("Testing with set name");
        connectedRoom.setName("Eric");
        testRoom.setRoomEntrance("E",connectedRoom);
        assertTrue(testRoom.getConnectedRoom("E").getName().equals("Eric"));
    }

    @Test
    public void testWithBigId() {
        System.out.println("Testing with a big ID");
        connectedRoom.setId(16161717);
        testRoom.setRoomEntrance("E",connectedRoom);
        assertTrue(testRoom.getConnectedRoom("E").getId() == 16161717);
    }

    @Test
    public void testWithNegativeId() {
        System.out.println("Testing with a negative ID");
        connectedRoom.setId(-50);
        testRoom.setRoomEntrance("W",connectedRoom);
        assertTrue(testRoom.getConnectedRoom("W").getId() == -50);
    }

    @Test
    public void testWithDefaultRoom() {
        System.out.println("Testing with a default room");
        connectedRoom = new Room();
        testRoom.setRoomEntrance("E",connectedRoom);
        assertTrue(testRoom.getConnectedRoom("E") == connectedRoom);
    }

    @Test
    public void testWithDifferentRooms() {
        System.out.println("Testing with a different room");
        connectedRoom.setShortDescription("Hello there");
        testRoom.setRoomEntrance("E",connectedRoom);
        Room otherRoom = new Room();
        assertTrue(testRoom.getConnectedRoom("E") != otherRoom);
    }

    @Test
    public void testWithFullRoom() {
        System.out.println("Testing with a full room");
        connectedRoom.setName("Living Room");
        connectedRoom.setId(16);
        connectedRoom.setShortDescription("Hello There");
        connectedRoom.setLongDescription("My name is Eric");
        testRoom.setRoomEntrance("W",connectedRoom);
        assertTrue(testRoom.getConnectedRoom("W") == connectedRoom);
    }
    


}