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

    public void setTxtUserName(String _uname){
        txtUserName.setText(_uname);
    }

    public void Login(ActionEvent event) throws Exception {
        //if (controller.user_exists(txtUserName.getText(),txtPassword.getText())){
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
                //Controller controller = loader.getController();
                main.active_user.setIsloggedIn(true);
                //controller.setUserList(Main.user_list);
                LoginMain.setMainRoot(root);
                //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//                primaryStage.setScene(new Scene(LoginMain.getMainRoot()));
            }
//            primaryStage.show();
        } else {
            lblStatus.setText("Username or password incorrect");
        }
    }
}