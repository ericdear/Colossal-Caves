package adventure;

import java.util.ArrayList;

public class Player implements java.io.Serializable {
    private static final long serialVersionUID = -2403269607193739060L;
    
    private static ArrayList<Room> rooms;
    private ArrayList<Item> inventory;
    private String name;
    private Room room;

    /**
     * default constructor sets name room and list of all rooms to null
     */
    public Player() {
        this(null,null,null);
    }

    /**
     * constructor to set up player
     * @param newName : the name of the player I guess? lol
     * @param newRoom : the room the player is in
     * @param listAllRooms : the list of all of the rooms
     */
    public Player(String newName, Room newRoom, ArrayList<Room> listAllRooms) {
        inventory = new ArrayList<Item>();
        this.setRoomList(listAllRooms);
        this.setName(newName);
        this.setRoom(newRoom);
    }

    /**
     * setRoomList sets the list of all rooms
     * @param listAllRooms : an arrayList of all the rooms in the adventure
     */
    public void setRoomList(ArrayList<Room> listAllRooms) {
        rooms = listAllRooms;
    }

    /**
     * setRoom sets the current room the player is in
     * @param newRoom : the new room the player is in
     */
    public void setRoom(Room newRoom) {
        room = newRoom;
    }

    /**
     * sets the name of the player
     * @param newName : the name of the player
     */
    public void setName(String newName) {
        name = newName;
    }

    /**
     * sets the inventory
     * @param newInventory : the new inventory for the player
     */
    public void setInventory(ArrayList<Item> newInventory) {
        inventory = newInventory;
    }

    /**
     * @return the name of the player
     */
    public String getName() {
        return(name);
    }

    /**
     * @return the players inventory
     */
    public ArrayList<Item> getInventory() {
        return(inventory);
    }

    /**
     * @return the room that the player is in
     */
    public Room getCurrentRoom() {
        return(room);
    }

    /**
     * @return the name of the saved game
     */
    public String getSaveGameName() { //FIXMEEEE
        return(null);
    }

    /**
     * look either returns the long description of the room if just look is typed
     * or the description of the item if the user typed look ___
     * @param command : the command the user inputed
     * @return the item description or the long description of the room
     */
    public String look(Command command) {
        if(command.hasSecondWord()) {
            return(room.searchItemDescription(command.getNoun()));
        } else {
            return(room.getLongDescription() + ".");
        }
    }

    /**
     * go changes the room the player is in if there is a room in that direction
     * @param command : the command the user inputed
     * @return an error if there is no room in that direction or the name and description of the new room
     */
    public String go(Command command) {
        room.setRoomArray(rooms);
        //check if there is an entrance in that direction, if there is, go there
        if(room.getConnectedRoom(command.getNoun()) == null) {
            return("There is no room in the direction " + command.getNoun() + ".");
        } else {
            room = room.getConnectedRoom(command.getNoun());
            return("You are in " + room.getName() + ", " + room.getShortDescription() + ".");
        }
    }

    /**
     * take picks up the item if it is there
     * @param command : the command the user inputed
     * @return an error if the item is not found or a success message telling the user they picked up the item
     */
    public String take(Command command) {
        Item item = room.removeItem(command.getNoun());
        if(item == null) {
            return("Item " + command.getNoun() + " not found.");
        } else {
            inventory.add(item);
            item.setContainingRoom(null);
            return("You picked up " + command.getNoun() + ".");
        }
    }

    /**
     * inventory shows the user the players inventory
     * @param command : the command the user inputed
     * @return the inventory of the player
     */
    public String inventory(Command command) {
        String inventoryString = "";
        if(inventory.isEmpty()) {
            return("You have nothing in your inventory.\n");
        }
        for(Item item : inventory) {
            inventoryString = inventoryString + " - " + item.getName() + "\n";
        }
        return(inventoryString);
    }

    /**
     * @return the name of the player and what room they are in
     */
    public String toString() {
        return(name + ". " + name + " is in " + room);
    }

}
