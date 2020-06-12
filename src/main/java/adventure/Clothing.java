package adventure;

public class Clothing extends Item implements Wearable {
    private static final long serialVersionUID = 2590167666594789581L;
    private boolean beingWorn;

    /**
     * calls the constructor with id 0, "" name, "" desc, and a null room
     */
    public Clothing() {
        this(0,"","",null);
    }

    /**
     * calls the item constructor and sets variables
     * @param tempId - the item id
     * @param tempName - the item name
     * @param tempDescription - the item desc
     * @param tempRoom - the room the item is in
     */
    public Clothing(long tempId, String tempName, String tempDescription, Room tempRoom) {
        super(tempId, tempName, tempDescription, tempRoom);
        setBeingWorn(false);
    }

    /**
     * @return the description of the player putting on the clothing
     */
    @Override
    public String wear() {
        return("You put on the " + this.getName());
    }

    /**
     * sets the state of the item being worn
     * @param state - the state of the item being worn
     */
    public void setBeingWorn(boolean state) {
        beingWorn = state;
    }

    /**
     * @return the boolean of the item being worn
     */
    public boolean getBeingWorn() {
        return(beingWorn);
    }
}
