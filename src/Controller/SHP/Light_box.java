package Controller.SHP;

import Model.SmartLight;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.NumFieldFX;

public class Light_box {
    public Label loc;
    public Label name;
    public ImageView light_imageView;
    public TextField from_hour;
    public TextField from_min;
    public TextField to_hour;
    public TextField to_min;

    private SmartLight light;

    private Image on;
    private Image off;

    @FXML
    void initialize(){
        on = new Image("FXML/Images/light_on.png");
        off = new Image("FXML/Images/light_off.png");

        new NumFieldFX().numField(from_hour);
        new NumFieldFX().numField(from_min);
        new NumFieldFX().numField(to_hour);
        new NumFieldFX().numField(to_min);
    }

    public void on_off(ActionEvent actionEvent) {
        if(isOn()){
            light_imageView.setImage(off);
            this.light.setOn(false);
        }else{
            light_imageView.setImage(on);
            this.light.setOn(true);
        }
    }

    public void setLight(SmartLight light) {
        this.light = light;
        loc.setText(light.getLocation());
        name.setText(light.getName());
    }

    private boolean isOn(){
        return light.isOn();
    }

    public void set_time(ActionEvent actionEvent) {
        this.light.setAway_mode_timing(from_hour.getText()+":"+from_min.getText()+","+
                to_hour+":"+to_min);
    }
}
