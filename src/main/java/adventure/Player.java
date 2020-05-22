package adventure;

import java.util.ArrayList;

public class Player {
    private static ArrayList<Room> rooms;
    private ArrayList<Item> inventory;
    private String name;
    private Room room;

    public Player(String newName, Room newRoom, ArrayList<Room> listAllRooms) {
        inventory = new ArrayList<Item>();
        rooms = listAllRooms;
        name = newName;
        room = newRoom;
    }

    public void setRoom(Room newRoom) {
        room = newRoom;
    }

    public String getName() {
        return(name);
    }

    public ArrayList<Item> getInventory() {
        return(inventory);
    }

    public Room getCurrentRoom() {
        return(room);
    }

    public String getSaveGameName() {
        return(null);
    }

    public String look(Command command) {
        if(command.hasSecondWord()) {
            return(room.searchItemDescription(command.getNoun()));
        } else {
            return(room.getLongDescription() + ".");
        }
    }

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

    public String take(Command command) {
        Item item = room.removeItem(command.getNoun());
        if(item == null) {
            return("Item " + command.getNoun() + " not found.");
        } else {
            inventory.add(item);
            return("You picked up " + command.getNoun() + ".");
        }
    }

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

}
