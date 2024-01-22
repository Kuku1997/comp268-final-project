public class Exits {
    private boolean locked;
    private boolean small;
    private int exitLeadsTo;

    public Exits(boolean locked, boolean small, int exitLeadsTo) {
        this.locked = locked;
        this.small = small;
        this.exitLeadsTo = exitLeadsTo;
    }

    public Exits(int exitLeadsTo) {
        this.locked = false;
        this.small = false;
        this.exitLeadsTo = exitLeadsTo;
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