package Controller;

import Model.*;

import java.util.ArrayList;

public class Controller {
    private ModuleLinkedList moduleList = new ModuleLinkedList();
    private ArrayList<User> userList = new ArrayList<User>(10);
    private boolean isSimRunning = false;


    //This will be filled with method in order to modify the states of the smart devices

    /**
     * Changes the temperature of the AC
     *
     * @param change
     * @param AC
     */
    public void changeACValue(double change, SmartAC AC) {
        AC.setCurrent_temp_AC(AC.getCurrent_temp_AC() + change);
    }

    /**
     * Toggles simulation
     */
    public void toggleSimulation() {
        isSimRunning = !isSimRunning;
    }

    /**
     * Checks if simulation is running
     *
     * @return
     */
    public boolean isSimRunning() {
        return isSimRunning;
    }

    /**
     * Toggles if simulation is running
     *
     * @param AC
     */
    public void toggleACPower(SmartAC AC) {
        AC.setOn(!AC.isOn());
    }

    /**
     * Changes value of thermostat
     *
     * @param value
     * @param Thermos
     */
    public void changeThermosValue(double value, SmartThermostat Thermos) {
        Thermos.setCurrent_temp_heater(Thermos.getCurrent_temp_heater() + value);
    }

    /**
     * Toggles thermostat on off state
     *
     * @param Thermos
     */
    public void toggleThermosPower(SmartThermostat Thermos) {
        Thermos.setOn(!Thermos.isOn());
    }

    /**
     * Toggles light on off state
     *
     * @param light
     */
    public void switchLightState(SmartLight light) {
        light.setOn(!light.isOn());
    }

    /**
     * Changes the percentage of the light dim
     *
     * @param change
     * @param light
     */
    public void changeLightDim(int change, SmartLight light) {
        if (light.isOn() && light.isDimmable()) {
            light.setLightPercentage(light.getLightPercentage() + change);
        }
    }

    /**
     * Toggles the lock state locked unlocked
     *
     * @param lock
     */
    public void changeLockState(SmartLock lock) {
        lock.setLocked(!lock.isLocked());
    }

    /**
     * This method will contact 911 in case of a break in
     *
     * @param sec
     */
    public void contactAuthorities(SmartSecurity sec) {
        if (sec.isInAwayMode() && sec.isSomeoneThere()) {
            System.out.print("Users have been notified.\n" +
                    "How long do you want to wait before contacting authorities?");
            //set timer here
            //waits amount specified
            System.out.println("We are contacting 911");
        }
    }

    /**
     * Toggles away mode and if home is set in away mode, all the doors and windows are locked.
     *
     * @param sec
     */
    public void changeAwayModeState(SmartSecurity sec) {
        sec.setInAwayMode(!sec.isInAwayMode());
        //The following loop runs if the house is in away mode locking all the doors and windows.
        if (sec.isInAwayMode()) {
            moduleList.setWhereAmI(getModuleList().getHead());
            while (moduleList.getWhereAmI() != null) {
                if (moduleList.getWhereAmI().getModule() instanceof SmartLock) {
                    ((SmartLock) moduleList.getWhereAmI().getModule()).setLocked(true);
                } else if (moduleList.getWhereAmI().getModule() instanceof SmartWindow) {
                    ((SmartWindow) moduleList.getWhereAmI().getModule()).setOpen(false);
                }
            }
        }
    }

    /**
     * This method creates a user account
     *
     * @param username
     * @param password
     */
    public void createAccount(String username, String password, User.permissions permissions) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername() == username) {
                System.out.println("Username already exists");
                return;
            }
        }
        User temp = new User(username, password, "outside", permissions);
        userList.add(temp);
        //Add user to txtfile
    }

    /**
     * This method removes a user account
     *
     * @param user
     */
    public void removeAccount(User user) {
        userList.remove(user);
        //Remove user from txtfile
    }

    /**
     * This method toggles the window state
     *
     * @param window
     */
    public void toggleWindowState(SmartWindow window) {
        if (!window.isObstructed())
            window.setOpen(window.isOpen());
        else
            System.out.println("Could not change the window state as the window is obstructed by an object. Please remove it.");
    }

    /**
     * This method toggles the window obstruction state
     *
     * @param window
     */
    public void toggleWindowObstruction(SmartWindow window) {
        window.setObstructed(!window.isObstructed());
    }

    /**
     * This method changes the temperature of the outside world
     *
     * @param temp
     */
    public void changeOutsideTemperature(double temp) {
        SmartModule.setOutsideTemp(temp);
    }

    /**
     * Returns the module list
     *
     * @return
     */
    public ModuleLinkedList getModuleList() {
        return moduleList;
    }

    /**
     * returns the list of users
     *
     * @return
     */
    public ArrayList<User> getUserList() {
        return userList;
    }

    /**
     * Set if the simulation is running or not.
     *
     * @param simRunning
     */
    public void setSimRunning(boolean simRunning) {
        isSimRunning = simRunning;
    }
}
