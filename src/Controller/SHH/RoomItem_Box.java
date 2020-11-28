package Controller.SHH;

import Model.Room;
import Model.SmartZone;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.App;
import main.Main;

import java.io.IOException;

public class RoomItem_Box {
    public Label room_name;

    private Room room;
    private SmartZone zone;
    private VBox box;

    public void setRoom(Room room) {
        this.room = room;

        room_name.setText(room.getName());
    }

    public void setZone(SmartZone zone) {
        this.zone = zone;
    }

    public void setBox(VBox box) {
        this.box = box;
    }

    public void remove_room(ActionEvent actionEvent) {
        if(!Main.getInstance().isIsSimulationRunning()){
            App.log("Simulation is not Running");
            return;
        }
        zone.rooms.remove(room);
        App.log("Removed the Room.");

        //refresh
        box.getChildren().clear();
        zone.rooms.forEach(r ->{
            box.getChildren().add(getRoomItem(r));
        });
    }

    public HBox getRoomItem(Room room){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../FXML/SHH/roomItem_box.fxml"));
        HBox box = null;
        try{
            box = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        RoomItem_Box controller = loader.getController();
        controller.setRoom(room);
        controller.setZone(zone);
        controller.setBox(this.box);
        return box;
    }
}
