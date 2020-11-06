package Model;

public class SmartLight extends SmartModule {

    private boolean isOn;
    private static int idNum=1;

    /**
     * Constructor for Smartlight class
     *
     * @param location
     * @param isOn
     */
    public SmartLight(String location, boolean isOn) {
        super("SmartLight#"+(idNum++), location);
        this.isOn = isOn;
    }


    /**
     * Toggles smart light on/off state.
     */
    @Override
    public void toggleModule() {
        setOn(!isOn);
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
                ", isOn=" + isOn +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
