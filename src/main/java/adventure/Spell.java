package adventure;

public class Spell extends Item implements Readable {
    private static final long serialVersionUID = -6382777534938715850L;

    /**
     * calls item default constructor
     */
    public Spell() {
        super();
    }

    /**
     * calls the item constructor
     * @param tempId - item id
     * @param tempName - item name
     * @param tempDescription - item desc
     * @param tempRoom - room that the item is in
     */
    public Spell(long tempId, String tempName, String tempDescription, Room tempRoom) {
        super(tempId, tempName, tempDescription, tempRoom);
    }

    /**
     * @return the string of what the item reads (its description)
     */
    public String read() {
        return("The " + this.getName() + " reads: " + this.getLongDescription());
    }
}
