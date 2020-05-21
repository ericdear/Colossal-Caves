package adventure;

public class Entrance {
    private long id;
    private String dir;

    public Entrance() {}

    //set up the entrance
    public Entrance(long newId, String newDir) {
        id = newId;
        dir = newDir;
    }

    /**
     * @return the id of the entrance
     */
    public long getId() {
        return(id);
    }

    /**
     * @return the direction the entrance is
     */
    public String getDir() {
        return(dir);
    }
}
