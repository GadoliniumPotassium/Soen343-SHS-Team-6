package Model;

public class SmartLock extends SmartModule{

    boolean isLocked;

    public SmartLock(String name, String location, boolean isLocked) {
        super(name, location);
        this.isLocked = false;
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
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
