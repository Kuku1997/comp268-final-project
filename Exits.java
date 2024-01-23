public class Exits {
    private boolean locked;
    private boolean small;
    private int exitLeadsTo;
    private String key;

    public Exits(boolean locked, boolean small, int exitLeadsTo, String key) {
        this.locked = locked;
        this.small = small;
        this.exitLeadsTo = exitLeadsTo;
        this.key = key;
    }

    public Exits(int exitLeadsTo) {
        this.locked = false;
        this.small = false;
        this.exitLeadsTo = exitLeadsTo;
    }

    public String getKey() {
        return key;
    }

    public void unlockExit() {
        this.locked = false;
    }

    public boolean isLocked() {
        return this.locked;
    }

    public boolean isSmall() {
        return this.small;
    }

    public int getExitLeadsTo() {
        return this.exitLeadsTo;
    }

}