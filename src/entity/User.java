package entity;

public class User {
    private int idUser;
    private String username;
    private int role;
    private int active;
    public User(int idUser, String username, int role, int active) {
        this.idUser = idUser;
        this.username = username;
        this.role = role;
        this.active = active;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
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
    public int getIdUser() {
        return idUser;
    }
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
