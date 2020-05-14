package adventure;
//import arrayList
import org.json.simple.JSONObject;

public class Room{
    /* you will need to add some private member variables */
    //private JSONObject room;
    private long id;
    private String name;
    private String short_description;
    private String long_description;
    //private ArrayList <Entrance> entrance;
    //private ArrayList <int> lootId;

    /* required public methods */

    public Room(JSONObject newRoom) {
        id = (long) newRoom.get("id");
        name = (String) newRoom.get("name");
        short_description = (String) newRoom.get("short_description");
        long_description = (String) newRoom.get("long_description");
        //entrance
        //loot
    }

/*
    public ArrayList <Item> listItems(){
        //lists all the items in the room

    }*/
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