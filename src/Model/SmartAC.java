package Model;

public class SmartAC extends SmartModule {
    public static final int MAX_TEMP = 24;
    public static final int MIN_TEMP = 17;
    private double current_temp_AC;
    private boolean isOn;

    public SmartAC(location whereIsModule, double current_temp_AC, boolean isOn) {
        super(whereIsModule);
        this.current_temp_AC = current_temp_AC;
        this.isOn = isOn;
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
}
