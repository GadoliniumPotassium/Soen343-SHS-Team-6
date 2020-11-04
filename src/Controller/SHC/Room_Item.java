package Controller.SHC;

import Model.House;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.App;
import main.Main;

import java.io.IOException;

public class Room_Item {

    @FXML public Label room_name_label;
    @FXML public Label temp_label;
    @FXML public Label doors_label;
    @FXML public Label windows_label;
    @FXML public Label lights_label;
    @FXML public Label users_label;

    @FXML void initialize(){}

    private House room = new House();
    private int users;


    public void setValues(House.Room _room){
        this.room.room = _room;
        this.users = Main.users_in_same_room(_room.getName());
        updateValues();
    }

    public void load_room_details(MouseEvent mouseEvent) {
        if(Main.isIsSimulationRunning()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../FXML/SHC/room_details.fxml"));
            BorderPane root = null;
            try {
                root = loader.load();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            Room_details controller = loader.getController();
            controller.room_Details(room, users);

            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.initModality(Modality.APPLICATION_MODAL);
            //newStage.initStyle(StageStyle.TRANSPARENT);
            newStage.showAndWait();
            updateValues();
        }else{
            App.log("Please Run the Simulator to perform these actions");
        }
    }
    public void updateValues(){
        room_name_label.setText(room.room.getName());
        temp_label.setText(room.room.getTemperature()+"");
        doors_label.setText(room.room.getDoors()+"");
        windows_label.setText(room.room.getWindows()+"");
        lights_label.setText(room.room.getLights()+"");
        users_label.setText(Main.users_in_same_room(room.room.getName())+"");
    }
}
