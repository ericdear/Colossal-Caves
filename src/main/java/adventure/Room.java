package adventure;

import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

public class Room implements java.io.Serializable {
    private static final long serialVersionUID = -8423856135517312624L;
    /* you will need to add some private member variables */
    //private JSONObject room;
    private long id;
    private String name;
    private String shortDescription;
    private String longDescription;
    private HashMap<String, Room> roomEntrances;

    private ArrayList<Item> roomItems;
    private ArrayList<Room> allRooms;

    /**
     * default constructor setting everything to null and the id of the room to 0
     */
    public Room() {
        roomItems = new ArrayList<Item>();
        roomEntrances = new HashMap<String, Room>();
        allRooms = new ArrayList<Room>();
        this.setId(0);
        this.setName("");
        this.setShortDescription("");
        this.setLongDescription("");
    }

    /**
     * room constuctor that sets up the room
     * @param newRoom : a json object of the room
     */
    public Room(JSONObject newRoom) {
        roomItems = new ArrayList<Item>();
        roomEntrances = new HashMap<String, Room>();
        allRooms = new ArrayList<Room>();
        this.setId(Long.parseLong(newRoom.get("id").toString()));
        this.setName(newRoom.get("name").toString());
        this.setShortDescription(newRoom.get("short_description").toString());
        this.setLongDescription(newRoom.get("long_description").toString());
    }

    /**
     * sets the name of the room
     * @param newName : the new name for the room
     */
    public void setName(String newName) {
        name = newName;
    }

    /**
     * sets the id of the room
     * @param newId : the new id for the room
     */
    public void setId(long newId) {
        id = newId;
    }

    /**
     * sets the short description of the room
     * @param newShort : the new short description of the room
     */
    public void setShortDescription(String newShort) {
        shortDescription = newShort;
    }

    /**
     * sets the long description of the room
     * @param newLong : the new long description of the room
     */
    public void setLongDescription(String newLong) {
        longDescription = newLong;
    }

    /**
     * @return the list of the items in the room
     */
    public ArrayList<Item> listItems(){
        return(roomItems);
    }

    /**
     * sets the items in the room
     * @param newRoomItems - the new list of items
     */
    public void setRoomItems(ArrayList<Item> newRoomItems) {
        roomItems = newRoomItems;
    }

    /**
     * add an item to the arrayList of items in the room
     * @param tempItem : a json object of the item
     * @param room : the current room
     */
    public void setRoomItem(JSONObject tempItem, Room room) {
        //create a Item object and add it to the array of items in this room
        long itemId = Long.parseLong(tempItem.get("id").toString());
        String itemName = tempItem.get("name").toString();
        String itemDesc = tempItem.get("desc").toString();
        Item item = checkItemKind(itemId, itemName, itemDesc, (Room) room, tempItem);
        
        //add the item to the roomItems ArrayList!
        roomItems.add(item);
    }

    /**
     * adds the item to the list of items in the room
     * @param item - the item to be added
     */
    public void addItem(Item item) {
        roomItems.add(item);
    }

    /**
     * checks what kind of item it is
     * @param itemId - item id
     * @param itemName item name
     * @param itemDesc item description
     * @param tempRoom room item is in
     * @param item jsonobject of item
     * @return the item
     */
    public Item checkItemKind(long itemId, String itemName, String itemDesc, Room tempRoom, JSONObject item) {
        if(checkFood(itemId, itemName, itemDesc,(Room) tempRoom, item) != null) {
            return(checkFood(itemId, itemName, itemDesc,(Room) tempRoom, item));
        } else if(checkClothing(itemId, itemName, itemDesc,(Room) tempRoom, item) != null) {
            return(checkClothing(itemId, itemName, itemDesc,(Room) tempRoom, item));
        } else {
            return(checkItemKind2(itemId, itemName, itemDesc, (Room) tempRoom, item));
        }
    }

    /**
     * check what item is and create it
     * @param itemId - item id
     * @param itemName item name
     * @param itemDesc item description
     * @param tempRoom room item is in
     * @param item jsonobject of item
     * @return the item
     */
    private Item checkItemKind2(long itemId, String itemName, String itemDesc, Room tempRoom, JSONObject item) {
        if(checkWeapon(itemId, itemName, itemDesc,(Room) tempRoom, item) != null) {
            return(checkWeapon(itemId, itemName, itemDesc,(Room) tempRoom, item));
        } else if(checkSpell(itemId, itemName, itemDesc,(Room) tempRoom, item) != null) {
            return(checkSpell(itemId, itemName, itemDesc,(Room) tempRoom, item));
        } else {
            return(new Item(itemId, itemName, itemDesc,(Room) tempRoom));
        }
    }

