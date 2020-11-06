package Model;

public class SmartAC extends SmartModule {
    public static final int MAX_TEMP = 24;
    public static final int MIN_TEMP = 17;
    private double current_temp_AC;
    private boolean isOn;
    private static int idNum=1;


    public SmartAC(String location) {
        super("SmartAC#"+(idNum++), location);
        this.current_temp_AC = 20;
        this.isOn = false;
    }

    @Override
    public void toggleModule() {
        setOn(!isOn);
    }

    /**
     * Returns the maximum temperature of the AC
     *
     * @return
     */
    public static int getMaxTemp() {
        return MAX_TEMP;
    }

    /**
     * Returns the minimum temperature of the AC
     *
     * @return
     */
    public static int getMinTemp() {
        return MIN_TEMP;
    }

    /**
     * Returns the current temperature of the AC
     *
     * @return
     */
    public double getCurrent_temp_AC() {
        return current_temp_AC;
    }

    /**
     * Sets the temperature of the AC
     *
     * @param current_temp_AC
     */
    public void setCurrent_temp_AC(double current_temp_AC) {
        this.current_temp_AC = current_temp_AC;
    }

    /**
     * Returns if AC is on
     *
     * @return
     */
    public boolean isOn() {
        return isOn;
    }

    /**
     * Toggle on or off AC
     *
     * @param on
     */
    public void setOn(boolean on) {
        isOn = on;
    }

    @Override
    public String toString() {
        return "SmartAC{" +
                "current_temp_AC=" + current_temp_AC +
                ", isOn=" + isOn +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
