package Model;

public class SmartLight extends SmartModule {

    private final boolean isDimmable;
    private int lightPercentage;
    private boolean isOn;

    public SmartLight(location whereIsModule, boolean isDimmable, int lightPercentage, boolean isOn) {
        super(whereIsModule);
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
}
