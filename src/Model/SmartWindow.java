package Model;

public class SmartWindow extends SmartModule{

    private boolean isOpen;
    private boolean isObstructed;

    public SmartWindow(String name, String location, boolean isOpen, boolean isObstructed) {
        super(name, location);
        this.isOpen = isOpen;
        this.isObstructed = isObstructed;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public boolean isObstructed() {
        return isObstructed;
    }

    public void setObstructed(boolean obstructed) {
        isObstructed = obstructed;
    }
}
