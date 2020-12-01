package Model;

public class Room extends House {

    /**
     * @param name
     * @param doors
     * @param lights
     */

    private MotionDetector motionDetector;

    public SmartThermostat thermostat;
    public SmartAC smartAC;

    public Room(){
        motionDetector = new MotionDetector(this);
        thermostat = new SmartThermostat(this.getName(),0,false);
        thermostat.setHeating_temp(0.1);
        smartAC = new SmartAC(this.getName());
        smartAC.setCooling_temp(0.1);
    }

    private int windows;
    private float temperature;
/*
    public void user_enter(){
        motionDetector.count();
    }
    public void user_exit(){
        motionDetector.deduct();
    }*/

    public MotionDetector getMotionDetector() {
        return motionDetector;
    }

    public void setWindows(int windows) {
        this.windows = windows;
    }

    public int getWindows() {
        return windows;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return "House{" +
                "name='" + getName() + '\'' +
                ", doors=" + getDoors() +
                ", windows=" + windows +
                ", lights=" + getLights() +
                ", temperature=" + temperature +
                '}';
    }
}
