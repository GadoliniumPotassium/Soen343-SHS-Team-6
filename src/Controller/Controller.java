package Controller;

import Model.*;

public class Controller {
    LinkedList moduleList=new LinkedList();

    //This will be filled with method in order to modify the states of the smart devices

    public void changeACValue(double change, SmartAC AC){
        AC.setCurrent_temp_AC(AC.getCurrent_temp_AC()+change);
    }

    public void changeThermosValue(double value, SmartThermostat Thermos){
        Thermos.setCurrent_temp_heater(Thermos.getCurrent_temp_heater()+value);
    }

    public void switchLightState(SmartLight light){
     light.setOn(!light.isOn());
    }

    public void changeLightDim(int change, SmartLight light){
        if(light.isOn() && light.isDimmable()){
            light.setLightPercentage(light.getLightPercentage()+change);
        }
    }

    public void changeLockState(SmartLock lock){
        lock.setLocked(!lock.isLocked());
    }

    public void contactAuthorities(SmartSecurity sec){
        if (sec.isInAwayMode() && sec.isSomeoneThere()){
            System.out.println("We are contacting 911");
        }
    }

    public void changeAwayModeState(SmartSecurity sec){
        sec.setInAwayMode(!sec.isInAwayMode());
    }

}
