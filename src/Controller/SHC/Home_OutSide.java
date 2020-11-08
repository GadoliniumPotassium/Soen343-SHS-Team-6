package Controller.SHC;

import Model.House;
import javafx.scene.control.Label;
import main.Main;

public class Home_OutSide {

    public Label name;
    public Label doors;
    public Label lights;
    public Label users;

    Main main = Main.getInstance();

    /**
     *This method serves to set the values of the doors and lights outside your house
     * @param out
     */
    public void setValues(House out){
        name.setText(out.getName());
        doors.setText(String.valueOf(out.getDoors()));
        lights.setText(String.valueOf(out.getLights()));

        users.setText(main.users_outside(out.getName())+"");
    }
}
