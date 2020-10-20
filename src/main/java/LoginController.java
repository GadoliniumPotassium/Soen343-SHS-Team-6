package application;
import javafx.event.ActionEvent;
import javafx.fxml.FMXL;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
public class Controller extends Application{
    @FXML
    private Label lblStatus;
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtPassword;
    public void Login(ActionEvent event) throws Exception{
        if (txtUserName.getText().equals("user") && txtPassword.getText().equals("pass")){
            lblStatus.setText("Login Success");
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("mami.fxml"));
            Scene scene=new Scene(root,400,400);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        }else{
            lblStatus.setText("Login Failed");
        }
    }
}