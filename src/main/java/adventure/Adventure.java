package adventure;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Adventure{
    /* you will need to add some private member variables */
    private ArrayList<Room> roomList;
    private ArrayList<Item> allItems;
    private Room currentRoom;
    

    /* ======== Required public methods ========== */
        /* note,  you don't have to USE all of these
        methods but you do have to provide them.
        We will be using them to test your code */

    public Adventure() {
        roomList = new ArrayList<Room>();
        allItems = new ArrayList<Item>();
    }


    public void setRoomList(ArrayList<JSONObject> newRooms, ArrayList<JSONObject> items) {
        int i;
        JSONObject roomObject;
        Room tempRoom;

        //set up the arrayList of rooms
        for(i = 0; i < newRooms.size(); i++) {
            roomObject = newRooms.get(i);
            tempRoom = new Room(roomObject);
            roomList.add(tempRoom);
        }

        //go through the list of rooms and get the enrances and items
        for(i = 0; i < roomList.size(); i++) {
            //create all the rooms and put into arraylist
            roomObject = newRooms.get(i);
            tempRoom = roomList.get(i);
            //roomList.add(tempRoom);

            //set the current room to the room with the start key
            if(roomObject.containsKey("start")) {
                currentRoom = roomList.get(i);
            }

            //set the items and entrances
            setLoot(roomObject, tempRoom, items);
            setEntrance(roomObject, tempRoom);
        }
    }


    public void setLoot(JSONObject roomObject, Room tempRoom, ArrayList<JSONObject> items) {
        //if the roomObject contains loot, get the JSONArray of it
        //iterate through the JSONArray making it a ArrayList of JSONObjects
        if(roomObject.containsKey("loot")) {
            ArrayList<JSONObject> roomItems = new ArrayList();
            JSONArray loot = (JSONArray) roomObject.get("loot");

            for(Object currentItem : loot) {
                roomItems.add((JSONObject) currentItem);
            }
            addItem(roomItems, items, tempRoom);
        }
    }

    private void addItem(ArrayList<JSONObject> roomItems, ArrayList<JSONObject> items, Room tempRoom) {
        for(int j = 0; j < roomItems.size(); j++) {
            for(int k = 0; k < items.size(); k++) {
                JSONObject item1 = roomItems.get(j);
                JSONObject item2 = items.get(k);

                long itemId = Long.parseLong(item2.get("id").toString());
                String itemName = item2.get("name").toString();
                String itemDesc = item2.get("desc").toString();
                allItems.add(new Item(itemId, itemName, itemDesc,(Room) tempRoom));
                if(item1.get("id").toString().equals(item2.get("id").toString())) {
                    tempRoom.setRoomItem(item2, tempRoom);
                }
            }
        }
    }

    //set the entrances of the current room
    public void setEntrance(JSONObject roomObject, Room tempRoom) {
        if(roomObject.containsKey("entrance")) {
            JSONArray entrances = (JSONArray) roomObject.get("entrance");

            for(Object currentEntrance : entrances) {
                JSONObject entrance = (JSONObject) currentEntrance;
                for(Room connectedRoom : roomList) {
                    if(entrance.get("id").equals(connectedRoom.getId())) {
                        tempRoom.setRoomEntrance(entrance.get("dir").toString(),connectedRoom);
                    }
                    
                }
            }
        }
    }

    //return the list of all rooms
    public ArrayList<Room> listAllRooms(){
        //make an array of type Room
        //dont exaclty need to use
        return(roomList);
    }

    //return the list of all the items
    public ArrayList<Item> listAllItems(){
        return(allItems);
    }

    //get the current room
    public Room getCurrentRoom() {
        return(currentRoom);
    }

    //get the current room description
    public String getCurrentRoomDescription(){
        return(currentRoom.getShortDescription());
    }

    public String toString() {
        return("Current room: " + currentRoom);
    }
}
