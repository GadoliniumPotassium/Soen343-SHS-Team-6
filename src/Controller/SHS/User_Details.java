package Controller.SHS;

import Model.User;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.App;

public class User_Details {
    public TextField user_textField;
    public PasswordField pass_textField;
    public ChoiceBox permissions;
    public Label msg_label;

    private User user;

    public void update(ActionEvent actionEvent) {
        if(user_textField.getText().isEmpty() ||
            pass_textField.getText().isEmpty()){
            msg_label.setText("Please Enter the value");
            return;
        }
        String uname = user_textField.getText().replaceAll("\\s+","");
        if(uname.equals(user_textField.getText())) {
            App.log(this.user.getUsername() + " user username is changed to " + uname);
            this.user.setUsername(uname);
        }
        else
            msg_label.setText("Please remove the white spaces from username");
        this.user.setPassword(pass_textField.getText());
        App.log(this.user.getUsername()+" password is changed");
        this.user.setUserPermission(
                (permissions.getValue().equals("full")? User.permissions.full :
                        (permissions.getValue().equals("partial")? User.permissions.partial: User.permissions.none))
        );
        App.log(this.user.getUsername()+" permissions is changed to "+user.getUserPermission().name());

        Stage stage =  (Stage)msg_label.getScene().getWindow();
        stage.close();
    }

    public void setUser(User _user) {
        this.user = _user;
        setPermissions();
        setUser_textField(_user.getUsername());
        setPass_textField(_user.getPassword());
    }

    private void setPermissions() {
        //System.out.println(user.getUserPermission().name());
        permissions.getSelectionModel().select(user.getUserPermission().name());

        permissions.getItems().add("full");
        permissions.getItems().add("partial");
        permissions.getItems().add("none");
    }

    private void setUser_textField(String _username) {
        this.user_textField.setText(_username);
    }
    private void setPass_textField(String _pass){
        this.pass_textField.setText(_pass);
        this.pass_textField.setVisible(true);
    }
}
