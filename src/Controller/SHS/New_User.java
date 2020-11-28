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

    private Main main = Main.getInstance();

    @FXML
    void initialize(){
        setPermissions();
    }

    /**
     * This method serves to add a new user when the button is clicked
     * @param actionEvent
     */
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
<<<<<<< Updated upstream
                (permissions.getValue().equals("full")? User.permissions.full :
                        (permissions.getValue().equals("partial")? User.permissions.partial: User.permissions.none))
        );
=======
                (permissions.getValue().equals("parent")? User.permissions.parent :
                        (permissions.getValue().equals("child")? User.permissions.child :
                                (permissions.getValue().equals("guest")? User.permissions.guest : User.permissions.stranger))
        ));
>>>>>>> Stashed changes
        main.user_list.add(newUser);
        Stage stage =  (Stage)msg_label.getScene().getWindow();
        stage.close();
    }

    /**
     * This method serves to get the permissions and add them in the box
     */
    private void setPermissions() {
        permissions.getItems().add("parent");
        permissions.getItems().add("child");
        permissions.getItems().add("guest");
        permissions.getItems().add("stranger");
    }
}
