package adventure;

public class Item{
    /* you will need to add some private member variables */
    private long id;
    private String name;
    private String description;
    private Room room;

    /* required public methods */

    public Item() {}

    //set up the item
    public Item(long tempId, String tempName, String tempDescription, Room tempRoomId) {
        id = tempId;
        name = tempName;
        description = tempDescription;
        room = tempRoomId;
    }

    //get the id of the item
    public long getId() {
        return(id);
    }

    //get the name of the item
    public String getName(){
        return(name);
    }

    //get the long description
    public String getLongDescription(){
        return(description);
    }

    //get the room that the item is in
    public Room getContainingRoom(){
        return(room);
    }
}
