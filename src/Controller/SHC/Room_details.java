package Controller.SHC;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.Main;

import java.io.IOException;

/**
 * class has info of detail of the room
 */
public class Room_details {
    public Label room_name;
    public ListView<HBox> users_in_room;
    public ListView<VBox> doors_in_room;
    public ListView<VBox> windows_in_room;
    public ListView<VBox> lights_in_room;
    public TextField temp_value;
    public ImageView ac;
    public ImageView heater;
    private Room room;

    private final Main main = Main.getInstance();

    private Image ac_on;
    private Image ac_off;

    private Image heater_on;
    private Image heater_off;

    @FXML
    void initialize(){
        ac_on = new Image("FXML/Images/AC_off.png");
        ac_off = new Image("FXML/Images/AC_on.png");

        heater_on = new Image("FXML/Images/heater_on.png");
        heater_off = new Image("FXML/Images/heater.png");

    }

    /**
     * This method changes the temperature in a room
     * @param actionEvent
     */
    public void change_temperature(ActionEvent actionEvent) {
        if(temp_value.getText().isEmpty()) return;
        this.room.setTemperature(Float.parseFloat(temp_value.getText()));
    }

    /**
     * This method takes a room and a user and
     * @param _room
     * @param _users
     */
    public void room_Details(Room _room,int _users){
        this.room = _room;
        room_name.setText(room.getName());
        temp_value.setText(room.getTemperature()+"");

        if(room.isHeater_ac()){
            ac.setImage(ac_on);
            heater.setImage(heater_off);
        }else{
            heater.setImage(heater_on);
            ac.setImage(ac_off);
        }

        //list of users in room
        update_user_list();

        main.doors_inside.forEach(e->{
            if(e.getLocation().equals(room.getName())){
                doors_in_room.getItems().add(getDoorBox((SmartWindow) e));
            }
        });

        main.windows_inside.forEach(e->{
            if(e.getLocation().equals(room.getName())){
                windows_in_room.getItems().add(getWindowBox((SmartWindow)e));
            }
        });

        main.lights_inside.forEach(e->{
            if(e.getLocation().equals(room.getName())){
                lights_in_room.getItems().add(getLightBox((SmartLight) e));
            }
        });
    }

    /**
     * This method serves to update the user list
     */
    public void update_user_list(){
        for(User user : main.user_list){
            if(user.getLocation() != null && user.getLocation().equals(this.room.getName())){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../FXML/SHC/user_in_room.fxml"));
                HBox h = null;
                try {
                    h = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                User_In_Room uIn = loader.getController();
                uIn.setUserName(user);
                uIn.setListView(users_in_room);
                uIn.setRoom(this.room);
                users_in_room.getItems().add(h);
            }
        }
    }

    /**
     * this method serves to pull the door UI box
     * @param _door
     * @return
     */
    public VBox getDoorBox(SmartWindow _door){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../FXML/SHC/Door_Status.fxml"));
        VBox vBox = null;
        try{
            vBox = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Door_Status controller = loader.getController();
        controller.setDoor(_door);
        return vBox;
    }

    /**
     * This method serves to pull the window UI box
     * @param _window
     * @return
     */
    public VBox getWindowBox(SmartWindow _window){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../FXML/SHC/Window_Status.fxml"));
        VBox vBox = null;
        try{
            vBox = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Window_Status controller = loader.getController();
        controller.setWindow(_window);
        return vBox;
    }

    /**
     * this method serves to pull the light UI box
     * @param _light
     * @return
     */
    public VBox getLightBox(SmartLight _light){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../FXML/SHC/Light_Status.fxml"));
        VBox vBox = null;
        try{
            vBox = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Light_Status controller = loader.getController();
        controller.setLight(_light);
        return vBox;
    }
}
