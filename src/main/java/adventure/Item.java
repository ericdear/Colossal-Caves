package adventure;

import java.util.ArrayList;

import org.json.simple.JSONObject;

public class Item{
    /* you will need to add some private member variables */
    private long id;
    private String name;
    private String description;
    private long roomId;

    /* required public methods */

    public Item(long tempId, String tempName, String tempDescription, long tempRoomId) {
        id = tempId;
        name = tempName;
        description = tempDescription;
        roomId = tempRoomId;
    }

    public void setItemsRoom() {

    }

    public long getId() {
        return(id);
    }

    public String getName(){
        return(name);
    }

    public String getLongDescription(){
        return(description);
    }

    public Room getContainingRoom(){
        //returns a reference to the room that contains the item
        return(roomId);
    }

    /* you may wish to add some helper methods*/
}