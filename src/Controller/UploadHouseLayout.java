package Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class UploadHouseLayout {
    public Label file_name;
    public Button uhl;


    public void upload_house_layout(ActionEvent actionEvent) {
        try {
            Stage primaryStage = (Stage) uhl.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../FXML/mami.fxml"));
            Scene scene = new Scene(root);

            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void choose_file(ActionEvent actionEvent) {
        uhl.setDisable(true);
        FileChooser fileChooser = new FileChooser();

        Stage dialog = new Stage();
        File file = fileChooser.showOpenDialog(dialog);
        //System.out.println(file == null ? "no file chosen" : file.toString());
        if(file != null)
            file_name.setText(file.toString());
        else
            file_name.setText("no file");
        uhl.setDisable(false);
    }
}
