package adventure;

public class JsonTest {
    private boolean missingItem;
    private boolean equalExits;
    private boolean missingRoom;
    private boolean noExits;

    public JsonTest() {
        missingItem = false;
        equalExits = false;
        missingRoom = false;
        noExits = false;
    }

    public void setMissingItem(boolean state) {
        if(state == true) {
            missingItem = state;
        }
    }

    public void setEqualExits(boolean state) {
        if(state == true) {
            equalExits = state;
        }
    }

    public void setMissingRoom(boolean state) {
        if(state == true) {
            missingRoom = state;
        }
    }

    public void setNoExits(boolean state) {
        if(state == true) {
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

    public String test() {
        String errors = "";
        errors = errors + missingItem() + equalExits() + missingRoom() + noExits();
        return(errors);
    }
}