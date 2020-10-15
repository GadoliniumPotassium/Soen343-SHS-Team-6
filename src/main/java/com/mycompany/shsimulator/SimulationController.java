/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shsimulator;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;

/**
 *
 * @author Vladislav
 */
public class SimulationController {
    @FXML private ToggleButton onToggleBtn;
    
    @FXML private void initialize() {
        
        // add click listener to the ON button
        onToggleBtn.setOnAction(new EventHandler() {
            @Override
            public void handle(Event t) {
                Boolean isOn = onToggleBtn.selectedProperty().get();
                System.out.println("Simulation btn clicked" + t);
                App.log("Simulation: " + (isOn?"On":"Off" ));
            }
        });
    }
}
