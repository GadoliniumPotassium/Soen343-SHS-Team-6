package Controller.SHS;

import Controller.LoginController;
import Controller.SHP.User_Notify;
import Model.MotionDetector;
import Model.SmartLight;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.App;
import main.LoginMain;
import main.Main;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class User_box {

    public ComboBox<String> locations;
    @FXML private Label username_label;

    private ListView listView;
    private User user;

    private Main main = Main.getInstance();

    @FXML
    void initialize(){

    }

    /**
     * This method serves to load the users
     * @param user
     * @param users_listView
     */
    public void load_user(User user, ListView users_listView) {
        this.user = user;
        this.listView = users_listView;
        updateValues();

    }

    /**
     * This method serves to update the user values in the GUI
     */
    private void updateValues(){
        username_label.setText(this.user.getUsername());
        main.outSides.forEach(outSide -> {
            locations.getItems().add(outSide.getName());
        });
        main.rooms_list.forEach(r->{
            locations.getItems().add(r.getName());
        });
        locations.setValue(user.getLocation());
    }

    /**
     * This method serves to log in the user in the GUI
     * @param actionEvent
     */
    @FXML private void login_user(ActionEvent actionEvent) {
        if(main.isIsSimulationRunning()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../FXML/Login.fxml"));
            AnchorPane root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.user.setIsloggedIn(false);
            main.setIsSimulationRunning(false);
            LoginController lc = loader.getController();
            lc.setTxtUserName(user.getUsername());
            LoginMain.setMainRoot(root);
        }else
            App.log("Simulation is off");
    }

    /**
     * This method serves to edit the user in the GUI
     * @param actionEvent
     */
    @FXML private void edit_user(ActionEvent actionEvent) {
        if(main.isIsSimulationRunning()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../FXML/SHS/user_details.fxml"));
            AnchorPane root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            User_Details ud = loader.getController();
            ud.setUser(this.user);
            Stage ss = new Stage();
            ss.setScene(new Scene(root));
            ss.initModality(Modality.APPLICATION_MODAL);
            ss.showAndWait();
            App.log(this.user.getUsername()+" Details is changed");
            updateValues();
        }else
            App.log("Simulation is not Running");
    }

    /**
     * This method serves to change the location in the GUI
     * @param actionEvent
     */
    @FXML private void change_location(ActionEvent actionEvent) {
        if(main.isIsSimulationRunning()) {
            String preLoc = this.user.getLocation();
            this.user.setLocation(locations.getValue());
            App.log(this.user.getUsername() + " location is changed to" + this.user.getLocation());

            main.rooms_list.forEach(room -> {
                // User Enter in new Room
                if(room.getName().equals(user.getLocation())){
                    MotionDetector c_md = room.getMotionDetector();
                    c_md.count();
                    if(main.automatic_lights && c_md.isSomeoneThere()){
                        main.lights_inside.forEach( e -> {
                            if(e.getLocation().equals(room.getName()))
                                ((SmartLight)e).setOn(true);
                        });
                    }
                }
                // User left the room.
                if(room.getName().equals(preLoc)){
                    MotionDetector p_md = room.getMotionDetector();
                    p_md.deduct();
                    if(main.automatic_lights && !p_md.isSomeoneThere()){
                        main.lights_inside.forEach(e ->{
                            if(e.getLocation().equals(room.getName()))
                                ((SmartLight)e).setOn(false);
                        });
                    }
                }
            });

            if(main.away_mode && usersInRoom() > 0){
                // open the popup window.
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../FXML/SHP/user_notify.fxml"));
                AnchorPane pane = null;
                try{
                    pane = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                User_Notify controller = loader.getController();
                controller.setup(user.getLocation(),"Alert: Smart house Security Detect someone in this room waiting for your response.");

                Stage stage = new Stage();
                stage.setScene(new Scene(pane));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
                stage.setOnCloseRequest(e->{
                    App.log("User Ignore: Alerting the authorities by Smart House System");
                });
            }

        }else {
            locations.setValue(user.getLocation());
            App.log("Simulation is not Running");
        }
    }

    /**
     * This method serves to remove a user
     * @param actionEvent
     */
    @FXML private void remove_user(ActionEvent actionEvent) {
        if(main.isIsSimulationRunning()) {
            if(this.user != main.active_user) {
                if (main.user_list.contains(this.user)) {
                    App.log(this.user.getUsername()+" user is removed.");
                    main.user_list.remove(this.user);
                }
            }else
                App.log("You can not remove active user.");

            listView.getItems().clear();

            main.user_list.forEach(user -> {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../FXML/SHS/user_box.fxml"));
                HBox user_box = null;
                try {
                    user_box = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                User_box ub_controller = loader.getController();
                ub_controller.load_user(user,listView);

                listView.getItems().add(user_box);
            });
        }else
            App.log("Simulation is not Running");
    }

    /**
     * This method returns in  which rooms the users are in
     * @return
     */
    public int usersInRoom(){
        AtomicInteger count = new AtomicInteger();
        main.user_list.forEach(user -> {
            count.addAndGet((int)main.rooms_list.stream().filter(room -> room.getName().equals(user.getLocation())).count());
//            System.out.println(count.get());
        });
        return count.get();
    }
}
