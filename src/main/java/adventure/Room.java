package adventure;

import org.json.simple.JSONObject;
import java.util.ArrayList;

public class Room{
    /* you will need to add some private member variables */
    //private JSONObject room;
    private long id;
    private String name;
    private String shortDescription;
    private String longDescription;
    private ArrayList<Entrance> roomEntrances;
    private ArrayList<Item> roomItems;
    private ArrayList<Room> allRooms;

    public Room() {}

    //set up the room
    public Room(JSONObject newRoom) {
        roomItems = new ArrayList();
        roomEntrances = new ArrayList();
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

    //set the rooms entrances
    public void setRoomEntrance(JSONObject tempEntrance) {
        Entrance entrance = new Entrance((long)tempEntrance.get("id"), (String)tempEntrance.get("dir"));
        roomEntrances.add(entrance);
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

    //print the list of items in the room
    public void printRoomItems() {
        for(int i = 0; i < roomItems.size(); i++) {
            Item item = roomItems.get(i);
            System.out.println("There is a " + item.getName() + " here.");
        } 
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
        long entranceId = -1;
        direction = direction.toUpperCase();
        //go through and find the id of the room to go to
        for(int i = 0; i < roomEntrances.size(); i++) {
            
            if(direction.equals(roomEntrances.get(i).getDir())) {
                entranceId = roomEntrances.get(i).getId();
            }
        }
        
        //go through the rooms and check if the roomId matches the entranceId
        for(int i = 0; i < allRooms.size(); i++) {
            long roomId = allRooms.get(i).getId();
            if(entranceId == roomId) {
                return(allRooms.get(i));
            }
        }

        return(null);
    }

    //set an array of all rooms
    public void setRoomArray(ArrayList<Room> rooms) {
        allRooms = new ArrayList();
        allRooms = rooms;
    }
}
