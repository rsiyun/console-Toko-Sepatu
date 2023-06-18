package app;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

import connection.Conn;
import entity.User;

public class Auth {
    private ArrayList<User> userList = new ArrayList<User>();

    public ArrayList<User> getUserList() {
        return userList;
    }
    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }
    public void getDataUser() throws Exception{
        Conn connection = new Conn();
        String sql = "SELECT * FROM users";
        ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
        ArrayList<User> list = new ArrayList<User>();
        while(rs.next()){
            User user = new User(rs.getInt("id_user"), rs.getString("username"), rs.getString("password"), rs.getInt("role"), rs.getInt("active"));
            list.add(user);
        }
        setUserList(list);
        
    }
    public void insertUser(String username, String password) throws Exception{
        Conn connection = new Conn();
        String sql = "INSERT INTO users (username, password, role, active) VALUES ('"+username+"','"+password+"', 0, 1);";
        connection.getConnection().createStatement().executeUpdate(sql);
    }
    public String processLogin() throws Exception {
        getDataUser();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();
        scanner.close();
        return checkLogin(username, password, getUserList());
    }
   public String checkLogin(String username, String password, ArrayList<User> userList){
        for (int i = 0; i < userList.size(); i++) {
            if (username.equals(userList.get(i).getUsername()) && password.equals(userList.get(i).getPassword())) {
                System.out.println("Login berhasil!");
                return username;
            }
        }
        return "";
   }

   public void processRegister() throws Exception {
        getDataUser();
        System.out.println("Register Page");
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Username: ");
            String username = scanner.nextLine();
            for (int i = 0; i < getUserList().size(); i++) {
                if (username.equals(userList.get(i).getUsername())) {
                    System.out.println("Username sudah ada");
                    return;
                }
            }
            System.out.print("Password: ");
            String password = scanner.nextLine();
            scanner.close();
            insertUser(username, password);
        }
   }
}