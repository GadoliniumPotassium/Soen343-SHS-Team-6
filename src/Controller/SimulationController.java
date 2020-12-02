/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;
import main.App;
import main.Main;

import java.time.LocalTime;

/**
 *class to create simulation controoler
 */
public class SimulationController {

    public JFXButton timerSpeed;
    @FXML private Label user_name;
    @FXML private Label loc;
    @FXML private Label temp;
    @FXML private Label date_time;
    @FXML private JFXToggleButton onToggleBtn;

    private Main main = Main.getInstance();

    private Timeline timeline;

    enum Time_states {
        normal,
        x2,
        x4,
        x8,
        x16,
        x32,
    }
    Time_states t_s = Time_states.normal;

    /**
     * This method initializes the simulation
     */
    @FXML private void initialize() {
        main.readTimeAndDate();
        update();
        Timer();

        timerSpeed.setText("normal");
        t_s = Time_states.x2;
    }

    /**
     * This method updates things in the simulation as things happen
     */
    public void update(){
        User currentUser = main.active_user;
        user_name.setText(currentUser.getUsername());
        loc.setText(currentUser.getLocation());

        temp.setText("Temperature: "+main.settings.getTemperature());

        date_time.setText(main.settings.getDate()+" "+
                main.settings.getTime());
    }
    private int sec = 0;
    public void Timer(){
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), e->{

          sec++;

            String[] t = main.settings.getTime().split(":");
            int hour = Integer.parseInt(t[0]);
            int minute = Integer.parseInt(t[1]);

            if(sec >= 59) {
                if (minute >= 59) {
                    if(hour >= 24){
                        hour = 0;
                    }else {
                        hour += 1;
                        minute = 0;
                    }
                } else {
                    minute++;
                    sec = 0;
                }
            }
            // below code is for away code
            if(main.away_mode){
                int finalHour = hour;
                int finalMinute = minute;
                main.lights_outside.forEach(smartLight -> {
                    if(!((SmartLight)smartLight).getAwayModeTiming().equals("00:00,00:00")){
                        String[] timing = ((SmartLight)smartLight).getAwayModeTiming().split(",");
                        String[] from = timing[0].split(":");
                        String[] to = timing [1].split(":");

                        int f_hour = Integer.parseInt(from[0]);
                        int f_min = Integer.parseInt(from[1])-1;
                        int t_hour = Integer.parseInt(to[0]);
                        int t_min = Integer.parseInt(to[1]);

                        LocalTime current = LocalTime.of(finalHour,finalMinute);
                        LocalTime f_light = LocalTime.of(f_hour,f_min);
                        LocalTime t_light = LocalTime.of(t_hour,t_min);

                        if(current.isAfter(f_light) && current.isBefore(t_light)){
                            ((SmartLight)smartLight).setOn(true);
                        }else{
                            ((SmartLight)smartLight).setOn(false);
                        }
                    }
                });

                main.lights_inside.forEach(smartLight -> {
                    if(!((SmartLight)smartLight).getAwayModeTiming().equals("00:00,00:00")){
                        String[] timing = ((SmartLight)smartLight).getAwayModeTiming().split(",");
                        String[] from = timing[0].split(":");
                        String[] to = timing [1].split(":");

                        int f_hour = Integer.parseInt(from[0]);
                        int f_min = Integer.parseInt(from[1])-1;
                        int t_hour = Integer.parseInt(to[0]);
                        int t_min = Integer.parseInt(to[1]);

                        LocalTime current = LocalTime.of(finalHour,finalMinute);
                        LocalTime f_light = LocalTime.of(f_hour,f_min);
                        LocalTime t_light = LocalTime.of(t_hour,t_min);

                        if(current.isAfter(f_light) && current.isBefore(t_light)){
                            ((SmartLight)smartLight).setOn(true);
                        }else{
                            ((SmartLight)smartLight).setOn(false);
                        }
                    }
                });

            }
            // HAVC System.
            havc_system(hour, minute);

            main.settings.setTime((hour < 10 ? "0"+hour:hour)+":"+(minute < 10 ? "0"+minute:minute));
            date_time.setText(main.settings.getDate()+" "+
                    main.settings.getTime()+":"+
                    (sec < 10 ? "0"+sec:sec));
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setRate(timeline.getRate());
        if(main.isIsSimulationRunning())
            timeline.play();
    }

