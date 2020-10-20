package Model;

import java.io.*;
import java.util.ArrayList;

public class LoadHomeLayout {
    /**
     * This method is meant to read the layout file and parse the information and return is as a string array in tokens
     * @param filename
     * @return
     */
    public String[][] readFile(String filename) {
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
        String information[][] = new String[lines.size()][5];
        for (int i = 0; i < information.length; i++) {
            String roomInfo = lines.get(i);
            String roomInfoSplit[] = roomInfo.split(";");
            for (int j = 0; j < information[i].length; j++) {
                information[i][j] = roomInfoSplit[j];
            }
        }
        return information;
    }
}
