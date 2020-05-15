package adventure;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

import java.util.ArrayList;

public class Room{
    /* you will need to add some private member variables */
    //private JSONObject room;
    private long id;
    private String name;
    private String short_description;
    private String long_description;
    private ArrayList <Entrance> roomEntrances;
    private ArrayList <Item> roomItems;

    /* required public methods */

    public Room(JSONObject newRoom) {
        roomItems = new ArrayList();
        roomEntrances = new ArrayList();
        id = (long) newRoom.get("id");
        name = (String) newRoom.get("name");
        short_description = (String) newRoom.get("short_description");
        long_description = (String) newRoom.get("long_description");
    }


    public ArrayList <Item> listItems(){ //need to have
        //lists all the items in the room
        return(roomItems);
    }

    public void setRoomItem(JSONObject tempItem, Room room) {//FIXME make it send in the Room
        //create a Item object
        //u will have to provide the id name and desc, and the id of the room it belongs to. the id is a long in this class
        Item item = new Item((long)tempItem.get("id"), (String)tempItem.get("name"), (String)tempItem.get("desc"),(Room) room);//chage this id to room
        
        //add the item to the roomItems ArrayList!
        roomItems.add(item);
    }

    public void setRoomEntrances(JSONObject tempEntrance) {
        Entrance entrance = new Entrance((long)tempEntrance.get("id"), (String)tempEntrance.get("dir"));
        roomEntrances.add(entrance);
    }

    public long getId(){
        return(id);
    }

    public String getName(){
        return(name);
    }

    public String getShortDescription(){
        return(short_description);
    }

    public String getLongDescription(){
        return(long_description);
    }

    public void printRoomItems() {
        for(int i = 0; i < roomItems.size(); i++) {
            Item item = roomItems.get(i);
            System.out.println("There is a " + item.getName() + " here.");
        }
    }

    public String searchItemDescription(String itemSearched) {
        for(int i = 0; i < roomItems.size(); i++) {
            Item item = roomItems.get(i);
            if(itemSearched.equals(item.getName())) {
                return(item.getLongDescription());
            }
        }
        return("Item " + itemSearched + " not found.");
    }
/*
    public Room getConnectedRoom(String direction) {
        
    }*/

    /* you may wish to add some helper methods*/
}