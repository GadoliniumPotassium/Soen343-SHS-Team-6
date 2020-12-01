package Controller.SHC;

import Model.SmartWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.App;
import main.Main;
/**
 * class to provide window status
 */
public class Window_Status {

    public Label window_name;
    public ImageView window_status;
    public ImageView window_lock;
    public ImageView window_Obstruct;

    private Image window_close;
    private Image window_open;
    private Image window_obstructed;
    private Image window_unobstructed;

    private SmartWindow window;

    private Main main = Main.getInstance();

    @FXML
    void initialize(){
        window_open = new Image("FXML/Images/window_open.png");
        window_close = new Image("FXML/Images/window_close.png");
        window_obstructed = new Image("FXML/Images/caution_p.png");
        window_unobstructed = new Image("FXML/Images/caution.png");
    }

    /**
     * This method toggles the open close state of a window
     * @param actionEvent
     */
    public void window_open_close(ActionEvent actionEvent) {
        if(main.away_mode){
            App.log("You can not open the window in away mode active");
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

    /**
     * This method sets the lock unlock state of the window
     * @param window
     */
    public void setWindow(SmartWindow window) {
        this.window = window;
        window_name.setText(window.getName());

        if(isWindow_open())
            window_status.setImage(window_open);
        else
            window_status.setImage(window_close);

        if(window.isObstructed())
            window_Obstruct.setImage(window_obstructed);
        else
            window_Obstruct.setImage(window_unobstructed);
    }

    /**
     * This method returns true if the window is open
     * @return
     */
    public boolean isWindow_open() {
        return this.window.isOpen();
    }

    /**
     * This method toggles the window obstruction state
     * @param actionEvent
     */
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

    /**
     * This method returns if the window is obstructed
     * @return
     */
    public boolean isWindow_Obstructed(){
        return this.window.isObstructed();
    }
}
