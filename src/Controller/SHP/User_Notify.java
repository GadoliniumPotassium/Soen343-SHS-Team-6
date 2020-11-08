package Controller.SHP;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.App;
import main.Main;

import java.util.concurrent.atomic.AtomicInteger;

public class User_Notify {

    @FXML public Label room_name;
    @FXML public Label msg;
    public Label alert_timer;

    private Timeline timeline;

    private Main main = Main.getInstance();

    @FXML
    void initialize(){
        Stage stage = (Stage) msg.getScene().getWindow();
        stage.setOnCloseRequest(e->{
            close();
        });

    }

    /**
     * This method serves as a set up for the notification box taking in the title and message
     * @param _rName
     * @param _msg
     */
    public void setup(String _rName, String _msg){
        room_name.setText(_rName);
        msg.setText(_msg);

        Timer();
    }

    /**
     * This  method serves as a timer
     */
    private void Timer(){
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        AtomicInteger minutes = new AtomicInteger(main.settings.getAlertTiming()-1);
        AtomicInteger sec = new AtomicInteger(59);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), e->{
            sec.getAndDecrement();
            if(sec.intValue() <= 00) {
                minutes.getAndDecrement();
                if (minutes.intValue() < 0) {
                    // close the this popup window and alert the authorities.
                    App.log("User did not Response. Alert The Authorities by Smart House Security System");
                    close();
                }
                sec.set(59);
            }
            alert_timer.setText("Alerting the Authorities in "+
                    (minutes.intValue() < 10 ? "0"+minutes.intValue() : minutes.intValue())+":"
                    +(sec.intValue() < 10 ? "0"+sec.intValue() : sec.intValue()));
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    /**
     * This method serves to close the notification
     */
    private void close(){
        main.away_mode = false;
        timeline.stop();
        Stage stage = (Stage) msg.getScene().getWindow();
        stage.close();
    }

    /**
     * This method logs that the home is safe
     * @param actionEvent
     */
    public void homie_save(ActionEvent actionEvent) {
        // closing the away mode.
        App.log("User Response: it's safe.");
        close();
        timeline.stop();
    }

    /**
     * This method serves to alert the authorities in case of a break-in
     * @param actionEvent
     */
    public void Alert_Authorities(ActionEvent actionEvent) {
        App.log("User Response: Alerting the authorities");
        close();
        timeline.stop();
    }
}
