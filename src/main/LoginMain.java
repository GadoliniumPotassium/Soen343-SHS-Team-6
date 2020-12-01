package main;

import Model.Summer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * class to create login for teh application
 */
public class LoginMain extends Application {


    private Main main = Main.getInstance();

    private static BorderPane main_root;

    /**
     * This method starts the login screen
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        main_root = new BorderPane();
        setSummers();
        try {
            main.loadData();

            // get full screen

            main_root.setStyle("-fx-background-color:  #f1f6f9");
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();
            main_root.setPrefWidth(bounds.getWidth());
            main_root.setPrefHeight(bounds.getHeight());

            Parent root = FXMLLoader.load(getClass().getResource("../FXML/Login.fxml"));
            main_root.setCenter(root);

            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(new Scene(main_root));
            primaryStage.setFullScreen(true);
            primaryStage.show();
            primaryStage.setOnCloseRequest(e->{
                main.saveData();
            });

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void setSummers(){
        Main.getInstance().summers.add(new Summer("Winter",25));
        Main.getInstance().summers.add(new Summer("Summer",60));
    }

    /**
     * Manipulates GUI elements
     * @param root
     */
    public static void setMainRoot(Node root){
        main_root.getChildren().clear();
        main_root.setCenter(root);
    }

    /**
     * Manipulates GUI elements
     * @return
     */
    public static BorderPane getMainRoot(){
        return main_root;
    }

    /**
     * Main method for the login
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
