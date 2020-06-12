package adventure;

public class BrandedClothing extends Clothing implements Readable {
    private static final long serialVersionUID = -4840233771808904574L;

    /**
     * calls the Item default constructor
     */
    public BrandedClothing() {
        super();
    }

    /**
     * calls the item constructor
     * @param tempId - the item id
     * @param tempName - the item name
     * @param tempDescription - the item desc
     * @param tempRoom - the room the item is in
     */
    public BrandedClothing(long tempId, String tempName, String tempDescription, Room tempRoom) {
        super(tempId, tempName, tempDescription, tempRoom);
    }

    /**
     * outputs the description of the item (what the item reads)
     * @return the string of what the item reads
     */
    @Override
    public String read() {
        return("The " + this.getName() + " reads: " + this.getLongDescription());
    }
}
