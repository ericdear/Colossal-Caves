package adventure;

import java.util.ArrayList;
import java.util.HashMap;

public class JsonTest implements java.io.Serializable {
    private static final long serialVersionUID = -7177533858802588594L;
    
    private boolean missingItem;
    private boolean equalExits;
    private boolean missingRoom;
    private boolean noExits;
    private static final HashMap<String,String> DIRECTIONMAP = new HashMap<String,String>();
    static {
        DIRECTIONMAP.put("N", "S");
        DIRECTIONMAP.put("S", "N");
        DIRECTIONMAP.put("E", "W");
        DIRECTIONMAP.put("W", "E");
        DIRECTIONMAP.put("UP", "DOWN");
        DIRECTIONMAP.put("DOWN", "UP");
    }

    /**
     * sets all booleans to false cuz no errors
     */
    public JsonTest() {
        missingItem = false;
        equalExits = false;
        missingRoom = false;
        noExits = false;
    }

    /**
     * sets the missing item to true if true is passed in
     * @param state - the state of the error
     */
    public void setMissingItem(boolean state) {
        if(state) {
            missingItem = state;
        }
    }

    /**
     * set the equal exits to true if true is passed in
     * @param state - the state of the error
     */
    public void setEqualExits(boolean state) {
        if(state) {
            equalExits = state;
        }
    }

    /**
     * sets the missing room to true if true is passed in
     * @param state - the state of the error
     */
    public void setMissingRoom(boolean state) {
        if(state) {
            missingRoom = state;
        }
    }

    /**
     * sets the no exits to true if true is passed in
     * @param state - the state of the error
     */
    public void setNoExits(boolean state) {
        if(state) {
            noExits = state;
        }
    }

    private String missingItem() {
        if(!missingItem) {
            return("");
        }
        return("There is an loot without a corresponding item.\n");
    }

    private String equalExits() {
        if(!equalExits) {
            return("");
        }
        return("There is a room without correct exits.\n");
    }

    private String missingRoom() {
        if(!missingRoom) {
            return("");
        }
        return("There is a missing room.\n");
    }

    private String noExits() {
        if(!noExits) {
            return("");
        }
        return("There is a room with no exits.\n");
    }

    /**
     * @return the string of errors
     */
    public String test() {
        String errors = "";
        errors = errors + missingItem() + equalExits() + missingRoom() + noExits();
        return(errors);
    }


    /**
     * tests if the entrances of rooms match
     * @param roomList - the list of all rooms in the adventure
     */
    public void testEntrances(ArrayList<Room> roomList) {
        for(Room room : roomList) {
            for(HashMap.Entry<String,String> entry : DIRECTIONMAP.entrySet()) {
                Room connectedRoom = room.getConnectedRoom(entry.getKey());
                if(connectedRoom != null) {
                    if(connectedRoom.getConnectedRoom(entry.getValue()) != room) {
                        setEqualExits(true);
                    }
                }
            }
        }
    }

}
