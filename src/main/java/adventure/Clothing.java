package adventure;

public class Clothing extends Item implements Wearable {
    private static final long serialVersionUID = 2590167666594789581L;

    public Clothing() {
        super();
    }

    public Clothing(long tempId, String tempName, String tempDescription, Room tempRoom) {
        super(tempId, tempName, tempDescription, tempRoom);
    }

    @Override
    public String wear() {
        return("You put on the " + this.getName());
    }
}