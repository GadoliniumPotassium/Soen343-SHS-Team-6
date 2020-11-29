package Model;

public class Summer {
    private String name;
    private double temperature;

    public Summer(String _name, double _temp){
        setName(_name);
        setTemperature(_temp);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