    /**
     * method for the HAVC sys
     * @param hour
     * @param minute
     */
    private void havc_system(int hour, int minute) {
        if(main.havc_system) {

            main.zones.forEach(zone -> {
                for (SmartZone.Period period : zone.periods) {

                    LocalTime current = LocalTime.of(hour, minute);
                    LocalTime f_time = LocalTime.of(period.getF_hour(),period.getF_min());
                    LocalTime t_time = LocalTime.of(period.getT_hour(),period.getT_min());

                    if (current.isAfter(f_time) && current.isBefore(t_time)) {
                        for (Room room : zone.rooms) {
                            tempAlert(room);
                            if(getSeason().equals("Summer")){
                                if(room.getTemperature() > period.getTemperature()){
                                    if(main.settings.getTemperature() < room.getTemperature() && !main.away_mode){
                                        windows(room,true);
                                        room.smartAC.setOn(false);
                                        room.thermostat.setOn(false);
                                        room.setTemperature((float) (room.getTemperature() - 0.05));
                                        System.out.println("cooling with outside temperature when room temperature is greater then period temp");
                                    }else {
                                        windows(room, false);
                                        room.setTemperature((float) (room.getTemperature() - room.smartAC.getCooling_temp())); //0.1
                                        room.smartAC.setOn(true);
                                        room.thermostat.setOn(false);
                                        System.out.println("room > outside < period");
                                    }
                                }else if(room.getTemperature() < period.getTemperature()){
                                    if(main.settings.getTemperature() > room.getTemperature() && !main.away_mode){
                                        windows(room,true);
                                        room.smartAC.setOn(false);
                                        room.thermostat.setOn(false);
                                        room.setTemperature((float) (room.getTemperature() + 0.05));
                                        System.out.println("cooling with outside temperature when room temperature is below period");
                                    }else {
                                        windows(room, false);
                                        room.setTemperature((float) (room.getTemperature() + room.smartAC.getCooling_temp())); //0.1
                                        room.smartAC.setOn(false);
                                        room.thermostat.setOn(true);
                                        System.out.println("room < outside > period");
                                    }
                                }
                            }else{
                                if(room.getTemperature() > period.getTemperature() && room.getTemperature() - period.getTemperature() > 0.25){
                                    room.smartAC.setOn(true);
                                    room.thermostat.setOn(false);
                                    room.setTemperature((float) (room.getTemperature()- room.smartAC.getCooling_temp())); // 0.1
                                    System.out.println("room > period");
                                } else if (room.getTemperature() < period.getTemperature() && room.getTemperature() - period.getTemperature() < -0.25) {
                                    room.thermostat.setOn(true);
                                    room.smartAC.setOn(false);
                                    room.setTemperature((float) (room.getTemperature() + room.thermostat.getHeating_temp())); // 0.1
                                    System.out.println("room < period");
                                }else{
                                    room.thermostat.setOn(false);
                                    room.smartAC.setOn(false);
                                    System.out.println("false");
                                }
                            }
                        }

                    }
                }
            });
        }else{
            main.rooms_list.forEach(room -> {
                tempAlert(room);
                if(room.getTemperature() > main.settings.getTemperature()){
                    room.setTemperature((float) (room.getTemperature() - 0.05)); //0.05
                }else if(room.getTemperature() < main.settings.getTemperature()){
                    room.setTemperature((float) (room.getTemperature() + 0.05));
                }
            });
        }
    }

    public String getSeason(){
        int month = Integer.parseInt(main.settings.getDate().split("/")[0]);

        String season = "";
        if(month >= 4 && month <= 9) season = "Summer";
        else season = "Winter";
        return season;
    }

    private void windows(Room room,boolean state) {
        main.windows_inside.forEach(window ->{
            if(window.getLocation().equals(room.getName())){
                if(((SmartWindow)window).isObstructed()){
                    App.log("[SHH] Alert: Window is Blocked by an Object.");
                }else {
                    ((SmartWindow) window).setOpen(state);
                }
            }
        });
    }

    /**
     * user notification in case to cold
     * @param r
     */
    private void tempAlert(Room r){

            if(r.getTemperature() <= 0){
                App.log(r.getName()+"WARNING! Temperature is Below 0 pipes can burst");
            
        }
    }

    @FXML private void onToggleBtn(ActionEvent actionEvent) {
        boolean isOn = onToggleBtn.selectedProperty().get();
        main.setIsSimulationRunning(isOn);
        if(isOn) timeline.play(); else timeline.pause();
        App.log("Simulation: "+(isOn?"On":"Off"));
    }

    /**
     * This method refreshes the GUI
     * @param actionEvent
     */
    public void refresh(ActionEvent actionEvent) {
        update();
    }

    /**
     * This method increases the timers speed
     * @param actionEvent
     */
    public void increase_timerSpeed(ActionEvent actionEvent) {
        switch(t_s){
            case normal: {
                timerSpeed.setText("normal");
                t_s = Time_states.x2;
                timeline.setRate(1);
                App.log("Simulation Time speed change to normal");
                break;
            }
            case x2 :{
                timerSpeed.setText("2x");
                t_s = Time_states.x4;
                timeline.setRate(2);
                App.log("Simulation Time speed change to 2x");
                break;
            }
            case x4 : {
                timerSpeed.setText("4x");
                t_s = Time_states.x8;
                timeline.setRate(4);
                App.log("Simulation Time speed change to 4x");
                break;
            }
            case x8 :{
                timerSpeed.setText("8x");
                timeline.setRate(8);
                t_s = Time_states.x16;
                App.log("Simulation Time speed change to 8x");
                break;
            }case x16 :{
                timerSpeed.setText("16x");
                timeline.setRate(16);
                t_s = Time_states.x32;
                App.log("Simulation Time speed change to 16x");
                break;
            }case x32 :{
                timerSpeed.setText("32x");
                timeline.setRate(32);
                t_s = Time_states.normal;
                App.log("Simulation Time speed change to 32x");
                break;
            }
        }
    }
}
