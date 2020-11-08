package Model;

public abstract class SmartModule {
    protected final String name;
    protected String location;
    protected static double outsideTemp = 20;

    public SmartModule(String name, String location) {
        this.name = name;
        this.location = location;
    }

    /**
     * Returns the module name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the location where the smart module is
     * @return
     */
    public String getLocation() {
        return location;
    }

    /**
     * Can set or reset the location where the smart module is
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * This method can pull what the outside temperature is
     * @return
     */
    public static double getOutsideTemp() {
        return outsideTemp;
    }

    /**
     * This method can set the temperature outside the house in the simulation
     * @param outsideTemp
     */
    public static void setOutsideTemp(double outsideTemp) {
        SmartModule.outsideTemp = outsideTemp;
    }

    /**
     * The purpose of this method is to toggle the state of the module.
     */
    public abstract void togglePower();

    @Override
    public String toString() {
        return "SmartModule{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

}
