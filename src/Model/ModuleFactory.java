package Model;

/**
 * In order to satisfy and have a creational design pattern I am creating this module Factory
 *
 */
public class ModuleFactory {

    /**
     * This method takes a module type and location and creates in and adds it in the system.
     * @param type
     * @param location
     * @return
     */
    public static SmartModule createModule(String type,String location) {
        switch (type) {
            case "SmartLight":
                return new SmartLight(location,false);
            case "SmartAC":
                return new SmartAC(location);
            case "SmartLock":
                return new SmartLock(location,false);
            case "SmartSecurity":
                return new SmartSecurity(location);
            case "SmartThermostat":
                return new SmartThermostat(location,0,false);
            case "SmartWindow":
                return new SmartWindow(location,false,false);
            default:
                System.out.println("You have not given a valid module type");
                break;
        }
        return null;
    }
}
