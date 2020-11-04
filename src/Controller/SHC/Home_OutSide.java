package Controller.SHC;

import Model.House;
import javafx.scene.control.Label;
import main.Main;

public class Home_OutSide {

    public Label name;
    public Label doors;
    public Label lights;
    public Label users;

    public void setValues(House.OutSide out){
        name.setText(out.getName());
        doors.setText(String.valueOf(out.getDoors()));
        lights.setText(String.valueOf(out.getLights()));

        users.setText(Main.users_outside(out.getName())+"");
    }
}
