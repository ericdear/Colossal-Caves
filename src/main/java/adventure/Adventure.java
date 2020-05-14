package adventure;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class Adventure{
    /* you will need to add some private member variables */
    private ArrayList <Room> roomList;
    private Room currentRoom;
    

    /* ======== Required public methods ========== */
        /* note,  you don't have to USE all of these
        methods but you do have to provide them.
        We will be using them to test your code */


    public void setRoomList(ArrayList <JSONObject> newRooms) {
        int i;
        JSONObject tempObject;
        roomList = new ArrayList();
        Room tempRoom;
        for(i = 0; i < newRooms.size(); i++) {
            //create all the rooms and put into arraylist
            tempObject = newRooms.get(i);
            tempRoom = new Room(tempObject);
            roomList.add(tempRoom);

            //set the current room to the room with the start key
            if(tempObject.containsKey("start")) {
                currentRoom = roomList.get(i);
            }
        }
    }
/*
    public ArrayList <Room> listAllRooms(){
        //make an array of type Room
        //dont exaclty need to use
    }*/
/*
    public ArrayList <Item> listAllItems(){

    }
*/
    public Room getCurrentRoom() {
        return(currentRoom);
    }

    public String getCurrentRoomDescription(){
        return(currentRoom.getShortDescription());
    }

    /* you may wish to add additional methods*/
}