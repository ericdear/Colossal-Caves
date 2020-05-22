package adventure;

import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

public class Room{
    /* you will need to add some private member variables */
    //private JSONObject room;
    private long id;
    private String name;
    private String shortDescription;
    private String longDescription;
    private HashMap<String, Room> roomEntrances;

    private ArrayList<Item> roomItems;
    private ArrayList<Room> allRooms;

    public Room() {}

    //set up the room
    public Room(JSONObject newRoom) {
        roomItems = new ArrayList();
        roomEntrances = new HashMap<String, Room>();
        id = (long) newRoom.get("id");
        name = (String) newRoom.get("name");
        shortDescription = (String) newRoom.get("short_description");
        longDescription = (String) newRoom.get("long_description");
    }

    //list all the items in the room
    public ArrayList<Item> listItems(){
        return(roomItems);
    }

    //set the arrayList of the rooms items
    public void setRoomItem(JSONObject tempItem, Room room) {
        //create a Item object and add it to the array of items in this room
        long itemId = (long)tempItem.get("id");
        String itemName = (String)tempItem.get("name");
        String itemDesc = (String)tempItem.get("desc");
        Item item = new Item(itemId, itemName, itemDesc, (Room) room);//chage this id to room
        
        //add the item to the roomItems ArrayList!
        roomItems.add(item);
    }


    public void setRoomEntrance(String direction, Room room) {
        direction = direction.toLowerCase();
        roomEntrances.put(direction, room);
    }

    //get the id of the room
    public long getId(){
        return(id);
    }

    //get the name of the room
    public String getName(){
        return(name);
    }

    //get the short description
    public String getShortDescription(){
        return(shortDescription);
    }

    //get the long description
    public String getLongDescription(){
        return(longDescription);
    }

    //search the item description of the item that was searched
    public String searchItemDescription(String itemSearched) {
        for(int i = 0; i < roomItems.size(); i++) {
            Item item = roomItems.get(i);
            if(itemSearched.equals(item.getName())) {
                return(item.getLongDescription());
            }
        }
        return("Item " + itemSearched + " not found.");
    }

    //get the connecting room in the direction provided
    public Room getConnectedRoom(String direction) {
        return(roomEntrances.get(direction));
    }

    //set an array of all rooms
    public void setRoomArray(ArrayList<Room> rooms) {
        allRooms = new ArrayList();
        allRooms = rooms;
    }

    public Item removeItem(String itemName) {
        Item item = null;
        for(int i = 0; i < roomItems.size(); i++) {
            if(itemName.equals(roomItems.get(i).getName())) {
                item = roomItems.get(i);
                roomItems.remove(i);
            }
        }
        return(item);
    }
}
