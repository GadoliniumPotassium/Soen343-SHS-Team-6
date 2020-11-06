package Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoadTxtInfo {
    /**
     * This method is meant to read the layout file and parse the information and return is as a string array in tokens
     * The format of this textfile should be usually lines of: room name,x,y,length,width <br>
     * where x,y are coordinates and length and width are the size of the room
     *
     * @param filename
     * @return
     */
    public String[][] ReadLayoutFile(String filename) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        ArrayList<String> lines = new ArrayList<String>();
        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[][] information = new String[lines.size()][5];
        for (int i = 0; i < information.length; i++) {
            String roomInfo = lines.get(i);
            String[] roomInfoSplit = roomInfo.split(";");
            for (int j = 0; j < information[i].length; j++) {
                information[i][j] = roomInfoSplit[j];
            }
        }
        return information;
    }

    /**
     * The text file for the module should follow this format: </br>
     * Room; module; modulename; isOn/isOpen (usually a boolean) if the smart module requires it
     *
     * @param filename
     * @return
     */
    public String[][] ReadModuleFile(String filename) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        ArrayList<String> lines = new ArrayList<String>(10);
        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        String[][] informartion = new String[lines.size()][];
        for (int i = 0; i < lines.size(); i++) {
            String[] moduleInfo = lines.get(i).split(";");
            informartion[i] = moduleInfo;
        }
        return informartion;
    }

    public static ArrayList<String> loadUserFile() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("users.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        ArrayList<String> userInfo = new ArrayList<String>(10);
        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                userInfo.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return userInfo;
    }
}

