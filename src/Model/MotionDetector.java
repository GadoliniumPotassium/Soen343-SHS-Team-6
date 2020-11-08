package Model;

public class MotionDetector {

    private int count;

    private Room room;

    public MotionDetector(Room _room){
        this.room = _room;
        count = 0;
    }

    public String room_name(){
        return room.getName();
    }

    public void count(){
        count++;
    }

    public void deduct(){
        count--;
    }

    public boolean isSomeoneThere(){
        return count > 0;
    }
}
