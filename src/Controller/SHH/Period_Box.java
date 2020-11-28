package Controller.SHH;

import Model.SmartZone;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.App;
import main.Main;

import java.io.IOException;

public class Period_Box {


    private VBox p_box = new VBox();

    public VBox getP_box() {
        return p_box;
    }

    private Main main = Main.getInstance();
    private SmartZone zone;

    public void add_period(ActionEvent actionEvent) {
        if(!main.isIsSimulationRunning()) {
            App.log("Simulation is not running.");
            return;
        }
        p_box.setSpacing(15);
        zone.addPeriod();
        p_box.getChildren().add(getItem(zone.periods.size()-1,zone.periods.get(zone.periods.size()-1)));
        App.log("Added New Period");
    }

    public void setZone(SmartZone zone) {
        this.zone = zone;
    }

    public HBox getItem(int index, SmartZone.Period period){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../FXML/SHH/periodItem_box.fxml"));
        HBox box = null;
        try{
            box = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PeriodItem_Box controller = loader.getController();
        controller.setPeriod_name("Period "+(index+1));
        controller.setPeriod(period);
        controller.setZone(zone);
        controller.setBox(this.p_box);
        return box;
    }
}
