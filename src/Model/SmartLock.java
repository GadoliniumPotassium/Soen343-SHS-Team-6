package Model;

public class SmartLock extends SmartModule {

    boolean isLocked;
    private static int idNum=1;


    public SmartLock(String location, boolean isLocked) {
        super("SmartLock#"+(idNum++), location);
        this.isLocked = false;
    }

    /**
     * Toggles Smartlock locked/unlocked state
     */
    @Override
    public void toggleModule() {
        setLocked(!isLocked);
    }

    /**
     * Returns if lock is closed or open
     *
     * @return
     */
    public boolean isLocked() {
        return isLocked;
    }

    /**
     * Toggle lock
     *
     * @param locked
     */
    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    @Override
    public String toString() {
        return "SmartLock{" +
                "isLocked=" + isLocked +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
