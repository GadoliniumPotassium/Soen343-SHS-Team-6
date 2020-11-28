package Model;

import main.Main;

import java.util.ArrayList;

/**
 * Smart Zone Class for SHH Module
 */
public class SmartZone {

    /**
     * Period Class for Zone
     */
    public class Period{
        private double temperature;

        private int f_hour;
        private int f_min;

        private int t_hour;
        private int t_min;

        public double getTemperature() {
            return temperature;
        }

        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }

        public int getF_hour() {
            return f_hour;
        }

        public void setF_hour(int f_hour) {
            this.f_hour = f_hour;
        }

        public int getF_min() {
            return f_min;
        }

        public void setF_min(int f_min) {
            this.f_min = f_min;
        }

        public int getT_hour() {
            return t_hour;
        }

        public void setT_hour(int t_hour) {
            this.t_hour = t_hour;
        }

        public int getT_min() {
            return t_min;
        }

        public void setT_min(int t_min) {
            this.t_min = t_min;
        }
    }

    private static int id = 65;
    private String name;

    public ArrayList<Room> rooms = new ArrayList<>();
    public ArrayList<Period> periods = new ArrayList<>();

    public SmartZone(){
        this.name = "Zone "+(char)id++;
        setDefaultPeriod();
    }

    public void setDefaultPeriod(){
        Period period = new Period();
        period.setF_hour(0);
        period.setF_min(0);
        period.setT_hour(23);
        period.setT_min(59);
        period.setTemperature(Main.getInstance().settings.getTemperature());

        periods.add(period);
    }

    public void addPeriod(){
        Period period = new Period();
        period.setF_hour(0);
        period.setF_min(0);
        period.setT_hour(0);
        period.setT_min(0);
        period.setTemperature(0);

        periods.add(period);
    }

    public String getName() {
        return name;
    }
}