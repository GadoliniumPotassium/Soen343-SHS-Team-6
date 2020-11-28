package Controller.SHH;

import Model.SmartZone;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.App;
import main.Main;

import java.io.IOException;

public class Zone_HBox {
    public Label zone_name;
    public VBox vbox;

    private VBox b_vbox = null;
    private SmartZone zone;
    private Period_Box period_controller = null;
    private AddRoom_Box room_controller = null;
    private Main main = Main.getInstance();

    /**
     * set the zone we are using in this controller.
     * @param zone
     */
    public void setZone(SmartZone zone) {
        this.zone = zone;
        zone_name.setText(zone.getName());
    }

    /**
     * every first time this default method will be called.
     */
    public void setup() {
        this.vbox.getChildren().add(getPeriodBox());

        this.vbox.getChildren().add(period_controller.getP_box());
        for (int i = 0; i < zone.periods.size(); i++) {
            period_controller.getP_box().getChildren().add(period_controller.getItem(i, zone.periods.get(i)));
        }
        roomsSetup();
    }

    /**
     * get Period Box and setting the default setup. and only called when the new zone is created.
     * @return
     */
    public HBox getPeriodBox(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../FXML/SHH/period_box.fxml"));
        HBox hbox = null;
        try{
            hbox = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        period_controller = loader.getController();
        period_controller.setZone(zone);
        return hbox;
    }

    public void setVbox(VBox vbox) {
        this.b_vbox = vbox;
    }

    // below code for adding room in zone

    public void roomsSetup(){
        this.vbox.getChildren().add(roomLabel());
        room_controller.setR_box();
    }


    public HBox roomLabel(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../FXML/SHH/addRoom_box.fxml"));
        HBox box = null;
        try{
            box = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        room_controller = loader.getController();
        room_controller.setZone(zone);
        room_controller.setVBox(vbox);
        room_controller.loadRooms();
        return box;
    }

    /**
     * This Method will remove zone if there are more then one zone.
     * @param actionEvent
     */
    public void remove(ActionEvent actionEvent) {
        if(!main.isIsSimulationRunning()){
            App.log("Simulation is not Running");
            return;
        }
        if(main.zones.size() == 1){
            App.log("Can't Remove the Zone. Need at least One Zone");
            return;
        }
        App.log(zone.getName()+" is Removed");
        main.zones.remove(zone);

        // refresh the system.
        b_vbox.getChildren().clear();
        main.zones.forEach(zone ->{
            b_vbox.getChildren().add(zoneBox(zone));
        });
    }

    public VBox zoneBox(SmartZone zone){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../FXML/SHH/zone_hbox.fxml"));
        VBox container = null;
        try{
            container = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Zone_HBox controller = loader.getController();
        controller.setVbox(this.vbox);
        controller.setZone(zone);
        controller.setup();
        return container;
    }
}