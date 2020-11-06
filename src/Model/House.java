package Model;

public class House {

    public Room room = new Room();
    public OutSide outSide = new OutSide();

    public class OutSide{
        private String name;
        private int doors;
        private int lights;

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
            return "OutSide{" +
                    "name='" + name + '\'' +
                    ", doors=" + doors +
                    ", lights=" + lights +
                    '}';
        }
    }
    public class Room {
        private String name;
        private int doors;
        private int windows;
        private int lights;
        private float temperature;

        public Room(){
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDoors(int doors) {
            this.doors = doors;
        }

        public void setWindows(int windows) {
            this.windows = windows;
        }

        public void setLights(int lights) {
            this.lights = lights;
        }

        public void setTemperature(float temperature) {
            this.temperature = temperature;
        }

        public String getName() {
            return name;
        }

        public int getDoors() {
            return doors;
        }

        public int getWindows() {
            return windows;
        }

        public int getLights() {
            return lights;
        }

        public float getTemperature() {
            return temperature;
        }

        @Override
        public String toString() {
            return "House{" +
                    "name='" + name + '\'' +
                    ", doors=" + doors +
                    ", windows=" + windows +
                    ", lights=" + lights +
                    ", temperature=" + temperature +
                    '}';
        }
    }
}
