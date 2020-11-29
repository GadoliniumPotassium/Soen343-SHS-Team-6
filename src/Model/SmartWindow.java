package Model;

public class SmartWindow extends SmartModule {

    private boolean isOpen;
    private boolean isObstructed;
    private boolean locked;
    private static int idNum = 0;

    /**
     * Smart Window constructor
     *
     * @param location
     * @param isOpen
     * @param isObstructed
     */
    public SmartWindow(String location, boolean isOpen, boolean isObstructed) {
        super("SmartWindow#" + (idNum++), location);
        this.isOpen = isOpen;
        this.isObstructed = isObstructed;
    }

    /**
     * returns if window is open
     *
     * @return
     */
    public boolean isOpen() {
        return isOpen;
    }

    /**
     * set window open or closed
     *
     * @param open
     */
    public void setOpen(boolean open) {
        isOpen = open;
    }

    /**
     * returns if window is obstructed
     *
     * @return
     */
    public boolean isObstructed() {
        return isObstructed;
    }

    /**
     * set if window is obstructed
     *
     * @param obstructed
     */
    public void setObstructed(boolean obstructed) {
        isObstructed = obstructed;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}
