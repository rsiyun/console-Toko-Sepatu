package app;

import java.util.Scanner;

import app.Admin.Admin;
import app.User.UserView;
import entity.User;
import service.BaseAuth;
import service.Enum.RoleUsers;

public class AuthView {
    public void View() throws Exception{
        BaseAuth baseAuth = BaseAuth.getInstance();
        User user = baseAuth.getUser();
        Auth auth = new Auth();
        while (user == null) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("SELAMAT DATANG DI TOKO SEPATU KAMI");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Pilih Pilihan anda: ");
            String pilihan = scanner.nextLine();
                switch (pilihan) {
                    case "1":
                        user = auth.processLogin();
                        System.out.print("\n");
                        break;
                    case "2":
                        auth.processRegister();
                        break;
                    case "3":
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Pilihan tidak ada");
                        break;
                }
            }
            System.out.println("Login berhasil!");
            baseAuth.setUser(user);
            if (user.getRole() == RoleUsers.Admin.value || user.getRole() == RoleUsers.superAdmin.value) {
                Admin admin = new Admin();
                admin.menu();
            }
            else{
                UserView userV = new UserView();
                userV.menu();
            }
    }
}
