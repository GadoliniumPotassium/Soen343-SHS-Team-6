/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.SHC.*;
import Controller.SHP.Light_box;
import Controller.SHS.User_box;
import Model.House;
import Model.SmartLight;
import Model.SmartWindow;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.App;
import main.Main;
import main.NumFieldFX;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Vladislav
 */
public class DashboardController {


    // SHC
    @FXML public VBox listView;

    public ListView home_outside_list;
    public ListView doors;
    public ListView lights;

    // SHS
    public ListView users_listView;
    public TabPane tabPane;

    public TextField temp_textField;
    public DatePicker datepicker;
    public TextField hour_tf;
    public TextField minutes_tf;

    public TextField room_name;
    public TextField doors_input;
    public TextField windows_input;
    public TextField lights_input;
    public TextField temp_input;
    
    //SHP
    public JFXToggleButton away_mode;
    public ListView lights_listview;
    public TextField hour_shp;
    public TextField min_shp;

    @FXML
    public void initialize() {

        new NumFieldFX().numField(temp_textField);
        new NumFieldFX().numField(hour_tf);
        new NumFieldFX().numField(minutes_tf);
        new NumFieldFX().numField(doors_input);
        new NumFieldFX().numField(windows_input);
        new NumFieldFX().numField(lights_input);
        new NumFieldFX().numField(temp_input);
        new NumFieldFX().numField(temp_input);

        load_SHC();
        load_SHS();
    }

    private void load_SHS(){
        users_listView.getItems().clear();

        user_box();

        temp_textField.setText(Main.settings.getTemperature()+"");
        datepicker.setValue(LocalDate.now());
        String[] time = Main.settings.getTime().split(":");
        hour_tf.setText(time[0]);
        minutes_tf.setText(time[1]);
    }
    private void user_box(){
        users_listView.getItems().clear();
        Main.user_list.forEach(user -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/SHS/user_box.fxml"));
            HBox user_box = null;
            try {
                user_box = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            User_box ub_controller = loader.getController();
            ub_controller.load_user(user,users_listView);

            users_listView.getItems().add(user_box);
        });
    }


    private void load_SHC(){
        listView.getChildren().clear();
        for(House.Room e : Main.rooms_list){

            if(Main.active_user.getUserPermission().name().equals("full")) {

                listView.getChildren().add(room_item(e));
            } else if(Main.active_user.getUserPermission().name().equals("partial")){
                int user_location = Main.user_location_room(Main.active_user.getLocation());
                if(user_location == -1) continue;
                if(e.getName().equals(Main.rooms_list.get(user_location).getName())){
                    listView.getChildren().add(room_item(e));
                }
            }else{
                Label msg = new Label("Stranger has no Permission");
                msg.setFont(Font.font("Bell MT",35));
                msg.setTextFill(Color.web("#14274e"));
                HBox msg_box = new HBox();
                msg_box.setAlignment(Pos.CENTER);
                msg_box.getChildren().add(msg);
                listView.getChildren().add(msg_box);
                break;
            }
        }
        home_outside();
    }

