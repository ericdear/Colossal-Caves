package adventure;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Adventure implements java.io.Serializable{
    private static final long serialVersionUID = 2140486198577919560L;
    /* you will need to add some private member variables */
    private ArrayList<Room> roomList; 
    private ArrayList<Item> allItems;
    private Room currentRoom;
    private Player player;
    

    /* ======== Required public methods ========== */
        /* note,  you don't have to USE all of these
        methods but you do have to provide them.
        We will be using them to test your code */

    /**
     * default constructor for adventure
     * sets up the roomList and allItems arrayList
     */
    public Adventure() {
        roomList = new ArrayList<Room>();
        allItems = new ArrayList<Item>();
        player = null;
    }

    /**
     * setAdventure sets up each room of the adventure
     * @param newRooms : a jsonobject arraylist of all the rooms
     * @param items : a jsonobject arraylist of all items
     */
    public void setAdventure(ArrayList<JSONObject> newRooms, ArrayList<JSONObject> items) {
        JSONObject roomObject;
        Room tempRoom;
        setRoomArrayList(newRooms);

        //go through the list of rooms and get the enrances and items
        for(int i = 0; i < roomList.size(); i++) {
            //create all the rooms and put into arraylist
            roomObject = newRooms.get(i);
            tempRoom = roomList.get(i);

            //set the current room to the room with the start key
            checkForStart(roomObject, i);

            //set the items and entrances
            setLoot(roomObject, tempRoom, items);
            setEntrance(roomObject, tempRoom);
        }
    }

    /**
     * checks each room to find the starting room and set the current room equal to it
     * @param roomObject - the json object of the room
     * @param i - the index of the room in roomList
     */
    public void checkForStart(JSONObject roomObject, int i) {
        if(roomObject.containsKey("start")) {
            currentRoom = roomList.get(i);
        }
    }


    /**
     * sets the player of the game
     * @param newPlayer - the new player of the game
     */
    public void setPlayer(Player newPlayer) {
        player = newPlayer;
    }

    /**
     * @return the player of the game
     */
    public Player getPlayer() {
        return(player);
    }

    /**
     * sets up the arrayList of type Room of all rooms
     * @param newRooms : a jsonobject arraylist of all the rooms
     */
    public void setRoomArrayList(ArrayList<JSONObject> newRooms) {
        Room tempRoom;
        for(JSONObject roomObject : newRooms) {
            tempRoom = new Room(roomObject);
            roomList.add(tempRoom);
        }
    }

    /**
     * sets the new arraylist of all the items
     * @param newAllItems - the new arraylist of all items
     */
    public void setAllItems(ArrayList<Item> newAllItems) {
        allItems = newAllItems;
    }

    /**
     * sets the arraylist of rooms
     * @param newRoomList - the new room list
     */
    public void setRoomList(ArrayList<Room> newRoomList) {
        roomList = newRoomList;
    }

    /**
     * sets up the arrayList of all items
     * @param newItems : an arraylist of all the items
     */
    public void setItemArrayList(ArrayList<Item> newItems) {
        allItems = newItems;
    }

    /**
     * setLoot sets the loot for each room
     * @param roomObject : the jsonobject current room to check if contaings loot
     * @param tempRoom : the Room current Room to add each item
     * @param items : a jsonobject arraylist of all the items in the adventure
     */
    public void setLoot(JSONObject roomObject, Room tempRoom, ArrayList<JSONObject> items) {
        //if the roomObject contains loot, get the JSONArray of it
        //iterate through the JSONArray making it a ArrayList of JSONObjects
        if(roomObject.containsKey("loot")) {
            ArrayList<JSONObject> roomItems = new ArrayList<JSONObject>();
            JSONArray loot = (JSONArray) roomObject.get("loot");

            for(Object currentItem : loot) {
                roomItems.add((JSONObject) currentItem);//FIXME this does this for all rooms so addsmultiple
            }
            addItem(roomItems, items, tempRoom);
        }
    }

    /**
     * adds the item to the correct room
     * goes through each loot, finds corresponding item, puts item into room
     * @param roomItems : a jsonobject arraylist of the roomItems (loot)
     * @param items : a jsonobject arraylist of all the items
     * @param tempRoom : the currennt room that items are being added to
     */
    private void addItem(ArrayList<JSONObject> roomItems, ArrayList<JSONObject> items, Room tempRoom) {
        for(JSONObject item1 : roomItems) {
            for(JSONObject item2 : items) {
                checkItem(item1, item2, tempRoom);
            }
        }
    }

    /**
     * checks if the items and loot match and if so add them to the rooms item list
     * @param loot - the item in the room
     * @param item - the item in the list of items
     * @param tempRoom - the current room
     */
    private void checkItem(JSONObject loot, JSONObject item, Room tempRoom) {
        long itemId = Long.parseLong(item.get("id").toString());
        String itemName = item.get("name").toString();
        String itemDesc = item.get("desc").toString();
        allItems.add(new Item(itemId, itemName, itemDesc,(Room) tempRoom));
        if(loot.get("id").toString().equals(item.get("id").toString())) {
            tempRoom.setRoomItem(item, tempRoom);
        }
    }

    /**
     * set the entrances of the current room
     * @param roomObject : jsonObject of the current room
     * @param tempRoom : type Room of the current room
     */
    public void setEntrance(JSONObject roomObject, Room tempRoom) {
        if(roomObject.containsKey("entrance")) {
            JSONArray entrances = (JSONArray) roomObject.get("entrance");

            for(Object currentEntrance : entrances) {
                addEntrance(tempRoom, currentEntrance);
            }
        }
    }

    private void addEntrance(Room tempRoom, Object currentEntrance) {
        JSONObject entrance = (JSONObject) currentEntrance;
        for(Room connectedRoom : roomList) {
            if(entrance.get("id").equals(connectedRoom.getId())) {
                tempRoom.setRoomEntrance(entrance.get("dir").toString(),connectedRoom);
            }
        }
    }

    /**
     * @return the list of all rooms
     */
    public ArrayList<Room> listAllRooms(){
        return(roomList);
    }

    /**
     * @return the list of all the items
     */
    public ArrayList<Item> listAllItems(){
        return(allItems);
    }

    /**
     * @return the current room
     */
    public Room getCurrentRoom() {
        return(currentRoom);
    }

    /**
     * set the current room
     * @param room : the current room to be set
     */
    public void setCurrentRoom(Room room) {
        currentRoom = room;
    }

    /**
     * @return the current room description
     */
    public String getCurrentRoomDescription(){
        return(currentRoom.getShortDescription());
    }

    /**
     * @return the name of the current room
     */
    public String toString() {
        return("Current room: " + currentRoom);
    }
}
