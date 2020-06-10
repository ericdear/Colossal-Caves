package adventure;

public class Spell extends Item implements Readable {
    private static final long serialVersionUID = -6382777534938715850L;

    public Spell() {
        super();
    }

    public Spell(long tempId, String tempName, String tempDescription, Room tempRoom) {
        super(tempId, tempName, tempDescription, tempRoom);
    }

    public String read() {
        return("The " + this.getName() + " reads: " + this.getLongDescription());
    }
}