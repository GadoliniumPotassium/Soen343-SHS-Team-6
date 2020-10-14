package Model;

public class SmartAC extends SmartModule {
    public static final int MAX_TEMP = 24;
    public static final int MIN_TEMP = 17;
    private double current_temp_AC;
    private boolean isOn;

    public SmartAC(String name, String location) {
        super(name, location);
        this.current_temp_AC = 20;
        this.isOn = false;
    }

    public static int getMaxTemp() {
        return MAX_TEMP;
    }

    public static int getMinTemp() {
        return MIN_TEMP;
    }

    public double getCurrent_temp_AC() {
        return current_temp_AC;
    }

    public void setCurrent_temp_AC(double current_temp_AC) {
        this.current_temp_AC = current_temp_AC;
    }

    public boolean isOn() {
        return isOn;
    }

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
