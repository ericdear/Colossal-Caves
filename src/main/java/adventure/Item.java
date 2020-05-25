package adventure;

public class Item{
    /* you will need to add some private member variables */
    private long id;
    private String name;
    private String description;
    private Room room;

    
    /**
     * default constructor that sets id to 0 and the rest to null
     */
    public Item() {
        this(0,null,null,null);
    }

    /**
     * constructor that sets up the item
     * @param tempId : the id of the new item
     * @param tempName : the name of the new item
     * @param tempDescription : the description of the new item
     * @param tempRoom : the room that contains the new item
     */
    public Item(long tempId, String tempName, String tempDescription, Room tempRoom) {
        this.setId(tempId);
        this.setName(tempName);
        this.setLongDescription(tempDescription);
        this.setContainingRoom(tempRoom);
    }

    /**
     * @return the id of the item
     */
    public long getId() {
        return(id);
    }

    /**
     * set the id of the item
     * @param newId : the new id for the item
     */
    public void setId(long newId) {
        id = newId;
    }

    /**
     * @return the name of the item
     */
    public String getName(){
        return(name);
    }

    /**
     * sets the name of the item
     * @param newName : the new name for the item
     */
    public void setName(String newName) {
        name = newName;
    }

    /**
     * @return the description of the item
     */
    public String getLongDescription(){
        return(description);
    }

    /**
     * sets the description of the item
     * @param newDescription : the new description for the item
     */
    public void setLongDescription(String newDescription) {
        description = newDescription;
    }

    /**
     * @return the room that the item is in
     */
    public Room getContainingRoom(){
        return(room);
    }

    /**
     * sets the room that contains the item
     * useful for setting to null when the player picks up the item
     * @param newRoom : the new room the item is in
     */
    public void setContainingRoom(Room newRoom) {
        room = newRoom;
    }

    /**
     * @return the name of the item
     */
    public String toString() {
        return(name);
    }
}
