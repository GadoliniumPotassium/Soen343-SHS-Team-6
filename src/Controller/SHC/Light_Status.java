package Controller.SHC;

import Model.SmartLight;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.App;

public class Light_Status {
    public Label light_name;
    public ImageView light_state;

    private Image on;
    private Image off;

    private SmartLight light;

    @FXML
    void initialize(){
        on = new Image("FXML/Images/light_on.png");
        off = new Image("FXML/Images/light_off.png");
    }
    public void light_on_off(ActionEvent actionEvent) {
        if(isOn()){
            light.setOn(false);
            light_state.setImage(off);
        }else {
            light.setOn(true);
            light_state.setImage(on);
        }
        App.log(light.getLocation()+" "+light.getName()+" "+
                (isOn() ? "On":"off"));
    }

    public void setLight(SmartLight light) {
        this.light = light;
        light_name.setText(light.getName());

        if(isOn())
            light_state.setImage(on);
    }

    public boolean isOn(){
        return light.isOn();
    }
}