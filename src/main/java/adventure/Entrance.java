package adventure;

public class Entrance {
    private long id;
    private String dir;

    public Entrance() {}

    public Entrance(long newId, String newDir) {
        id = newId;
        dir = newDir;
    }

    /** this returns the id */
    public long getId() {
        return(id);
    }

    /** this returns the direction */
    public String getDir() {
        return(dir);
    }
}
