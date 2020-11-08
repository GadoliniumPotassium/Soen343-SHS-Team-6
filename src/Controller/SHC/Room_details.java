package Controller.SHC;

import Model.*;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.App;
import main.Main;

import java.io.IOException;

public class Room_details {
    public Label room_name;
    public ListView users_in_room;
    public ListView doors_in_room;
    public ListView windows_in_room;
    public ListView lights_in_room;
    public TextField temp_value;
    private Room room;

    private Main main = Main.getInstance();

    public void change_temperature(ActionEvent actionEvent) {
        if(temp_value.getText().isEmpty()) return;
        this.room.setTemperature(Float.parseFloat(temp_value.getText()));
    }
    public void room_Details(Room _room,int _users){
        this.room = _room;
        room_name.setText(room.getName());
        temp_value.setText(room.getTemperature()+"");

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
