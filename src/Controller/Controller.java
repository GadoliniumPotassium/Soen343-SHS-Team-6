package Controller;

import Model.*;

import java.util.ArrayList;

public class Controller {
    private ModuleLinkedList moduleList = new ModuleLinkedList();
    private ArrayList<User> userList = new ArrayList<User>(10);
    private boolean isSimRunning=false;


    //This will be filled with method in order to modify the states of the smart devices

    /**
     * Changes the temperature of the AC
     * @param change
     * @param AC
     */
    public void changeACValue(double change, SmartAC AC) {
        AC.setCurrent_temp_AC(AC.getCurrent_temp_AC() + change);
    }

    /**
     * Toggles simulation
     */
    public void toggleSimulation(){
        isSimRunning=!isSimRunning;
    }

    /**
     * Checks if simulation is running
     * @return
     */
    public boolean isSimRunning() {
        return isSimRunning;
    }

    /**
     * Toggles if simulation is running
     * @param AC
     */
    public void toggleACPower(SmartAC AC) {
        AC.setOn(!AC.isOn());
    }

    /**
     * Changes value of thermostat
     * @param value
     * @param Thermos
     */
    public void changeThermosValue(double value, SmartThermostat Thermos) {
        Thermos.setCurrent_temp_heater(Thermos.getCurrent_temp_heater() + value);
    }

    /**
     * Toggles thermostat on off state
     * @param Thermos
     */
    public void toggleThermosPower(SmartThermostat Thermos) {
        Thermos.setOn(!Thermos.isOn());
    }

    /**
     * Toggles light on off state
     * @param light
     */
    public void switchLightState(SmartLight light) {
        light.setOn(!light.isOn());
    }

    /**
     * Changes the percentage of the light dim
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
     * @param lock
     */
    public void changeLockState(SmartLock lock) {
        lock.setLocked(!lock.isLocked());
    }

    /**
     * This method will contact 911 in case of a break in
     * @param sec
     */
    public void contactAuthorities(SmartSecurity sec) {
        if (sec.isInAwayMode() && sec.isSomeoneThere()) {
            System.out.println("We are contacting 911");
        }
    }

    /**
     * Toggles away mode
     * @param sec
     */
    public void changeAwayModeState(SmartSecurity sec) {
        sec.setInAwayMode(!sec.isInAwayMode());
    }

    /**
     * This method creates a user account
     * @param username
     * @param password
     */
    public void createAccount(String username, String password) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername() == username) {
                System.out.println("Username already exists");
                return;
            }
        }
        User temp = new User(username, password, "outside", User.permissions.partial);
        userList.add(temp);
    }

    /**
     * This method removes a user account
     * @param user
     */
    public void removeAccount(User user) {
        userList.remove(user);
    }

    /**
     * This method toggles the window state
     * @param window
     */
    public void toggleWindowState(SmartWindow window) {
        if (window.isObstructed())
            window.setOpen(window.isOpen());
    }

    /**
     * This method toggles the window obstruction state
     * @param window
     */
    public void toggleWindowObstruction(SmartWindow window) {
        window.setObstructed(!window.isObstructed());
    }

    /**
     * This method changes the temperature of the outside world
     * @param temp
     */
    public void changeOutsideTemperature(double temp){
        SmartModule.setOutsideTemp(temp);
    }

    public ModuleLinkedList getModuleList() {
        return moduleList;
    }

    public void setModuleList(ModuleLinkedList moduleList) {
        this.moduleList = moduleList;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }

    public void setSimRunning(boolean simRunning) {
        isSimRunning = simRunning;
    }
}
