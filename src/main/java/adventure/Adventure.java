package adventure;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;


public class Adventure{
    /* you will need to add some private member variables */
    private ArrayList <Room> roomList;
    private ArrayList <Item> allItems;
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

            checkLoot(tempObject, tempRoom, items);
            //checkEntrance
        }
    }


    public void checkLoot(JSONObject tempObject, Room tempRoom, ArrayList <JSONObject> items) {
        //if the tempObject contains loot, get the JSONArray of it
        //iterate through the JSONArray making it a ArrayList of JSONObjects
        if(tempObject.containsKey("loot")) {
            ArrayList <JSONObject> roomItems = new ArrayList();
            JSONArray loot = (JSONArray) tempObject.get("loot");

            for(Object current_item : loot) {
                roomItems.add((JSONObject) current_item);
            }

            //ArrayList roomItems holds loot found in tempObject, ArrayList items holds the list of all items
            //go through the items in the room (tempObject is the room) and check if the object.get("id") matches any object.get("id") in the items arrayList
            //if it does, send the JSONObject to currentRoom.setRoomItems(Object);
            //make sure you give it the object from the items array because it has more data
            allItems = new ArrayList();
            for(int j = 0; j < roomItems.size(); j++) {
                for(int k = 0; k < items.size(); k++) {
                    JSONObject item1 = roomItems.get(j);
                    JSONObject item2 = items.get(k);

                    allItems.add(new Item((long)item2.get("id"), (String)item2.get("name"), (String)item2.get("desc"),(Room) tempRoom));
                    if(item1.get("id") == item2.get("id")) {
                        tempRoom.setRoomItem(item2, tempRoom);
                    }
                }
            }
        }
    }

    public ArrayList <Room> listAllRooms(){
        //make an array of type Room
        //dont exaclty need to use
        return(roomList);
    }

    public ArrayList <Item> listAllItems(){
        return(allItems);
    }

    public Room getCurrentRoom() {
        return(currentRoom);
    }

    public String getCurrentRoomDescription(){
        return(currentRoom.getShortDescription());
    }

    /* you may wish to add additional methods*/
}