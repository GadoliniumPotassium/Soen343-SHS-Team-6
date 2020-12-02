package Controller.SHH;

import Model.SmartZone;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.App;

import java.io.IOException;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;
import Exception.ImpossibleTimeException;
/**
 * class to provide create period box in FE
 */
public class PeriodItem_Box {
    public Label period_name;
    public TextField f_hour;
    public TextField f_min;
    public TextField t_hour;
    public TextField t_min;
    public TextField period_temp;

    private SmartZone.Period period;
    private SmartZone zone;

    private VBox box;

    /**
     * passing period of this controller and setting it's value on Graphic User Interface
     * @param period
     */
    public void setPeriod(SmartZone.Period period) {
        this.period = period;
        setF_hour(period.getF_hour() < 10 ? "0"+period.getF_hour():period.getF_hour()+"");
        setF_min(period.getF_min()<10 ? "0"+period.getF_min(): period.getF_min()+"");
        setT_hour(period.getT_hour() < 10 ? "0"+period.getT_hour(): period.getT_hour()+"");
        setT_min(period.getT_min() < 10 ? "0"+period.getT_min() : period.getT_min()+"");
        setPeriod_temp(period.getTemperature()+"");

    }

    /**
     * passing the zone in which this period is in it.
     * @param zone
     */
    public void setZone(SmartZone zone) {
        this.zone = zone;
    }

    public void setBox(VBox box) {
        this.box = box;
    }

    /**
     * set Period name
     * @param period_name
     */
    public void setPeriod_name(String period_name){
        this.period_name.setText(period_name);
    }
    /**
     * set Period hour
     * @param _hour
     */
    public void setF_hour(String _hour){
        f_hour.setText(_hour);
    }
    /**
     * set Period min
     * @param _min
     */
    public void setF_min(String _min){
        f_min.setText(_min);
    }
    public void setT_hour(String _hour){
        t_hour.setText(_hour);
    }
    public void setT_min(String _min){
        t_min.setText(_min);
    }
    public void setPeriod_temp(String _temp){
        period_temp.setText(_temp);
    }

    /**
     * method to remove the period
     * @param actionEvent
     */
    public void remove(ActionEvent actionEvent) {
        if(zone.periods.size() == 1){
            App.log("-Can not remove- Zone Must Have a single Period");
            return;
        }
        zone.periods.remove(period);
        App.log("Period is removed");

        //refresh
        box.getChildren().clear();
        AtomicInteger index = new AtomicInteger(1);
        zone.periods.forEach(p -> {
            box.getChildren().add(getItem(index.getAndIncrement(),p));
        });

    }

    /**
     * method to update period
     * @param actionEvent
     */
    public void update(ActionEvent actionEvent) {

        int f_h = Integer.parseInt(f_hour.getText());
        int f_m = Integer.parseInt(f_min.getText());
        int t_h = Integer.parseInt(t_hour.getText());
        int t_m = Integer.parseInt(t_min.getText());

        try{
            if(f_h < 0 || f_h > 23 || f_m < 1 || f_m > 59 || t_h < 0 || t_h > 23 || t_m < 1 || t_m > 59){
                throw new ImpossibleTimeException("Invalid Period Time to Update");
            }
        } catch (ImpossibleTimeException exception) {
            exception.printStackTrace();
        }

        period.setF_hour(f_h);
        period.setF_min(f_m);
        period.setT_hour(t_h);
        period.setT_min(t_m);

        period.setTemperature(Double.parseDouble(period_temp.getText()));

        // checking if the time is not intercepting.
        isTimeIntercepting();
    }

    private void isTimeIntercepting() {
        zone.periods.forEach(e->{
            LocalTime current_period_fTime = LocalTime.of(period.getF_hour(),period.getF_min());
            LocalTime current_period_tTime = LocalTime.of(period.getT_hour(),period.getT_min());

            LocalTime e_fTime = LocalTime.of(e.getF_hour(),e.getF_min());
            LocalTime e_tTime = LocalTime.of(e.getT_hour(),e.getT_min());

            if(current_period_fTime.isAfter(e_fTime) && current_period_fTime.isBefore(e_tTime) ||
                    current_period_tTime.isAfter(e_fTime) && current_period_tTime.isBefore(e_tTime)){
                App.log(period_name.getText()+"  timing is intercepting to another period.");
                reset();
            }
        });
    }

    /**
     * mehtod to reset the period
     */
    private void reset(){
        period.setF_hour(0);
        period.setF_min(0);
        period.setT_hour(0);
        period.setT_min(0);
        period.setTemperature(0);
    }

    /**
     * method to get item from FE
     * @param index
     * @param period
     * @return
     */
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
        controller.setBox(this.box);
        return box;
    }
}