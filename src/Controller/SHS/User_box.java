package Controller.SHS;

import Controller.LoginController;
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

public class User_box {

    public ComboBox<String> locations;
    @FXML private Label username_label;

    private ListView listView;
    private User user;

    @FXML
    void initialize(){

    }

    public void load_user(User user, ListView users_listView) {
        this.user = user;
        this.listView = users_listView;
        updateValues();

    }
    private void updateValues(){
        username_label.setText(this.user.getUsername());
        Main.outSides.forEach(outSide -> {
            locations.getItems().add(outSide.getName());
        });
        Main.rooms_list.forEach(r->{
            locations.getItems().add(r.getName());
        });
        locations.setValue(user.getLocation());
    }

    @FXML private void login_user(ActionEvent actionEvent) {
        if(Main.isIsSimulationRunning()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../FXML/Login.fxml"));
            AnchorPane root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.user.setIsloggedIn(false);
            Main.setIsSimulationRunning(false);
            LoginController lc = loader.getController();
            lc.setTxtUserName(user.getUsername());
            LoginMain.setMainRoot(root);
        }else
            App.log("Simulation is off");
    }

    @FXML private void edit_user(ActionEvent actionEvent) {
        if(Main.isIsSimulationRunning()) {
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

    @FXML private void change_location(ActionEvent actionEvent) {
        if(Main.isIsSimulationRunning()) {
            this.user.setLocation(locations.getValue());
            App.log(this.user.getUsername()+" location is changed to"+this.user.getLocation());
        }else {
            locations.setValue(user.getLocation());
            App.log("Simulation is not Running");
        }
    }

    @FXML private void remove_user(ActionEvent actionEvent) {
        if(Main.isIsSimulationRunning()) {
            if(this.user != Main.active_user) {
                if (Main.user_list.contains(this.user)) {
                    App.log(this.user.getUsername()+" user is removed.");
                    Main.user_list.remove(this.user);
                }
            }else
                App.log("You can not remove active user.");

            listView.getItems().clear();

            Main.user_list.forEach(user -> {
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
}
