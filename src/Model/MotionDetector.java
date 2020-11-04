package Model;

public class MotionDetector {
    // user name who came inside room

    // okay here i have done mistake using stack when multiple user can
    // enter the room and what  2 user enter the room and 1st one left first so this will be bad

    private Stack<User> userStack;

    public MotionDetector(){
        userStack = new Stack<>();
    }

    public void user_detect(User user){
        userStack.push(user);
    }

    public void user_left(User _user){

    }
}
