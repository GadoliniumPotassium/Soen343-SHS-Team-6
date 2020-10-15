/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shsimulator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 *
 * @author Vladislav
 */
public class OutputConsoleController {
    @FXML private TextArea textArea;
    
    
    //save the reference to the output console globally for the App
    @FXML
    private void initialize() {
        App.outputConsole = this;
    }
    
    
    
    public void log(String string) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("[HH:mm]");  
        textArea.setText("[" + dtf.format(now) + "] " + string + "\n" + textArea.getText());
    }
    
    @FXML
    protected void doSomething() {
        System.out.println("The button was clicked!");
    }
}

