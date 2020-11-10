/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.SHC.*;
import Controller.SHP.Light_box;
import Controller.SHS.User_box;
import Model.*;
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
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Vladislav
 */
public class DashboardController {


    public JFXToggleButton automatic_lights;
    // SHC
    @FXML private VBox listView;

    @FXML private ListView<HBox> home_outside_list;
    @FXML public ListView<HBox> doors;
    @FXML public ListView<VBox> lights;

    // SHS
    @FXML private ListView<HBox> users_listView;
    @FXML public TabPane tabPane;

    @FXML private TextField temp_textField;
    @FXML private DatePicker datepicker;
    @FXML private TextField hour_tf;
    @FXML private TextField minutes_tf;

    @FXML private TextField room_name;
    @FXML private TextField doors_input;
    @FXML private TextField windows_input;
    @FXML private TextField lights_input;
    @FXML private TextField temp_input;
    
    //SHP
    @FXML private JFXToggleButton away_mode;
    @FXML private ListView<HBox> lights_outside_listview;
    @FXML private ListView<HBox> lights_inside_listview;
    @FXML private ComboBox<String> alert_time;
    @FXML private ListView<HBox> monitoring_listview;
//    public TextField hour_shp;
//    public TextField min_shp;

    /*
    These are singleton classes.
     */
    private Main main = Main.getInstance();
    private NumFieldFX numFieldFX = NumFieldFX.getInstance();

    private ArrayList<MotionDetector> detectors = new ArrayList<>();

    @FXML
    public void initialize() {

        numFieldFX.numField(temp_textField);
        numFieldFX.numField(hour_tf);
        numFieldFX.numField(minutes_tf);
        numFieldFX.numField(doors_input);
        numFieldFX.numField(windows_input);
        numFieldFX.numField(lights_input);
        numFieldFX.numField(temp_input);
        numFieldFX.numField(temp_input);

        load_SHC();
        load_SHS();

        alert_time.getItems().add("10 Minutes");
        alert_time.getItems().add("5 Minutes");
        alert_time.getItems().add("2 Minutes");
        alert_time.getItems().add("1 Minutes");

        alert_time.setValue("2 Minutes");
    }

