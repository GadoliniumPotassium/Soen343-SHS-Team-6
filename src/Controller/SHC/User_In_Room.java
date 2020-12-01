package Controller.SHC;

import Model.Room;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import main.Main;

import java.io.IOException;
/**
 * class to provide info about user in room
 */
public class User_In_Room {
    public Label userName;
    private User user;

    private ListView listView;
    private Room room;

    private Main main = Main.getInstance();

    /**
     * This method removes a user from a room
     * @param actionEvent
     */
    public void removeUserFromRoom(ActionEvent actionEvent) {
        this.user.setLocation("outside");
        listView.getItems().clear();

        for(User user : main.user_list){
            if(user.getLocation() != null && user.getLocation().equals(this.room.getName())){
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

    /**
     * This method sets a room
     * @param room
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * This method gives a list view for the items in the room
     * @param listView
     */
    public void setListView(ListView listView) {
        this.listView = listView;
    }

    /**
     * This method sets the user name?
     * @param _user
     */
    public void setUserName(User _user) {
        this.user = _user;
        this.userName.setText(_user.getUsername());
    }
}
