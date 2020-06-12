package adventure;

public class Food extends Item implements Edible{
    private static final long serialVersionUID = -3234960400166778987L;
    
    /**
     * calls the default item constructor
     */
    public Food() {
        super();
    }

    /**
     * calls the item constructor
     * @param tempId - item id
     * @param tempName - item name
     * @param tempDescription - item desc
     * @param tempRoom - room that the item is in
     */
    public Food(long tempId, String tempName, String tempDescription, Room tempRoom) {
        super(tempId, tempName, tempDescription, tempRoom);
    }

    /**
     * @return the string of the player eating the item
     */
    public String eat() {
        return("You ate the " + this.getName());
    }
}
