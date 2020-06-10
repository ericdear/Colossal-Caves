package adventure;

public class BrandedClothing extends Clothing implements Readable {
    private static final long serialVersionUID = -4840233771808904574L;

    public BrandedClothing() {
        super();
    }

    public BrandedClothing(long tempId, String tempName, String tempDescription, Room tempRoom) {
        super(tempId, tempName, tempDescription, tempRoom);
    }

    @Override
    public String read() {
        return("The " + this.getName() + " reads: " + this.getLongDescription());
    }
}