package Controller.SHP;

import Model.SmartLight;
import Model.SmartModule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.NumFieldFX;

public class Light_box {
    public Label loc;
    public Label name;
//    public ImageView light_imageView;
    public TextField from_hour;
    public TextField from_min;
    public TextField to_hour;
    public TextField to_min;

    private SmartLight light;


    private NumFieldFX numFieldFX = NumFieldFX.getInstance();
    @FXML
    /**
     * Thsi method initializes the light box
     */
    void initialize(){


        numFieldFX.numField(from_hour);
        numFieldFX.numField(from_min);
        numFieldFX.numField(to_hour);
        numFieldFX.numField(to_min);
    }

    /**
     * This method sets the light in its location
     * @param light
     */
    public void setLight(SmartModule light) {
        this.light = (SmartLight) light;
        loc.setText(light.getLocation());
        name.setText(light.getName());
        setAwayModeTextFields();
    }

    /**
     * This method returns the set away mode text fields
     */
    private void setAwayModeTextFields(){
        String[] timing = light.getAwayModeTiming().split(",");
        String[] from = timing[0].split(":");
        String[] to = timing [1].split(":");

        int f_hour = Integer.parseInt(from[0]);
        int f_min = Integer.parseInt(from[1]);
        int t_hour = Integer.parseInt(to[0]);
        int t_min = Integer.parseInt(to[1]);

        from_hour.setText(f_hour+"");
        from_min.setText(f_min+"");
        to_hour.setText(t_hour+"");
        to_min.setText(t_min+"");
    }

    /**
     * This method returns whether the light is on or off
     * @return
     */
    private boolean isOn(){
        return light.isOn();
    }

    /**
     * This method serves to set the away mode timing in case of a break-in or for the lights
     * @param actionEvent
     */
    public void set_time(ActionEvent actionEvent) {
        if(from_hour.getText().isEmpty() && from_min.getText().isEmpty()
        && to_hour.getText().isEmpty() && to_min.getText().isEmpty()){
            this.light.setAwayModeTiming("00:00,00:00");
        }else{
            this.light.setAwayModeTiming(from_hour.getText()+":"+from_min.getText()+","+
                    to_hour.getText()+":"+to_min.getText());
        }
    }
}