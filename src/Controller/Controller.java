package Controller;

import Model.*;

import javax.swing.*;
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
        FileManipulator.append("log.txt", "toggled simulation state.");
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
     * Changes value of thermostat, this method works by incrementing the value to the previous temperature.
     * <br/> example, you the set temp is 20, the value you give will be added or subtracted to 20.
     *
     * @param value
     * @param Thermos
     */
    public void changeThermosValue(double value, SmartThermostat Thermos) {
        Thermos.setCurrent_temp_heater(Thermos.getCurrent_temp_heater() + value);
        FileManipulator.append("log.txt", "Changed the value of thermostat" + Thermos.getName() + " in " + Thermos.getLocation() + " to" + Thermos.getCurrent_temp_heater());
    }

    /**
     * This method takes in a module and using polymorphism figures out which type of module it is and toggles its on/off,
     * locked/unlocked state. (Will not toggle the obstruction state of a window, must use toggleWindowObstruction to do that)
     *
     * <em>Special case: If smart security is set on, it will lock every door and window</em>
     * @param module
     */
    public void toggleOnOffStateModule(SmartModule module) {
        module.toggleModule();
        FileManipulator.append("log.txt", "We changed the state of " + module.getName() + " in " + module.getLocation());
        //This will toggle set every single door and window to locked and closed if SmartSecurity has been set to away mode.
        if (module instanceof SmartSecurity) {
            if (((SmartSecurity) module).isInAwayMode()) {
                moduleList.setWhereAmI(getModuleList().getHead());
                while (moduleList.getWhereAmI() != null) {
                    if (moduleList.getWhereAmI().getModule() instanceof SmartLock) {
                        ((SmartLock) moduleList.getWhereAmI().getModule()).setLocked(true);
                        FileManipulator.append("log.txt", "We changed the state of " + moduleList.getWhereAmI().getModule().getName() + " in " + moduleList.getWhereAmI().getModule().getLocation());
                    } else if (moduleList.getWhereAmI().getModule() instanceof SmartWindow) {
                        ((SmartWindow) moduleList.getWhereAmI().getModule()).setOpen(false);
                        FileManipulator.append("log.txt", "We changed the state of " + moduleList.getWhereAmI().getModule().getName() + " in " + moduleList.getWhereAmI().getModule().getLocation());
                    }
                }
            }
        }
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
            FileManipulator.append("log.txt", "SmartSecurity was tripped and the authorities have been contacted");
        }
    }


    /**
     * This method creates a user account and adds it to the database.
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
        FileManipulator.append("users.txt", temp.getUsername() + ";" + temp.getPassword() + ";" + temp.getUserPermission());
        FileManipulator.append("log.txt", "User has been created and added to database");

    }

    /**
     * This method removes a user account
     *
     * @param user
     */
    public void removeAccount(User user) {
        userList.remove(user);
        FileManipulator.remove("users.txt", user.getUsername() + ";" + user.getPassword() + ";" + user.getUserPermission());
        FileManipulator.append("log.txt", "We removed a user from the database");
    }

    /**
     * This method toggles the window obstruction state
     *
     * @param window
     */
    public void toggleWindowObstruction(SmartWindow window) {
        window.setObstructed(!window.isObstructed());
        FileManipulator.append("log.txt", "Toggled the obstruction state of the window");
    }

    /**
     * This method changes the temperature of the outside world
     *
     * @param temp
     */
    public void changeOutsideTemperature(double temp) {
        SmartModule.setOutsideTemp(temp);
        FileManipulator.append("log.txt", "Changed the temperature of outside");
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
