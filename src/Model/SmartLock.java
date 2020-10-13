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

    @Override
    public String toString() {
        return "SmartLock{" +
                "isLocked=" + isLocked +
                ", whereIsModule=" + whereIsModule +
                ", name='" + name + '\'' +
                '}';
    }
}
