import app.Admin;
import app.Auth;

public class App {
    public static void main(String[] args) throws Exception {
        // Auth login = new Auth();
        // login.processRegister();
        Admin admin = new Admin();
        admin.menu();
    }
}
