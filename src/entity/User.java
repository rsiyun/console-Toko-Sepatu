package entity;

public class User {
    private int id_user;
    private String username;
    private String password;
    private int role;
    private int active;
    public User(int id_user, String username, String password, int role, int active) {
        this.id_user = id_user;
        this.username = username;
        this.password = password;
        this.role = role;
        this.active = active;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getRole() {
        return role;
    }
    public void setRole(int role) {
        this.role = role;
    }
    public int getActive() {
        return active;
    }
    public void setActive(int active) {
        this.active = active;
    }
    public int getId_user() {
        return id_user;
    }
    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}
