package Model;

public class SmartLock extends SmartModule {

    boolean isLocked;

    public SmartLock(String name, String location, boolean isLocked) {
        super(name, location);
        this.isLocked = false;
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
