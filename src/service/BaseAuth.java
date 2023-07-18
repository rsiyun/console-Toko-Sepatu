package service;
import entity.User;

public class BaseAuth {
    private static BaseAuth instance;
    private User user;

    private BaseAuth() {}

    public static BaseAuth getInstance() {
        if (instance == null) {
            instance = new BaseAuth();
        }
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

