package Controller.SHH;

import Model.Room;
import Model.SmartZone;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.App;
import main.Main;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class AddRoom_Box {

    private VBox r_box = new VBox();

    private VBox vBox;

    private SmartZone zone;


    public void setVBox(VBox vBox) {
        this.vBox = vBox;
    }

    public void setR_box() {
        this.vBox.getChildren().add(r_box);
        r_box.setSpacing(15);
    }
    public void loadRooms(){
        r_box.getChildren().clear();
        zone.rooms.forEach(room -> {
            r_box.getChildren().add(getRoomItem(room));
        });
    }

    public void setZone(SmartZone zone) {
        this.zone = zone;
    }

    public void add_room(ActionEvent actionEvent) {
        if(!Main.getInstance().isIsSimulationRunning()) {
            App.log("Simulation is not running.");
            return;
        }
        Room room = getSelectedRoom();
        if(room == null) return;
        if(roomInZone(room)) {
            App.log(room.getName()+" is already in a Zone.");
            return;
        }
        zone.rooms.add(room);
        r_box.getChildren().add(getRoomItem(room));
        App.log("Added Room.");
    }

    /**
     * Checking all zones if the room we are trying to add is already in zone or not.
     * @param _room
     * @return
     */
    public boolean roomInZone(Room _room){
        AtomicBoolean cond = new AtomicBoolean(false);
        Main.getInstance().zones.forEach(e->{
            for (Room room : e.rooms) {
                if(room == _room){
                    cond.set(true);
                    break;
                }
            }
        });
        return cond.get();
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
        controller.setBox(r_box);
        return box;
    }

    public Room getSelectedRoom(){
        ListView<Room> rooms_list = new ListView<>();
        Main.getInstance().rooms_list.forEach(e->{
            rooms_list.getItems().add(e);
        });

        Stage pop = new Stage();


        AtomicReference<Room> room_name = new AtomicReference<>();
        rooms_list.setOnMouseClicked(e->{
            room_name.set(rooms_list.getSelectionModel().getSelectedItem());
            pop.close();
        });

        Pane pane = new Pane();
        pane.getChildren().add(rooms_list);

        pop.setScene(new Scene(pane));
        pop.initModality(Modality.APPLICATION_MODAL);
        pop.initStyle(StageStyle.TRANSPARENT);
        pop.showAndWait();

        return room_name.get();
    }
}
