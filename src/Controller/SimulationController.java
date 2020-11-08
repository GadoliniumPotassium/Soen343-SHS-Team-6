/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.SmartLight;
import Model.User;
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

import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Vladislav
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
    }
    Time_states t_s = Time_states.normal;

    @FXML private void initialize() {
        main.readTimeAndDate();
        update();
        Timer();

        timerSpeed.setText("normal");
        t_s = Time_states.x2;
    }

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

            // turn on off lights when on away mode.
//            System.out.println("away mode"+Main.away_mode);
            if(main.away_mode){
                int finalHour = hour;
                int finalMinute = minute;
                main.lights_outside.forEach(smartLight -> {
                    check_awayMode_lights((SmartLight)smartLight,finalHour,finalMinute);
                });

                main.lights_inside.forEach(smartLight -> {
                    check_awayMode_lights((SmartLight)smartLight,finalHour,finalMinute);
                });

            }

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

    private void check_awayMode_lights(SmartLight _smartLight, int _hour, int _minute){
        if(!_smartLight.getAwayModeTiming().equals("00:00,00:00")){
            String[] timing = _smartLight.getAwayModeTiming().split(",");
            String[] from = timing[0].split(":");
            String[] to = timing [1].split(":");

            int f_hour = Integer.parseInt(from[0]);
            int f_min = Integer.parseInt(from[1])-1;
            int t_hour = Integer.parseInt(to[0]);
            int t_min = Integer.parseInt(to[1]);

//            System.out.println(_hour+":"+_minute+"\n"+f_hour+":"+f_min+","+t_hour+":"+t_min);
            // Time Compare

            LocalTime current = LocalTime.of(_hour,_minute);
            LocalTime f_light = LocalTime.of(f_hour,f_min);
            LocalTime t_light = LocalTime.of(t_hour,t_min);

            if(current.isAfter(f_light) && current.isBefore(t_light)){
                _smartLight.setOn(true);
            }else{
                _smartLight.setOn(false);
            }

            /*if(_hour >= f_hour && _hour <= t_hour){
                _smartLight.setOn(true);
            }else{
                _smartLight.setOn(false);
            }*/
        }
    }

    @FXML private void onToggleBtn(ActionEvent actionEvent) {
        boolean isOn = onToggleBtn.selectedProperty().get();
        main.setIsSimulationRunning(isOn);
        if(isOn) timeline.play(); else timeline.pause();
        App.log("Simulation: "+(isOn?"On":"Off"));
    }

    public void refresh(ActionEvent actionEvent) {
        update();
    }

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
                t_s = Time_states.normal;
                App.log("Simulation Time speed change to 8x");
                break;
            }
        }
    }
}
