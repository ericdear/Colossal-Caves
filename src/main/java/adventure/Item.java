package adventure;

import java.util.ArrayList;

import org.json.simple.JSONObject;

public class Item{
    /* you will need to add some private member variables */
    private long id;
    private String name;
    private String description;
    private Room room;

    /* required public methods */

    public Item() {}

    public Item(long tempId, String tempName, String tempDescription, Room tempRoomId) {
        id = tempId;
        name = tempName;
        description = tempDescription;
        room = tempRoomId;
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
        return(room);
    }

    /* you may wish to add some helper methods*/
}