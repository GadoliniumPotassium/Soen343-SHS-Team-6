package main;

import Controller.OutputConsoleController;
import Model.Summer;
import com.sun.javafx.geom.AreaOp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    /**
     * This method is overriden from Javafx application in order to start the app
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        // load the main window scene here

        scene = new Scene(loadFXML("mami"));
        
        stage.setScene(scene);
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("../FXML/"+fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * The main method of the program
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
    
    /* store a reference to the output console controller */
    public static OutputConsoleController outputConsole;



    /* log a message to the output console */
    public static void log(String message) {
       if (outputConsole != null)
            outputConsole.log(message);

    }
    
}