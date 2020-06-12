package adventure;

public class SmallFood extends Food implements Tossable {
    private static final long serialVersionUID = -341416328737178539L;

    /**
     * calls item default constructor
     */
    public SmallFood() {
        super();
    }

    /**
     * calls the item constructor
     * @param tempId - item id
     * @param tempName - item name
     * @param tempDescription - item desc
     * @param tempRoom - room that the item is in
     */
    public SmallFood(long tempId, String tempName, String tempDescription, Room tempRoom) {
        super(tempId, tempName, tempDescription, tempRoom);
    }

    /**
     * @return the string of the item being tossed
     */
    public String toss() {
        return("You tossed the " + this.getName());
    }
}
