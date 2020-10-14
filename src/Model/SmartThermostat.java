package Model;

public class SmartThermostat extends SmartModule {
    final static int MIN_TEMP=0;
    final static int MAX_TEMP=25;
    private double current_temp_heater;
    private boolean  isOn;

    public SmartThermostat(String name, String location, double current_temp_heater, boolean isOn) {
        super(name, location);
        this.current_temp_heater = current_temp_heater;
        this.isOn = isOn;
    }

    public static int getMinTemp() {
        return MIN_TEMP;
    }

    public static int getMaxTemp() {
        return MAX_TEMP;
    }

    public double getCurrent_temp_heater() {
        return current_temp_heater;
    }

    public void setCurrent_temp_heater(double current_temp_heater) {
        this.current_temp_heater = current_temp_heater;
    }

    public boolean isOn() {
        return isOn;
    }

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
