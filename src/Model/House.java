package Model;

public class House {

    private String name;
    private int doors;
    private int lights;

    public House(){

    }


    public House(String _name, int _doors, int _lights){
        setName(_name);
        setDoors(_doors);
        setLights(_lights);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public void setLights(int lights) {
        this.lights = lights;
    }

    public String getName() {
        return name;
    }

    public int getDoors() {
        return doors;
    }

    public int getLights() {
        return lights;
    }

    @Override
    public String toString() {
        return "House{" +
                "name='" + name + '\'' +
                ", doors=" + doors +
                ", lights=" + lights +
                '}';
    }
}
