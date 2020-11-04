package Model;

public class SmartLight extends SmartModule {

    private final boolean isDimmable;
    private int lightPercentage;
    private boolean isOn;

    private String away_mode_timing;

    /**
     * Constructor for Smartlight class
     *
     * @param name
     * @param location
     * @param isDimmable
     * @param lightPercentage
     * @param isOn
     */
    public SmartLight(String name, String location, boolean isDimmable, int lightPercentage, boolean isOn) {
        super(name, location);
        this.isDimmable = isDimmable;
        this.lightPercentage = lightPercentage;
        this.isOn = isOn;
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

    public String getAway_mode_timing() {
        return away_mode_timing;
    }

    public void setAway_mode_timing(String away_mode_timing) {
        this.away_mode_timing = away_mode_timing;
    }
}
