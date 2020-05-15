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
    //private ArrayList <Entrance> entrance;
    private ArrayList <Item> roomItems;
    private ArrayList <Item> allItems;

    /* required public methods */

    public Room(JSONObject newRoom) {
        roomItems = new ArrayList();
        id = (long) newRoom.get("id");
        name = (String) newRoom.get("name");
        short_description = (String) newRoom.get("short_description");
        long_description = (String) newRoom.get("long_description");
        //entrance

        //set all loot 
        
        
    }

/*
    public ArrayList <Item> listItems(){
        //lists all the items in the room

    }*/
    /*public void setAllItems(ArrayList <JSONObject> items) {
        JSONObject item;
        for(int i = 0; i < items.size(); i++) {
            item = items.get(i);

            Item temp = new Item( (long)item.get("id"), (String)item.get("name"), (String)item.get("desc"));
            allItems = new ArrayList();
            allItems.add(temp);
        }
    }*/

    public void setRoomItem(JSONObject tempItem) {//FIXME: need to set the room items. need to get the the arraylist of all rooms
        //create a Item object
        //u will have to provide the id name and desc, and the id of the room it belongs to. the id is a long in this class
        Item item = new Item((long)tempItem.get("id"), (String)tempItem.get("name"), (String)tempItem.get("desc"), id);
        
        //add the item to the roomItems ArrayList!
        roomItems.add(item);
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
/*
    public Room getConnectedRoom(String direction) {

    }*/

    /* you may wish to add some helper methods*/
}