    private void load_SHS(){
        users_listView.getItems().clear();

        user_box();

        temp_textField.setText(main.settings.getTemperature()+"");
        datepicker.setValue(LocalDate.now());
        String[] time = main.settings.getTime().split(":");
        hour_tf.setText(time[0]);
        minutes_tf.setText(time[1]);
    }
    private void user_box(){
        users_listView.getItems().clear();
        main.user_list.forEach(user -> {
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

    /**
     * Loads SHC
     */
    private void load_SHC(){
        listView.getChildren().clear();
        for(Room e : main.rooms_list){

            if(main.active_user.getUserPermission().name().equals("full")) {

                listView.getChildren().add(room_item(e));
            } else if(main.active_user.getUserPermission().name().equals("partial")){
                int user_location = main.user_location_room(main.active_user.getLocation());
                if(user_location == -1) continue;
                if(e.getName().equals(main.rooms_list.get(user_location).getName())){
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

    /**
     * This method does the GUI for the outside home
     */
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

        main.outSides.forEach(outSide -> {
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

    /**
     * does something
     * @param e
     * @return
     */
    public HBox home_outSide_item(House e){
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

    /**
     * Gets the room items
     * @param e
     * @return
     */
    private HBox room_item(Room e){
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

    /**
     * Updates the SHS
     * @param actionEvent
     */
    public void update_SHS(ActionEvent actionEvent) {
        if(temp_textField.getText().isEmpty() || hour_tf.getText().isEmpty() || minutes_tf.getText().isEmpty()) return;
        main.settings.setTemperature(Float.parseFloat(temp_textField.getText()));
        main.settings.setDate(datepicker.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        main.settings.setTime(hour_tf.getText()+":"+minutes_tf.getText());
    }

    /**
     * This method serves to add a user via the GUI
     * @param actionEvent
     */
    public void add_user(ActionEvent actionEvent) {
        if(main.isIsSimulationRunning()) {
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

    /**
     * This method allows you to add room info and even add a whole room
     * @param actionEvent
     */
    public void add_room(ActionEvent actionEvent) {
        if(room_name.getText().isEmpty() ||
        doors_input.getText().isEmpty() ||
        windows_input.getText().isEmpty() ||
        lights_input.getText().isEmpty() ||
        temp_input.getText().isEmpty()) return;

        Room room = new Room();

        room.setName(room_name.getText());
        room.setDoors(Integer.parseInt(doors_input.getText()));
        room.setWindows(Integer.parseInt(windows_input.getText()));
        room.setLights(Integer.parseInt(lights_input.getText()));
        room.setTemperature(Float.parseFloat(temp_input.getText()));

        if(!main.room_exists(room.getName())) {
            main.rooms_list.add(room);
            App.log("Added New Room [ "+room.getName()+" ]");
        } else
            App.log("Room Already Exists with this name ("+room.getName()+")");
    }

    //SHP

    /**
     * SHP Selection
     * @param event
     */
    public void shp_selection(Event event) {
        setLights_listview();
        monitoring();
        update_monitoring();
        away_mode.selectedProperty().setValue(main.away_mode);
    }

    /**
     * This method serves to manage the detectors
     */
    public void monitoring(){
        detectors.clear();
        main.rooms_list.forEach(room -> {
            MotionDetector detector = room.getMotionDetector();
            detectors.add(detector);
        });
    }

    /**
     * This method serves to update the room monitoring in case of a user room change
     */
    public void update_monitoring(){
        this.monitoring_listview.getItems().clear();
        HBox heading = new HBox();
        heading.setAlignment(Pos.CENTER_LEFT);
        heading.setStyle("fx-background-color: #f1f6f9");

        Label room_name = new Label("Location");
        room_name.setPrefWidth(125);
        room_name.setTextFill(Color.web("#14274e"));
        room_name.setFont(Font.font("Bell MT",18));

        Label someone_in_room = new Label("Someone in Room");
        someone_in_room.setPrefWidth(125);
        someone_in_room.setTextFill(Color.web("#14274e"));
        someone_in_room.setFont(Font.font("Bell MT",18));
        heading.getChildren().addAll(room_name,someone_in_room);

        this.monitoring_listview.getItems().add(heading);

        detectors.forEach(motionDetector -> {
            HBox s_box = new HBox();
            s_box.setAlignment(Pos.CENTER_LEFT);
            s_box.setStyle("fx-background-color: #f1f6f9");

            Label s_loc = new Label(motionDetector.room_name());
            s_loc.setPrefWidth(125);
            s_loc.setTextFill(Color.web("#14274e"));
            s_loc.setFont(Font.font("Bell MT",18));

            Label s_someoneThere = new Label(motionDetector.isSomeoneThere() ? "Yes":"No");
            s_someoneThere.setPrefWidth(125);
            s_someoneThere.setTextFill(Color.web("#14274e"));
            s_someoneThere.setFont(Font.font("Bell MT",18));

            s_box.getChildren().addAll(s_loc,s_someoneThere);
            this.monitoring_listview.getItems().add(s_box);

        });

    }

    /**
     * This method serves to get the list of the lights?
     */
    public void setLights_listview(){
        lights_outside_listview.getItems().clear();
        main.lights_outside.forEach(light ->{
            lights_outside_listview.getItems().add(light_box(light));
        });
        lights_inside_listview.getItems().clear();
        main.lights_inside.forEach(light ->{
            lights_inside_listview.getItems().add(light_box(light));
        });
    }

    /**
     * This method serves as a light box manager
     * @param _light
     * @return
     */
    public HBox light_box(SmartModule _light){
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

    /**
     * This method sets the house in Away Mode from the GUI
     * @param actionEvent
     */
    public void setAwayMode(ActionEvent actionEvent) {
        if(!main.isIsSimulationRunning()) {
            away_mode.selectedProperty().setValue(false);
            App.log("Simulation is not running");
            return;
        }
        if(main.active_user.getUserPermission() == User.permissions.none){
            away_mode.selectedProperty().setValue(false);
            App.log("Sorry you don't have any authority.");
            return;
        }
        if(main.windows_inside.stream().filter(window -> ((SmartWindow)window).isObstructed()).count() > 0){
            App.log("Can not Activate the Away Mode Doors/ Windows are Obstructed");
            away_mode.selectedProperty().setValue(false);
            return;
        }
        boolean off = away_mode.selectedProperty().get();
        main.away_mode = off;

        // check if users in the room and msg to the console and not active away mode if users in room.
        int n = usersInRoom();
        if(!off){
            App.log("Away Mode is off now you can unlock doors");
            return;
        }
        if(n > 0){
            away_mode.selectedProperty().setValue(false);
            App.log(n+" Members in Home Can not active Away Mode");
            main.away_mode = false;
        }else{
            // send command to SHC to close all doors and windows and lock them.
            main.doors_inside.forEach(door -> {
                ((SmartWindow)door).setOpen(false);
                ((SmartWindow)door).setLocked(true);
            });
            App.log("Doors are closed and locked home inside");
            main.windows_inside.forEach(window ->{
                ((SmartWindow)window).setOpen(false);
                ((SmartWindow)window).setLocked(true);
            });
            App.log("Windows are closed and locked home inside");
            main.doors_outside.forEach(d ->{
                ((SmartWindow)d).setOpen(false);
                ((SmartWindow)d).setLocked(true);
            });
            App.log("Doors are closed and locked home outside");

        }
    }
    public int usersInRoom(){
        AtomicInteger count = new AtomicInteger();
        main.user_list.forEach(user -> {
            count.addAndGet((int)main.rooms_list.stream().filter(room -> room.getName().equals(user.getLocation())).count());
//            System.out.println(count.get());
        });
        return count.get();
    }

    /**
     * This method allows us from the GUI to  change the users location
     * @param actionEvent
     */
    public void set_users_locations_to_outside(ActionEvent actionEvent) {
        if(!main.isIsSimulationRunning()){
            App.log("Simulation is not running");
            return;
        }
        main.user_list.forEach(user ->{
            user.setLocation(main.outSides.get(new Random().nextInt(main.outSides.size())).getName());
        });
        detectors.forEach(MotionDetector::deduct);
        update_monitoring();
        App.log("All Users locations set to outsides");
    }

    /**
     * This method allows us to set the alert time
     * @param mouseEvent
     */
    public void setAlertTime(ActionEvent mouseEvent) {
        main.settings.setAlertTiming(Integer.parseInt(String.valueOf(alert_time.getValue().charAt(0))));
    }

    /**
     * This method handles the auto light toggling whether a user entered or left a room
     * @param actionEvent
     */
    public void automic_lights_on_off(ActionEvent actionEvent) {
        if(!main.isIsSimulationRunning() || main.away_mode) {
            automatic_lights.selectedProperty().setValue(false);
            App.log("cannot active automatic lights right now");
            return;
        }
        boolean on = automatic_lights.selectedProperty().getValue();
        main.automatic_lights = on;
        if(!on){

            App.log("Automatic Lights Smart System is off.");
        }else{
            main.rooms_list.forEach(room -> {
                MotionDetector detector = room.getMotionDetector();
                if(detector.isSomeoneThere()){
                    main.lights_inside.forEach(light ->{
                        if(light.getLocation().equals(room.getName())){
                            ((SmartLight)light).setOn(true);
                        }
                    });
                }else{
                    main.lights_inside.forEach(light ->{
                        if(light.getLocation().equals(room.getName())){
                            ((SmartLight)light).setOn(false);
                        }
                    });
                }
            });
            App.log("Automatic Lights Smart System is on.");
        }
    }
}