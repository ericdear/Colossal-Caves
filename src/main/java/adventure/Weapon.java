package adventure;

public class Weapon extends Item implements Tossable {
    private static final long serialVersionUID = 7822602174928984358L;

    public Weapon() {
        super();
    }

    public Weapon(long tempId, String tempName, String tempDescription, Room tempRoom) {
        super(tempId, tempName, tempDescription, tempRoom);
    }

    public String toss() {
        return("You threw the " + this.getName());
    }
}