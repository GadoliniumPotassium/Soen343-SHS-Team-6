/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.User;
import com.jfoenix.controls.JFXToggleButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;
import main.App;
import main.Main;

import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Vladislav
 */
public class SimulationController {

    @FXML private Label user_name;
    @FXML private Label loc;
    @FXML private Label temp;
    @FXML private Label date_time;
    @FXML private JFXToggleButton onToggleBtn;

    private Timeline timeline;

    @FXML private void initialize() {
        new Main().readTimeAndDate();
        update();
        Timer();
    }

    public void update(){
        User currentUser = Main.active_user;
        user_name.setText(currentUser.getUsername());
        loc.setText(currentUser.getLocation());

        temp.setText("Temperature: "+Main.settings.getTemperature());

        date_time.setText(Main.settings.getDate()+" "+
                Main.settings.getTime());
    }
    public void Timer(){
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(60), e->{
            String[] t = Main.settings.getTime().split(":");
            int hour = Integer.parseInt(t[0]);
            int minute = Integer.parseInt(t[1]);

            if(minute==60){
                hour += 1;
                minute = 0;
            }else{
                minute++;
            }
            /*if(Main.away_mode){
                int finalHour = hour;
                Main.lights_inside.forEach(inside->{
                    String[] timing = inside.getAway_mode_timing().split(",");
                    String[] from = timing[0].split(":");
                    String[] to = timing[1].split(":");
                    if(Integer.parseInt(from[0]) > finalHour && Integer.parseInt(to[0]) < finalHour){
                        inside.setOn(true);
                    }
                });
            }*/

            Main.settings.setTime((hour < 10 ? "0"+hour:hour)+":"+(minute < 10 ? "0"+minute:minute));
            date_time.setText(Main.settings.getDate()+" "+
                    Main.settings.getTime());
        });
        timeline.getKeyFrames().add(keyFrame);
        if(Main.isIsSimulationRunning())
            timeline.play();
    }

    @FXML private void onToggleBtn(ActionEvent actionEvent) {
        boolean isOn = onToggleBtn.selectedProperty().get();
        Main.setIsSimulationRunning(isOn);
        if(isOn) timeline.play(); else timeline.pause();
        App.log("Simulation: "+(isOn?"On":"Off"));
    }

    public void refresh(ActionEvent actionEvent) {
        update();
    }
}
