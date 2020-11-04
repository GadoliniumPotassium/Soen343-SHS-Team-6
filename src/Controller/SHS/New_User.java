package Controller.SHS;

import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;

public class New_User {

    public TextField user_textField;
    public ChoiceBox permissions;
    public PasswordField pass_textField;
    public Label msg_label;
    @FXML
    void initialize(){
        setPermissions();
    }

    public void add(ActionEvent actionEvent) {
        User newUser = new User();
        if(user_textField.getText().isEmpty() ||
                pass_textField.getText().isEmpty()){
            msg_label.setText("Please Enter the value");
            return;
        }
        String uname = user_textField.getText().replaceAll("\\s+","");
        if(uname.equals(user_textField.getText()))
            newUser.setUsername(uname);
        else
            msg_label.setText("Please remove the white spaces from username");

        newUser.setPassword(pass_textField.getText());
        newUser.setUserPermission(
                (permissions.getValue().equals("full")? User.permissions.full :
                        (permissions.getValue().equals("partial")? User.permissions.partial: User.permissions.none))
        );
        Main.user_list.add(newUser);
        Stage stage =  (Stage)msg_label.getScene().getWindow();
        stage.close();
    }
    private void setPermissions() {
        permissions.getItems().add("full");
        permissions.getItems().add("partial");
        permissions.getItems().add("none");
    }
}
