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

    /**
     * returns users username
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * set the username
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * return true if user is logged in
     * @return
     */
    public boolean isIsloggedIn() {
        return isloggedIn;
    }

    /**
     * setter for if user is logged in
     * @param isloggedIn
     */
    public void setIsloggedIn(boolean isloggedIn) {
        this.isloggedIn = isloggedIn;
    }

    /**
     * returns user password
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * set or reset user password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * get the users location
     * @return
     */
    public String getLocation() {
        return location;
    }

    /**
     * set the users location
     * @param location
     */
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
