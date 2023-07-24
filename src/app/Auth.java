package app;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

import entity.User;
import service.AllSql;

public class Auth extends AllSql{
    public User processLogin() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (getDataUser(password, username) == null) {
            System.out.println("Username dan Password Salah");
        }
        return getDataUser(password, username);
    }

   public void processRegister() throws Exception {
        System.out.println("Register Page");
        Scanner scanner = new Scanner(System.in);
            System.out.print("Username: ");
            String username = scanner.nextLine();
            if (getDataUser(username) != null) {
                System.out.println("Username sudah terisi");
                return;
            }
            System.out.print("Password: ");
            String password = scanner.nextLine();
            insertUser(username, password);
   }
}