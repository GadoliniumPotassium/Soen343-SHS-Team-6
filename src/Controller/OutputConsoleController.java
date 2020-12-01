/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import main.App;
import main.Main;

/**
 *
 * @author Vladislav
 */
public class OutputConsoleController {


    public JFXTextArea textArea;

    //save the reference to the output console globally for the App
    @FXML
    private void initialize() {
        App.outputConsole = this;
    }

    private Main main = Main.getInstance();

    /**
     * This method outputs to the log
     * @param string
     */
    public void log(String string) {
        textArea.setText("[" + main.settings.getDate()+" "+main.settings.getTime() + "] " + string + "\n" + textArea.getText());
    }

    /**
     * This method clears the output controller
     * @param actionEvent
     */
    public void clear(ActionEvent actionEvent) {
        textArea.clear();
        System.out.println("The button was clicked!");
    }
}

