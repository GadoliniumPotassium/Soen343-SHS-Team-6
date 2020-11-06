package Controller.SHP;

import Model.SmartLight;
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

//    private Image on;
//    private Image off;

    @FXML
    void initialize(){
//        on = new Image("FXML/Images/light_on.png");
//        off = new Image("FXML/Images/light_off.png");

        new NumFieldFX().numField(from_hour);
        new NumFieldFX().numField(from_min);
        new NumFieldFX().numField(to_hour);
        new NumFieldFX().numField(to_min);
    }
//
//    public void on_off(ActionEvent actionEvent) {
//        if(isOn()){
//            light_imageView.setImage(off);
//            this.light.setOn(false);
//        }else{
//            light_imageView.setImage(on);
//            this.light.setOn(true);
//        }
//    }

    public void setLight(SmartLight light) {
        this.light = light;
        loc.setText(light.getLocation());
        name.setText(light.getName());
        setAwayModeTextFields();
    }

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

    private boolean isOn(){
        return light.isOn();
    }

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