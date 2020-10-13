package Model;

public class SmartLock extends SmartModule{

    boolean isLocked;

    public SmartLock(location whereIsModule, boolean isLocked) {
        super(whereIsModule);
        this.isLocked = isLocked;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }
}
