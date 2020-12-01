package Controller.SHC;

import Model.House;
import Model.SmartLight;
import Model.SmartWindow;
import Model.User;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.Main;

import java.io.IOException;

public class OutSide_Details {
    public Label name;
    public ListView users;
    public ListView doors;
    public ListView lights;

    private House outside;

    private Main main;
    @FXML void initialize(){
        main = Main.getInstance();
    }

    /**
     * This method serves to do something?
     * @param outside
     */
    public void setOutside(House outside) {
        this.outside = outside;

        name.setText(outside.getName());

        update();
        user_list();
    }

    /**
     * This method serves to update the update the door and lights and add them in items
     */
    public void update(){
        main.doors_outside.forEach(door ->{
            if(door.getLocation().equals(outside.getName())){
                doors.getItems().add(getDoorBox((SmartWindow)door));
            }
        });
        main.lights_outside.forEach(light ->{
            if(light.getLocation().equals(outside.getName())){
                lights.getItems().add(getLightBox((SmartLight) light));
            }
        });
    }

    /**
     * This method serves to place the user list in the screen
     */
    private void user_list(){
        users.getItems().clear();
        for(User user : main.user_list){
            if(user.getLocation() != null && user.getLocation().equals(outside.getName())){
                HBox user_box = new HBox();
                user_box.setAlignment(Pos.CENTER_LEFT);
                user_box.setStyle("-fx-background-color:  #14274e");
                user_box.setPrefHeight(50);

                Label user_label = new Label(user.getUsername());
                JFXButton removeBtn = new JFXButton("Remove");

                user_label.setFont(Font.font("Bell MT",18));
                user_label.setTextFill(Color.web("#9ba4b4"));
                user_label.setAlignment(Pos.CENTER);
                user_label.setPrefWidth(100);

                removeBtn.setFont(Font.font("Bell MT",18));
                removeBtn.setTextFill(Color.web("#9ba4b4"));
                removeBtn.setPrefWidth(100);

                user_box.getChildren().addAll(user_label,removeBtn);

                removeBtn.setOnAction(e->{
                    user.setLocation("unknown");
                    user_list();
                });

                users.getItems().add(user_box);
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
