package Model;

public class User {
    private String username;
    private String password;
    private String location;
    private boolean isloggedIn;

    public User(String username,String password,String location) {
        this.username = username;
        this.password=password;
        this.location=location;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", location='" + location + '\'' +
                ", isloggedIn=" + isloggedIn +
                '}';
    }
}
