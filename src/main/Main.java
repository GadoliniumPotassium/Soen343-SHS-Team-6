package main;

import Controller.DashboardController;
import Model.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static ArrayList<User> user_list = new ArrayList<>();
    public static ArrayList<House.Room> rooms_list = new ArrayList<>();
    public static ArrayList<House.OutSide> outSides = new ArrayList<>();

    public static ArrayList<SmartWindow> doors_outside = new ArrayList<>();
    public static ArrayList<SmartLight> lights_outside = new ArrayList<>();

    public static ArrayList<SmartWindow> doors_inside = new ArrayList<>();
    public static ArrayList<SmartWindow> windows_inside = new ArrayList<>();
    public static ArrayList<SmartLight> lights_inside = new ArrayList<>();

    public static User active_user;

    public static boolean layoutLoaded;
    public static boolean house_loaded = false;

    public static boolean isSimulationRunning;
    public static boolean away_mode;

    public static Settings settings;

    /**
     * this method check if user exists
     */
    public static boolean user_exists(String _username, String _password){
        for (int i = 0; i < user_list.size(); i++) {
            if (user_list.get(i).getUsername().equals(_username) && user_list.get(i).getPassword().equals(_password)) {
                active_user = user_list.get(i);
                return true;
            }
        }
        return false;
    }
    /**
     * this method load users
     */
    public static void load_users(){
        String path = "src/resource/users.json";
        File json = new File(path);

        if(json.exists()) {
            JsonReader reader = null;
            try {
                reader = new JsonReader(new FileReader(json));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            User[] users = new Gson().fromJson(reader,User[].class);
            Collections.addAll(user_list,users);
        }
    }
    public void writeSettings(){
        JSONArray as = new JSONArray();
        try{
            JSONObject s = new JSONObject();
            s.put("date",settings.getDate());
            s.put("time",settings.getTime());
            s.put("temperature",settings.getTemperature());
            as.put(s);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try(FileWriter fileWriter = new FileWriter("src/resource/settings.json")){
            fileWriter.write(as.toString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void readTimeAndDate(){
        String path = "src/resource/settings.json";
        File json = new File(path);

        if(json.exists()) {
            JsonReader reader = null;
            try {
                reader = new JsonReader(new FileReader(json));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Settings[] set = new Gson().fromJson(reader,Settings[].class);
            settings = set[0];
        }
      /*  LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        DateTimeFormatter d = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        DateTimeFormatter t = DateTimeFormatter.ofPattern("HH:mm:a");
        settings = new Settings(d.format(date),t.format(time),18);*/
    }
    public static int user_location_room(String _loc){
        for(int i = 0; i< rooms_list.size();i++){
            if(rooms_list.get(i).getName().equals(_loc))
                return i;
        }
        return -1;
    }

    public void loadData(){
        loadHouseLayout();
        load_users();
        readTimeAndDate();
        ReadHomeOutSide();
        doors_and_windows_and_lights();
    }
    public static void ReadHomeOutSide(){
        String path = "src/resource/HomeOutSide.json";

        File json = new File(path);
        if(json.exists()){
            house_loaded = true;
            JsonReader reader = null;
            try{
                reader = new JsonReader(new FileReader(json));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            House.OutSide[] houses = new Gson().fromJson(reader,House.OutSide[].class);
            Collections.addAll(outSides,houses);
        }else house_loaded = false;
    }
    public void doors_and_windows_and_lights(){
        for (House.OutSide outSide : outSides) {
            for(int i = 0; i<outSide.getDoors(); i++){
                SmartWindow door = new SmartWindow("Door "+(i+1),outSide.getName(),false,false);
                door.setLocked(false);
                doors_outside.add(door);
            }
            for(int j = 0; j<outSide.getLights();j++){
                SmartLight light = new SmartLight("Light "+(j+1),outSide.getName(),false,1,false);
                lights_outside.add(light);
            }
        }
        rooms_list.forEach(room ->{
            for(int i = 0; i<room.getDoors();i++){
                SmartWindow door = new SmartWindow("Door "+(i+1),room.getName(),false,false);
                door.setLocked(false);
                doors_inside.add(door);
            }
            for(int i = 0; i<room.getWindows(); i++){
                SmartWindow window = new SmartWindow("Window "+(i+1),room.getName(), false,false);
                window.setLocked(false);
                windows_inside.add(window);
            }
            for(int i =0; i<room.getLights(); i++){
                SmartLight light = new SmartLight("Light "+(i+1),room.getName(),false,1,false);
                lights_inside.add(light);
            }
        });
    }

    public static void loadHouseLayout(){
        String path = "src/resource/HouseLayout.json";

        File json = new File(path);
        if(json.exists()){
            house_loaded = true;
            JsonReader reader = null;
            try{
                reader = new JsonReader(new FileReader(json));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            House.Room[] houses = new Gson().fromJson(reader,House.Room[].class);
            Collections.addAll(rooms_list,houses);
        }else house_loaded = false;
    }

    public static void setIsSimulationRunning(boolean isSimulationRunning) {
        Main.isSimulationRunning = isSimulationRunning;
    }

    public static boolean isIsSimulationRunning() {
        return isSimulationRunning;
    }

    /**
     * This method will count users with same location
     */
    public static int users_in_same_room(String _loc){
        AtomicInteger count = new AtomicInteger();
        user_list.forEach(u ->{
            if(u.getLocation() == null) return;
            if(u.getLocation().equals(_loc)) count.getAndIncrement();
        });
        return count.intValue();
    }
    public static int users_outside(String _loc){
        AtomicInteger count = new AtomicInteger();
        user_list.forEach(u ->{
            if(u.getLocation() == null) return;
            if(u.getLocation().equals(_loc)) count.getAndIncrement();
        });
        return count.intValue();
    }

//    public static void main(String args[]){
//        new Main().write();
//    }

    public void saveData(){
        writeHouseLayout();
        writeUsersData();
        writeSettings();
    }

    public void writeUsersData(){
        JSONArray student = new JSONArray();
        user_list.forEach(user->{
            try {
                JSONObject u = new JSONObject();
                u.put("username", user.getUsername());
                u.put("password",user.getPassword());
                u.put("location",user.getLocation());
                u.put("isLoggedIn",user.isIsloggedIn());
                u.put("userPermission",user.getUserPermission().name());

                student.put(u);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

        try(FileWriter fileWriter = new FileWriter("src/resource/users.json")){
            fileWriter.write(student.toString(1));
            fileWriter.flush();
            System.out.println("Student File Upgraded");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeHouseLayout(){
        JSONArray rooms = new JSONArray();
        rooms_list.forEach(room->{
            try{
                JSONObject r = new JSONObject();
                r.put("name",room.getName());
                r.put("windows",room.getWindows());
                r.put("doors",room.getDoors());
                r.put("lights",room.getLights());
                r.put("temperature",room.getTemperature());
                rooms.put(r);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
        try(FileWriter fileWriter = new FileWriter("src/resource/HouseLayout.json")){
            fileWriter.write(rooms.toString(1));
            fileWriter.flush();
            System.out.println("room file upgraded");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean room_exists(String _name){
        for(House.Room room : rooms_list){
            if(room.getName().equals(_name))
                return true;
        }
        return false;
    }

    void write(){
         try {
            JSONObject roomDetails = new JSONObject();
            roomDetails.put("name", "Bed Room");
            roomDetails.put("windows", 2);
            roomDetails.put("doors",2);
            roomDetails.put("lights",4);
            roomDetails.put("temperature",33.5);

            JSONObject roomDetails2 = new JSONObject();
            roomDetails2.put("name","kitchen");
            roomDetails2.put("windows",1);
            roomDetails2.put("doors",1);
            roomDetails2.put("lights",2);
            roomDetails2.put("temperature",18.4);


            JSONObject roomDetails3 = new JSONObject();
            roomDetails3.put("name","Garage");
            roomDetails3.put("windows",2);
            roomDetails3.put("doors",2);
            roomDetails3.put("lights",3);
            roomDetails3.put("temperature",21.0);


            JSONObject roomDetail4 = new JSONObject();
            roomDetail4.put("name","Master Room");
            roomDetail4.put("windows",3);
            roomDetail4.put("door",1);
            roomDetail4.put("lights",5);
            roomDetail4.put("temperature",17.5);

            JSONObject roomDetail5 = new JSONObject();
            roomDetail5.put("name","Amneet Room");
            roomDetail5.put("windows",3);
            roomDetail5.put("doors",1);
            roomDetail5.put("lights",6);
            roomDetail5.put("temperature",20.9);


            JSONArray houseLayout = new JSONArray();
            houseLayout.put(roomDetails);
            houseLayout.put(roomDetails2);
            houseLayout.put(roomDetails3);
            houseLayout.put(roomDetail4);
            houseLayout.put(roomDetail5);

            try(FileWriter fileWriter = new FileWriter("src/resource/HouseLayout.json")){
                fileWriter.write(houseLayout.toString(1));
                fileWriter.flush();
                System.out.println("json file written");
            }

        }catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }
}
