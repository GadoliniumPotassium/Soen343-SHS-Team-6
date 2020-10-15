/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shsimulator;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;

/**
 *
 * @author Vladislav
 */
public class MainController {
    @FXML ToggleButton onBtn;
    @FXML OutputConsoleController outputConsoleController;
    
    @FXML
    public void initialize() {
        outputConsoleController.log("Start...");
    }
}
