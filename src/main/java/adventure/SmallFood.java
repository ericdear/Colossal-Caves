package adventure;

public class SmallFood extends Food implements Tossable {
    private static final long serialVersionUID = -341416328737178539L;

    public SmallFood() {
        super();
    }

    public SmallFood(long tempId, String tempName, String tempDescription, Room tempRoom) {
        super(tempId, tempName, tempDescription, tempRoom);
    }

    public String toss() {
        return("You tossed the " + this.getName());
    }
}