    /**
     * checks if the item is clothing or branded clothing
     * @param itemId - item id
     * @param itemName item name
     * @param itemDesc item description
     * @param tempRoom room item is in
     * @param item jsonobject of item
     * @return the item made
     */
    private Item checkClothing(long itemId, String itemName, String itemDesc, Room tempRoom, JSONObject item) {
        boolean bothTrue = item.containsKey("wearable") && item.containsKey("readable");
        if(bothTrue && item.get("wearable").equals(true) && item.get("readable").equals(true)) {
            return(new BrandedClothing(itemId, itemName, itemDesc,(Room) tempRoom));
        } else if(item.containsKey("wearable") && item.get("wearable").equals(true)) {
            return(new Clothing(itemId, itemName, itemDesc,(Room) tempRoom));
        } else {
            return(null);
        }
    }

    /**
     * checks if item is a wepon
     * @param itemId - item id
     * @param itemName item name
     * @param itemDesc item description
     * @param tempRoom room item is in
     * @param item jsonobject of item
     * @return the item created
     */
    private Item checkWeapon(long itemId, String itemName, String itemDesc, Room tempRoom, JSONObject item) {
        if(item.containsKey("tossable") && item.get("tossable").equals(true)) {
            return(new Weapon(itemId, itemName, itemDesc,(Room) tempRoom));
        } else {
            return(null);
        }
    }

    /**
     * checks if the item is small food or food
     * @param itemId - item id
     * @param itemName item name
     * @param itemDesc item description
     * @param tempRoom room item is in
     * @param item jsonobject of item
     * @return the item created
     */
    private Item checkFood(long itemId, String itemName, String itemDesc, Room tempRoom, JSONObject item) {
        boolean bothTrue = item.containsKey("edible") && item.containsKey("tossable");
        if(bothTrue && item.get("edible").equals(true) && item.get("tossable").equals(true)) {
            return(new SmallFood(itemId, itemName, itemDesc,(Room) tempRoom));
        } else if(item.containsKey("edible") && item.get("edible").equals(true)) {
            return(new Food(itemId, itemName, itemDesc,(Room) tempRoom));
        } else {
            return(null);
        }
    }

    /**
     * checks if the item is a spell
     * @param itemId - item id
     * @param itemName item name
     * @param itemDesc item description
     * @param tempRoom room item is in
     * @param item jsonobject of item
     * @return the item created
     */
    private Item checkSpell(long itemId, String itemName, String itemDesc, Room tempRoom, JSONObject item) {
        if(item.containsKey("readable") && item.get("readable").equals(true)) {
            return(new Spell(itemId, itemName, itemDesc,(Room) tempRoom));
        } else {
            return(null);
        }
    }


    /**
     * puts an entrance into the room entrance hashmap
     * @param direction : the direction the entrance is in
     * @param room : the room it goes to
     */
    public void setRoomEntrance(String direction, Room room) {
        direction = direction.toLowerCase();
        roomEntrances.put(direction, room);
    }

    /**
     * sets all the entrances for the rooms
     * @param entrances : the hashmap of directions to Rooms
     */
    public void setRoomEntrances(HashMap<String, Room> entrances) {
        roomEntrances = entrances;
    }

    /**
     * @return the id of the room
     */
    public long getId(){
        return(id);
    }

    /**
     * @return the name of the room
     */
    public String getName(){
        return(name);
    }

    /**
     * @return the short description of the room
     */
    public String getShortDescription(){
        return(shortDescription);
    }

    /**
     * @return the long description of the room
     */
    public String getLongDescription(){
        return(longDescription);
    }

    //search the item description of the item that was searched
    /**
     * searches for the item and returns the description
     * @param itemSearched : the item name the user is looking for
     * @return the item description or a message saying the item was not found
     */
    public String searchItemDescription(String itemSearched) {
        for(int i = 0; i < roomItems.size(); i++) {
            Item item = roomItems.get(i);
            if(itemSearched.equals(item.getName())) {
                return(item.getLongDescription());
            }
        }
        return("Item " + itemSearched + " not found.");
    }

    /**
     * getConnectedRoom finds the room that you would be in if you went that direction
     * @param direction : the direction the user wants to go
     * @return the room in that direction
     */
    public Room getConnectedRoom(String direction) {
        direction = direction.toLowerCase();
        return(roomEntrances.get(direction));
    }

    /**
     * sets the array of all rooms in the adventure
     * @param rooms : an arrayList of all the rooms
     */
    public void setAllRooms(ArrayList<Room> rooms) {
        allRooms = rooms;
    }

    /**
     * gets the list of rooms
     * @return the list of rooms
     */
    public ArrayList<Room> getRoomArray() {
        return(allRooms);
    }

    /**
     * removeItem removes an item from the room
     * used for when a player picks up an item
     * @param itemName : the name of the item the user wants to remove
     * @return the item that was removed or null if the item was not found
     */
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

    /**
     * @return the name of the room
     */
    public String toString() {
        return(name);
    }
}
