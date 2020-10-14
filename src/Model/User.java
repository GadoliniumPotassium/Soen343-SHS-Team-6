package Model;

public class User {
    private String username;
    private String password;
    private boolean isloggedIn;

    public User(String username,String password) {
        this.username = username;
        this.password=password;
        this.isloggedIn = false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isIsloggedIn() {
        return isloggedIn;
    }

    public void setIsloggedIn(boolean isloggedIn) {
        this.isloggedIn = isloggedIn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", isloggedIn=" + isloggedIn +
                '}';
    }
}
