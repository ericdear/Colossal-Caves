package adventure;

public class Weapon extends Item implements Tossable {
    private static final long serialVersionUID = 7822602174928984358L;

    /**
     * calls the item default constructor
     */
    public Weapon() {
        super();
    }

    /**
     * calls the item constructor
     * @param tempId - item id
     * @param tempName - item name
     * @param tempDescription - item desc
     * @param tempRoom - room that the item is in
     */
    public Weapon(long tempId, String tempName, String tempDescription, Room tempRoom) {
        super(tempId, tempName, tempDescription, tempRoom);
    }

    /**
     * @return the string of the player tossing the item
     */
    public String toss() {
        return("You threw the " + this.getName());
    }
}
