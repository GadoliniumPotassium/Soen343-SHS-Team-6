package Controller.SHC;

import Model.House;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import main.Main;

import java.io.IOException;

public class User_In_Room {
    public Label userName;
    private User user;

    private ListView listView;
    private House room;

    public void removeUserFromRoom(ActionEvent actionEvent) {
        this.user.setLocation("outside");
        listView.getItems().clear();

        for(User user : Main.user_list){
            if(user.getLocation() != null && user.getLocation().equals(this.room.room.getName())){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/user_in_room.fxml"));
                HBox h = null;
                try {
                    h = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                User_In_Room uIn = loader.getController();
                uIn.setUserName(user);
                uIn.setListView(listView);
                listView.getItems().add(h);
            }
        }
       // System.out.println(user.defaultLocation+"\nUSERS\n"+ Main.user_list.toString());
    }

    public void setRoom(House room) {
        this.room = room;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }

    public void setUserName(User _user) {
        this.user = _user;
        this.userName.setText(_user.getUsername());
    }
}
