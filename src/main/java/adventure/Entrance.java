package adventure;

public class Entrance {
    private long id;
    private String dir;

    public Entrance() {}

    public Entrance(long newId, String newDir) {
        id = newId;
        dir = newDir;
    }

    public long getId() {
        return(id);
    }

    public String getDir() {
        return(dir);
    }
}