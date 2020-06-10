package adventure;

public class Food extends Item implements Edible{
    private static final long serialVersionUID = -3234960400166778987L;
    
    public Food() {
        super();
    }

    public Food(long tempId, String tempName, String tempDescription, Room tempRoom) {
        super(tempId, tempName, tempDescription, tempRoom);
    }

    public String eat() {
        return("You ate the " + this.getName());
    }
}