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


    public void setRoomList(ArrayList <JSONObject> newRooms, ArrayList <JSONObject> items) {
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

            //FIXME
            //if the tempObject contains loot, get the JSONArray of it
            //iterate through the JSONArray making it a ArrayList of JSONObjects
            //go through the items in the room (tempObject is the room) and check if the object.get("id") matches any object.get("id") in the items arrayList
            //if it does, send the JSONObject to currentRoom.setRoomItems(Object);
            //make sure you give it the object from the items array because it has more data

            //put items in the room into an array
            if(newRoom.containsKey("loot")) {
                roomItems = new ArrayList();
                JSONArray items = new JSONArray();
                items = (JSONArray) newRoom.get("loot");
            
                JSONObject tempLoot = new JSONObject();
                try {
                    Iterator<JSONObject> iterator = items.iterator();
                    // goes through the items
			            while (iterator.hasNext()) {
                        tempLoot = iterator.next();
                        System.out.println(tempLoot.get("id"));
                        //roomItems.add(tempLoot.get("id"));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
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