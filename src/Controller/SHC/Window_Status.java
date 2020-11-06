package Controller.SHC;

import Model.SmartWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.App;
import main.Main;

public class Window_Status {

    public Label window_name;
    public ImageView window_status;
    public ImageView window_lock;
    public ImageView window_Obstruct;

    private Image window_close;
    private Image window_open;
    private Image window_locked;
    private Image window_unlock;
    private Image window_obstructed;
    private Image window_unobstructed;

    private SmartWindow window;

    @FXML
    void initialize(){
        window_open = new Image("FXML/Images/window_open.png");
        window_close = new Image("FXML/Images/window_close.png");
        window_locked = new Image("FXML/Images/lock.png");
        window_unlock = new Image("FXML/Images/unlock.png");
        window_obstructed = new Image("FXML/Images/caution_p.png");
        window_unobstructed = new Image("FXML/Images/caution.png");
    }

    public void window_open_close(ActionEvent actionEvent) {
        if(Main.away_mode){
            App.log("You can not open the window in away mode active");
            return;
        }
        if(isWindow_locked() && !isWindow_open()){
            App.log(window.getLocation()+" "+window.getName()+" is locked can not open");
            return;
        }
        if(isWindow_Obstructed()){
            App.log(window.getLocation()+" "+window.getName()+" is obstructed by some object");
            return;
        }
        if(isWindow_open()){
            this.window.setOpen(false);
            window_status.setImage(window_close);
        }else{
            this.window.setOpen(true);
            window_status.setImage(window_open);
        }
        App.log(window.getLocation()+" "+window.getName()+" "+
                (isWindow_open() ? "Open":"Close"));
    }

    public void window_lock_unlock(ActionEvent actionEvent) {
        if(Main.away_mode){
            App.log("While Away Mode is active you can not Unlock the door");
            return;
        }
        if(!isWindow_locked()){
            this.window.setLocked(true);
            window_lock.setImage(window_locked);
            window.setOpen(false);
            window_status.setImage(window_close);
        }else{
            this.window.setLocked(false);
            window_lock.setImage(window_unlock);
        }
        App.log(window.getLocation()+" "+window.getName()+" "+
                (isWindow_locked() ? (isWindow_open() ? "Closed and Lock":"Lock"):
                        "Unlock"));
    }

    public void setWindow(SmartWindow window) {
        this.window = window;
        window_name.setText(window.getName());

        if(isWindow_open())
            window_status.setImage(window_open);
        else
            window_status.setImage(window_close);

        if(isWindow_locked())
            window_lock.setImage(window_locked);
        else
            window_lock.setImage(window_unlock);

        if(window.isObstructed())
            window_Obstruct.setImage(window_obstructed);
        else
            window_Obstruct.setImage(window_unobstructed);
    }


    public boolean isWindow_open() {
        return this.window.isOpen();
    }

    public boolean isWindow_locked() {
        return this.window.isLocked();
    }

    public void window_obstructed(ActionEvent actionEvent) {
        if(isWindow_Obstructed()){
            window.setObstructed(false);
            window_Obstruct.setImage(window_unobstructed);
        }else{
            window.setObstructed(true);
            window_Obstruct.setImage(window_obstructed);
        }
        App.log(window.getLocation()+" "+window.getName()+" "+(isWindow_Obstructed() ? "Obstructed":"UnObstructed"));
    }

    public boolean isWindow_Obstructed(){
        return this.window.isObstructed();
    }
}
