package Model;

public class Settings {
    private String date;
    private String time;
    private float temperature;
    private int alertTiming;

    // MM/DD/YYYY
    // HH:mm
    // 00.0


    public Settings(String _date,String _time,float _temp){
        setDate(_date);
        setTime(_time);
        setTemperature(_temp);
    }

    public void setAlertTiming(int alertTiming) {
        this.alertTiming = alertTiming;
    }

    public int getAlertTiming() {
        return alertTiming;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public float getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", temperature=" + temperature +
                '}';
    }
}
