/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shsimulator;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.util.Callback;

/**
 *
 * @author Vladislav
 */
public class DashboardController {
    
    
    private class PairStrBool {
        String name;
        SimpleBooleanProperty b;
        PairStrBool(String s) {
            this.name = s;
            this.b = new SimpleBooleanProperty(false);
        }
        @Override
        public String toString() {
            return name;
        }
        
    }
    @FXML ListView<String> shcItemList;
    @FXML ListView<PairStrBool> shcOnOffList;
    
    ObservableList<String> items = FXCollections.observableArrayList(
            "Windows", 
            "Lights", 
            "Doors"
    );

    ObservableList<String> windows = FXCollections.observableArrayList("Bedroom", "Kitchen", "Hall");
    ObservableList<PairStrBool> lights = FXCollections.observableArrayList(new PairStrBool("Bedroom"), new PairStrBool("Kitchen"), new PairStrBool("Hall"));
    ObservableList<PairStrBool> doors = FXCollections.observableArrayList(new PairStrBool("Bedroom"), new PairStrBool("Kitchen"), new PairStrBool("Hall"));
    
    @FXML
    public void initialize() {

        
        System.out.println(items);
        shcItemList.setItems(items);
        shcOnOffList.setItems(lights);
        shcOnOffList.setEditable(true);
        
        // set the cell factory
        // given a person, we return the property that represents
        // whether or not they are invited. We can then bind to this
        // bidirectionally.
        Callback<PairStrBool, ObservableValue<Boolean>> getProperty = 
                (PairStrBool p) -> p.b;
        
        
        shcOnOffList.setCellFactory(CheckBoxListCell.forListView(getProperty));  
        
    }
}
