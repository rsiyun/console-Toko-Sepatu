package app;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

import entity.User;
import service.AllSql;

public class Auth extends AllSql{

    public User getDataUser(String password, String username) throws Exception{
        
        String sql = "SELECT users.id_user, users.username, users.role, users.active FROM users WHERE users.password = '"+password+"' AND users.username = '"+username+"'" ;
        ResultSet rs = this.sqlquerry(sql);
        User user = null;
        while(rs.next()){
           user = new User(rs.getInt("id_user"), rs.getString("username"), rs.getInt("role"), rs.getInt("active"));
        }
        
        return user;
        
    }
    public User getDataUser(String username) throws Exception{
        
        String sql = "SELECT users.id_user, users.username, users.role, users.active FROM users WHERE users.username = '"+username+"'" ;
        ResultSet rs = this.sqlquerry(sql);
        User user = null;
        while(rs.next()){
           user = new User(rs.getInt("id_user"), rs.getString("username"), rs.getInt("role"), rs.getInt("active"));
        }
        return user;
        
    }
    public void insertUser(String username, String password) throws Exception{
        String sql = "INSERT INTO users (username, password, role, active) VALUES ('"+username+"','"+password+"', 0, 1);";
        this.sqlexupdate(sql);
    }
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