    public void home_outside(){
        home_outside_list.getItems().clear();
        HBox heading = new HBox();
        heading.setAlignment(Pos.CENTER);
        heading.setSpacing(40);
        heading.setStyle("fx-background-color: #f1f6f9");

        Label name = new Label("Name");
        name.setFont(Font.font("Bell MT",18));
        name.setTextFill(Color.web("#14274e"));

        Label door = new Label("Doors");
        door.setFont(Font.font("Bell MT",18));
        door.setTextFill(Color.web("#14274e"));

        Label lights = new Label("Lights");
        lights.setFont(Font.font("Bell MT",18));
        lights.setTextFill(Color.web("#14274e"));

        Label users_label = new Label("Users");
        users_label.setFont(Font.font("Bell MT",18));
        users_label.setTextFill(Color.web("#14274e"));

        heading.getChildren().addAll(name,door,lights,users_label);

        home_outside_list.getItems().add(heading);

        Main.outSides.forEach(outSide -> {
            HBox item = home_outSide_item(outSide);
            item.setOnMouseClicked(e->{
                FXMLLoader loader = new FXMLLoader(getClass().getResource(
                        "../FXML/SHC/Home_OutSide_Details.fxml"
                ));
                AnchorPane pane = null;
                try{
                    pane = loader.load();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                OutSide_Details controller = loader.getController();
                controller.setOutside(outSide);
                Stage pop = new Stage();
                pop.setScene(new Scene(pane));
                pop.initModality(Modality.APPLICATION_MODAL);
                pop.showAndWait();
            });

            home_outside_list.getItems().add(item);
        });
    }


    public HBox home_outSide_item(House.OutSide e){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/SHC/home_outside_item.fxml"));
        HBox hbox = null;
        try {
            hbox = loader.load();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Home_OutSide controller = loader.getController();
        controller.setValues(e);
        return hbox;
    }

    private HBox room_item(House.Room e){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/SHC/room_item.fxml"));
        HBox hbox = null;
        try {
            hbox = loader.load();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Room_Item controller = loader.getController();
        controller.setValues(e);
        return hbox;
    }

    public void update_SHS(ActionEvent actionEvent) {
        if(temp_textField.getText().isEmpty() || hour_tf.getText().isEmpty() || minutes_tf.getText().isEmpty()) return;
        Main.settings.setTemperature(Float.parseFloat(temp_textField.getText()));
        Main.settings.setDate(datepicker.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        Main.settings.setTime(hour_tf.getText()+":"+minutes_tf.getText());
    }

    public void add_user(ActionEvent actionEvent) {
        if(Main.isIsSimulationRunning()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/SHS/New_User.fxml"));
            AnchorPane root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            user_box();
        }else
            App.log("Simulation is not Running");
    }

    public void shs_selection(Event event) {
        load_SHS();
    }

    public void shc_selection(Event event) {
        load_SHC();
    }

    public void add_room(ActionEvent actionEvent) {
        if(room_name.getText().isEmpty() ||
        doors_input.getText().isEmpty() ||
        windows_input.getText().isEmpty() ||
        lights_input.getText().isEmpty() ||
        temp_input.getText().isEmpty()) return;

        House room = new House();

        room.room.setName(room_name.getText());
        room.room.setDoors(Integer.parseInt(doors_input.getText()));
        room.room.setWindows(Integer.parseInt(windows_input.getText()));
        room.room.setLights(Integer.parseInt(lights_input.getText()));
        room.room.setTemperature(Float.parseFloat(temp_input.getText()));

        if(!new Main().room_exists(room.room.getName())) {
            Main.rooms_list.add(room.room);
            App.log("Added New Room [ "+room.room.getName()+" ]");
        } else
            App.log("Room Already Exists with this name ("+room.room.getName()+")");
    }

    //SHP

    public void shp_selection(Event event) {
        setLights_listview();
    }

    public void setLights_listview(){
        Main.lights_outside.forEach(light ->{
            lights_listview.getItems().add(light_box(light));
        });
        Main.lights_inside.forEach(light ->{
            lights_listview.getItems().add(light_box(light));
        });
    }

    public HBox light_box(SmartLight _light){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/SHP/light_box.fxml"));
        HBox hBox = null;
        try{
            hBox = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Light_box light_box = loader.getController();
        light_box.setLight(_light);
        return hBox;
    }

    public void setAwayMode(ActionEvent actionEvent) {
        boolean off = away_mode.selectedProperty().get();
        Main.away_mode = off;
        // check if users in the room and msg to the console and not active away mode if users in room.
        int n = usersInRoom();
        if(!off){
            App.log("Away Mode is off now you can unlock doors");
            return;
        }
        if(n > 0){
            away_mode.selectedProperty().setValue(false);
            App.log(n+" Members in Home Can not active Away Mode");
        }else{
            // send command to SHC to close all doors and windows and lock them.
            Main.doors_inside.forEach(door -> {
                door.setOpen(false);
                door.setLocked(true);
            });
            App.log("Doors are closed and locked home inside");
            Main.windows_inside.forEach(window ->{
                window.setOpen(false);
                window.setLocked(true);
            });
            App.log("Windows are closed and locked home inside");
            Main.doors_outside.forEach(d ->{
                d.setOpen(false);
                d.setLocked(true);
            });
            App.log("Doors are closed and locked home outside");

        }
    }
    public int usersInRoom(){
        AtomicInteger count = new AtomicInteger();
        Main.user_list.forEach(user -> {
            count.addAndGet((int)Main.rooms_list.stream().filter(room -> room.getName().equals(user.getLocation())).count());
//            System.out.println(count.get());
        });
        return count.get();
    }

    public void save_alert_time_shp(ActionEvent actionEvent) {
    }

}