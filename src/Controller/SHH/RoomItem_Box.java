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

/**
 * class to create room in FE
 */
public class RoomItem_Box {
    public Label room_name;

    private Room room;
    private SmartZone zone;
    private VBox box;

    /**
     * method to set room
     * @param room
     */
    public void setRoom(Room room) {
        this.room = room;

        room_name.setText(room.getName());
    }

    /**
     * method to set zones
     * @param zone
     */
    public void setZone(SmartZone zone) {
        this.zone = zone;
    }

    public void setBox(VBox box) {
        this.box = box;
    }

    /**
     * method to remove room from zone
     * @param actionEvent
     */
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

    /**
     * method to get room smart  item
     * @param room
     * @return
     */
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
