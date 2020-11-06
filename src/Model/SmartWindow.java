package Model;

public class SmartWindow extends SmartModule {

    private boolean isOpen;
    private boolean isObstructed;
    private static int idNum=1;

    /**
     * Smart Window constructor
     *
     * @param location
     * @param isOpen
     * @param isObstructed
     */
    public SmartWindow( String location, boolean isOpen, boolean isObstructed) {
        super("SmartWindow"+(idNum++), location);
        this.isOpen = isOpen;
        this.isObstructed = isObstructed;
    }

    /**
     * Toggle Windows open closed state
     */
    @Override
    public void toggleModule() {
        if(!isObstructed)
            setOpen(!isOpen);
        else
            System.out.println("Window is obstructed, could not change its state");
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
}
