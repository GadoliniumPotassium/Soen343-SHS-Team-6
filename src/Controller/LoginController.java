package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.LoginMain;
import main.Main;

/**
 * class for the login information
 */
public class LoginController {
    @FXML
    private Label lblStatus;
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtPassword;

    private Main main = Main.getInstance();

    @FXML
    void initialize(){

    }

    /**
     * This method serves to the set the username in the login
     * @param _uname
     */
    public void setTxtUserName(String _uname){
        txtUserName.setText(_uname);
    }

    /**
     * This method handles the login process
     * @param event
     * @throws Exception
     */
    public void Login(ActionEvent event) throws Exception {
        if (main.user_exists(txtUserName.getText(), txtPassword.getText())) {
            lblStatus.setText("Login Success");
            Stage primaryStage = (Stage) txtUserName.getScene().getWindow();
            Parent root;
            if (!main.house_loaded) {
                root = FXMLLoader.load(getClass().getResource("../FXML/UploadHouseLayout.fxml"));
                LoginMain.setMainRoot(root);
                Scene scene = new Scene(LoginMain.getMainRoot());
                primaryStage.setScene(scene);
            } else {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/mami.fxml"));
                root = loader.load();

                main.active_user.setIsloggedIn(true);

                LoginMain.setMainRoot(root);

            }

        } else {
            lblStatus.setText("Username or password incorrect");
        }
    }
}