package Model;

public class SmartThermostat extends SmartModule {
    final static int MIN_TEMP = 0;
    final static int MAX_TEMP = 25;
    private double current_temp_heater;
    private boolean isOn;

    /**
     * SmartThermostat constructor
     *
     * @param name
     * @param location
     * @param current_temp_heater
     * @param isOn
     */
    public SmartThermostat(String name, String location, double current_temp_heater, boolean isOn) {
        super(name, location);
        this.current_temp_heater = current_temp_heater;
        this.isOn = isOn;
    }

    /**
     * Returns minimum temp of the thermostat
     *
     * @return
     */
    public static int getMinTemp() {
        return MIN_TEMP;
    }

    /**
     * Returns maximum temperature of teh smart thermostat
     *
     * @return
     */
    public static int getMaxTemp() {
        return MAX_TEMP;
    }

    /**
     * returns the current temperature the thermostat was set at
     *
     * @return
     */
    public double getCurrent_temp_heater() {
        return current_temp_heater;
    }

    /**
     * set the temperature of the thermostat
     *
     * @param current_temp_heater
     */
    public void setCurrent_temp_heater(double current_temp_heater) {
        this.current_temp_heater = current_temp_heater;
    }

    /**
     * return is the thermostat is On
     *
     * @return
     */
    public boolean isOn() {
        return isOn;
    }

    /**
     * Toggle thermostat
     *
     * @param on
     */
    public void setOn(boolean on) {
        isOn = on;
    }

    @Override
    public String toString() {
        return "SmartThermostat{" +
                "current_temp_heater=" + current_temp_heater +
                ", isOn=" + isOn +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
