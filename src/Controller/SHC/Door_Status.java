package Controller.SHC;

import Model.SmartWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.App;
import main.Main;

public class Door_Status {

    public ImageView door_status;
    public ImageView door_lock;
    public Label door_name;
    public ImageView door_obstruct;

    private Image door_open;
    private Image door_close;
    private Image door_locked;
    private Image door_unlock;

    private SmartWindow door;

    private Main main = Main.getInstance();

    @FXML
    void initialize(){
        door_open = new Image("FXML/Images/door_open.png");
        door_close = new Image("FXML/Images/door_close.png");
        door_locked = new Image("FXML/Images/lock.png");
        door_unlock = new Image("FXML/Images/unlock.png");

    }

    public void setDoor(SmartWindow door) {
        this.door = door;
        door_name.setText(door.getName());

        if(!door.isLocked())
            door_lock.setImage(door_unlock);
        else
            door_lock.setImage(door_locked);

        if(door.isOpen())
            door_status.setImage(door_open);
        else
            door_status.setImage(door_close);
    }

    public String getDoor_name() {
        return this.door.getName();
    }

    public void door_open_close(ActionEvent actionEvent) {
        if(main.away_mode){
            App.log("You can not open the door in away mode active");
            return;
        }
        if(isDoor_lock() && !isDoor_open()) {
            App.log(door.getLocation()+" "+door.getName()+" is locked can't open the door");
            return;
        }
        if(isDoor_obstructed()){
            App.log(door.getLocation()+" "+door.getName()+" is obstructed by an object");
            return;
        }
        if(isDoor_open()){
            door.setOpen(false);
            door_status.setImage(door_close);
        }else {
            door.setOpen(true);
            door_status.setImage(door_open);
        }
        App.log(door.getLocation()+" "+door.getName()+" "+
                (isDoor_open() ? "Open":"Close"));
    }

    public void door_lock_unlock(ActionEvent actionEvent) {
        if(main.away_mode){
            App.log("While Away Mode is active you can not Unlock the door");
            return;
        }
        if(isDoor_lock()){
            door.setLocked(false);
            door_lock.setImage(door_unlock);
        }else{
            door.setLocked(true);
            door_lock.setImage(door_locked);
            door.setOpen(false);
            door_status.setImage(door_close);
        }
        App.log(door.getLocation()+" "+door.getName()+" "+
                (isDoor_lock() ? (isDoor_open() ? "Closed and ":"")+"Lock":"Unlock"));
    }


    public boolean isDoor_open() {
        return door.isOpen();
    }

    public boolean isDoor_lock() {
        return door.isLocked();
    }


    public boolean isDoor_obstructed(){
        return door.isObstructed();
    }
}
