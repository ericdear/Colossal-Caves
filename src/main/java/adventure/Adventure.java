package adventure;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class Adventure{
    /* you will need to add some private member variables */
    private JSONArray rooms;
    private JSONObject currentRoom;

    /* ======== Required public methods ========== */
        /* note,  you don't have to USE all of these
        methods but you do have to provide them.
        We will be using them to test your code */


    public void setRooms(JSONArray newRooms, JSONObject newRoom) {
        rooms = newRooms;
        currentRoom = newRoom;
    }
/*
    public ArrayList <Room> listAllRooms(){
        
    }*/
/*
    public ArrayList <Item> listAllItems(){

    }
*/
    public String getCurrentRoomDescription(){
        String description = (String) currentRoom.get("short_description");
        return(description);
    }

    /* you may wish to add additional methods*/
}