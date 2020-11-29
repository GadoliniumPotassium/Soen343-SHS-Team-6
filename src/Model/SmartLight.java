package Model;

public class SmartLight extends SmartModule {

    private final boolean isDimmable;
    private int lightPercentage;
    private boolean isOn;
    private String awayModeTiming;
    private static int idNum=0;

    /**
     * Constructor for Smartlight class
     *
     //* @param name
     * @param location
     * @param isDimmable
     * @param lightPercentage
     * @param isOn
     */
    public SmartLight(String location, boolean isDimmable, int lightPercentage, boolean isOn) {
        super("SmartLight#"+(idNum++), location);
        this.isDimmable = isDimmable;
        this.lightPercentage = lightPercentage;
        this.isOn = isOn;

        setAwayModeTiming("00:00,00:00");
    }

    /**
     * Returns if this light is dimmable
     *
     * @return
     */
    public boolean isDimmable() {
        return isDimmable;
    }

    /**
     * If light is dimmable it returns the percentage
     *
     * @return
     */
    public int getLightPercentage() {
        return lightPercentage;
    }

    /**
     * If the light is dimmable this will set the light percentage
     *
     * @param lightPercentage
     */
    public void setLightPercentage(int lightPercentage) {
        this.lightPercentage = lightPercentage;
    }

    /**
     * Returns if the light is on
     *
     * @return
     */
    public boolean isOn() {
        return isOn;
    }

    /**
     * Toggle on or off light
     *
     * @param on
     */
    public void setOn(boolean on) {
        isOn = on;
    }

    public void setAwayModeTiming(String awayModeTiming) {
        this.awayModeTiming = awayModeTiming;
    }

    public String getAwayModeTiming() {
        return awayModeTiming;
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