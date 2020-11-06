package Controller;

import Model.*;

import javax.swing.*;
import java.util.ArrayList;

public class Controller {
    private ModuleLinkedList moduleList = new ModuleLinkedList();
    private ArrayList<User> userList = new ArrayList<User>(10);
    private boolean isSimRunning = false;
    private FileWriter fw = new FileWriter();

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
        //Log command
    }

    /**
     * Checks if simulation is running
     *
     * @return
     */
    public boolean isSimRunning() {
        return isSimRunning;
        //Log command
    }

    /**
     * Toggles if simulation is running
     *
     * @param AC
     */
    public void toggleACPower(SmartAC AC) {
        AC.setOn(!AC.isOn());
        //Log command
    }

    /**
     * Changes value of thermostat
     *
     * @param value
     * @param Thermos
     */
    public void changeThermosValue(double value, SmartThermostat Thermos) {
        Thermos.setCurrent_temp_heater(Thermos.getCurrent_temp_heater() + value);
        //Log command
    }

    /**
     * Toggles thermostat on off state
     *
     * @param Thermos
     */
    public void toggleThermosPower(SmartThermostat Thermos) {
        Thermos.setOn(!Thermos.isOn());
        //Log command
    }

    /**
     * Toggles light on off state
     *
     * @param light
     */
    public void switchLightState(SmartLight light) {
        light.setOn(!light.isOn());
        //Log command
    }

    /**
     * Toggles the lock state locked unlocked
     *
     * @param lock
     */
    public void changeLockState(SmartLock lock) {
        lock.setLocked(!lock.isLocked());
        //Log command
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
            //My brain broke, this is the best I could come up with.
            String t = JOptionPane.showInputDialog("Activity has been detected in your house when in away mode," +
                    " </br>how long before we contact the authorities? (insert answer in seconds please)", "");

            long time = 0;
            if (t.matches("-?\\d+(\\.\\d+)?")) {
                time = Long.valueOf(t) * 1000;
            } else {
                time = 0;
            }
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("We are contacting 911");
            //Log command
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
                    //Log command
                } else if (moduleList.getWhereAmI().getModule() instanceof SmartWindow) {
                    ((SmartWindow) moduleList.getWhereAmI().getModule()).setOpen(false);
                    //Log command
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
        //Log command
    }

    /**
     * This method removes a user account
     *
     * @param user
     */
    public void removeAccount(User user) {
        userList.remove(user);
        //Remove user from txtfile
        //Log command
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
        //Log command
    }

    /**
     * This method toggles the window obstruction state
     *
     * @param window
     */
    public void toggleWindowObstruction(SmartWindow window) {
        window.setObstructed(!window.isObstructed());
        //Log command
    }

    /**
     * This method changes the temperature of the outside world
     *
     * @param temp
     */
    public void changeOutsideTemperature(double temp) {
        SmartModule.setOutsideTemp(temp);
        //Log command
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

    /**
     * This method serves to create a new module in the home
     *
     * @param type
     * @param location
     */
    public void createModule(String type, String location) {
        SmartModule m = ModuleFactory.createModule(type, location);
        if (m == null) {
            System.out.println("Object could not be created and will not be added");
            return;
        }
        moduleList.addModuleToList(m);
        //log module creation.
    }
}
