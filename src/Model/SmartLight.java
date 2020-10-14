package Model;

public class SmartLight extends SmartModule {

    private final boolean isDimmable;
    private int lightPercentage;
    private boolean isOn;

    public SmartLight(String name, String location, boolean isDimmable, int lightPercentage, boolean isOn) {
        super(name, location);
        this.isDimmable = isDimmable;
        this.lightPercentage = lightPercentage;
        this.isOn = isOn;
    }

    public boolean isDimmable() {
        return isDimmable;
    }

    public int getLightPercentage() {
        return lightPercentage;
    }

    public void setLightPercentage(int lightPercentage) {
        this.lightPercentage = lightPercentage;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    @Override
    public String toString() {
        return "SmartLight{" +
                "isDimmable=" + isDimmable +
                ", lightPercentage=" + lightPercentage +
                ", isOn=" + isOn +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
