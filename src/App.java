import java.util.Scanner;

import app.Auth;
import app.Admin.Admin;
import entity.User;
import app.User.UserView;
import service.BaseAuth;
import service.Enum.RoleUsers;

public class App {
    User user = null;
    
    public static void main(String[] args) throws Exception {
        App app = new App();
        app.run();
        BaseAuth baseAuth = BaseAuth.getInstance();
        app.user.setPassword("");
        baseAuth.setUser(app.user);
        if (app.user.getRole() == RoleUsers.Admin.value || app.user.getRole() == RoleUsers.superAdmin.value) {
            Admin admin = new Admin();
            admin.menu();
        }
        else{
            UserView user = new UserView();
            user.menu();
        }

    }

    public void run() throws Exception{
        Auth auth = new Auth();
        Scanner scanner = new Scanner(System.in);
        System.out.println("SELAMAT DATANG DI TOKO SEPATU KAMI");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.print("Pilih Pilihan anda: ");
        String pilihan = scanner.nextLine();
        if (pilihan.equals("1")) {
            user = auth.processLogin();
            System.out.print("\n");
        }else{
            auth.processRegister();
        }
        if (user == null) {
            run();
        }
        System.out.println("Login berhasil!");
    }

}
