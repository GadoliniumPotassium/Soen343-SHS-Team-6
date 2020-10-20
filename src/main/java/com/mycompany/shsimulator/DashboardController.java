/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shsimulator;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.util.Callback;

/**
 *
 * @author Vladislav
 */
public class DashboardController {
    
    
    // a string bool pair
    private class PairStrBool {
        public String name;
        public SimpleBooleanProperty selected;
        
        PairStrBool(String s) {
            this.name = s;
            this.selected = new SimpleBooleanProperty(false);
        }
        
        public BooleanProperty selectedProperty() {
            return selected;
        }
        
        @Override
        public String toString() {
            return name;
        }
       
        
    }
    @FXML ListView<String> shcItemList;
    @FXML ListView<PairStrBool> shcOnOffList;
    @FXML TitledPane itemTitlePane; // open/close text
    
    ObservableList<String> items = FXCollections.observableArrayList(
            "Windows", 
            "Lights", 
            "Doors"
    );

    
    ObservableList<PairStrBool> windows = FXCollections.observableArrayList(new PairStrBool("Bedroom"), new PairStrBool("Kitchen"), new PairStrBool("Hall"));
    ObservableList<PairStrBool> lights = FXCollections.observableArrayList(new PairStrBool("Bedroom"), new PairStrBool("Kitchen"), new PairStrBool("Hall"));
    ObservableList<PairStrBool> doors = FXCollections.observableArrayList(new PairStrBool("Bedroom"), new PairStrBool("Kitchen"), new PairStrBool("Hall"));
    
    @FXML
    public void initialize() {

        System.out.println(items);
        shcItemList.setItems(items);
        
        // add callbacks for each item in the observed arrays to receive notification when the selected boolean property changes
        windows.forEach(window -> window.selectedProperty().addListener((observer, wasSelected, isSelected) -> {
                //System.out.println("[SHC] window '" + window.toString() + "' " + (isSelected?"Opened":"Closed"));
                App.log("[SHC] window '" + window.toString() + "' " + (isSelected?"Opened":"Closed"));
        }));
        
        lights.forEach(light -> light.selectedProperty().addListener((observer, wasSelected, isSelected) -> {
                //System.out.println("[SHC] light '" + light.toString() + "' " + (isSelected?"Opened":"Closed"));
                App.log("[SHC] light '" + light.toString() + "' " + (isSelected?"Turned On":"Turned Off"));
        }));
        
        doors.forEach(door -> door.selectedProperty().addListener((observer, wasSelected, isSelected) -> {
                //System.out.println("[SHC] door '" + door.toString() + "' " + (isSelected?"Opened":"Closed"));
                App.log("[SHC] door '" + door.toString() + "' " + (isSelected?"Opened":"Closed"));
        }));
        
        
        // item click callback
        shcItemList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                
                //System.out.println("[SHC] Selected item: " + newValue);
                App.log("[SHC] Selected item: " + newValue);
                
                // Switches the list of windows lights or doors as a CheckBoxList
                if (newValue.equals("Windows")) {
                    shcOnOffList.setItems(windows);
                    itemTitlePane.setText("Open/Close");
                } else if (newValue.equals("Lights")) {
                    shcOnOffList.setItems(lights);
                    itemTitlePane.setText("On/Off");
                } else if (newValue.equals("Doors")) {
                    shcOnOffList.setItems(doors);
                    itemTitlePane.setText("Open/Close");
                }

/*
                // given a PairStrBool, we return the boolean property that represents
                // whether the window is open or not. We can then bind to this
                // bidirectionally.
                Callback<PairStrBool, ObservableValue<Boolean>> getProperty = 
                    (PairStrBool p) -> p.selected;
*/                

                // set the cell factory to a CheckBoxList
                shcOnOffList.setCellFactory(CheckBoxListCell.forListView(PairStrBool::selectedProperty));  
            }
        });
        
    }
    
    
}
