import java.sql.ResultSet;
import java.util.ArrayList;

import classPackage.User;

public class App {
    public static void main(String[] args) throws Exception {
        Conn connection = new Conn();
        String sql = "SELECT * FROM users";
        ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
        ArrayList<User> userList = new ArrayList<User>();
        while(rs.next()){
            User user = new User(rs.getInt("id_user"), rs.getString("username"), rs.getString("password"), rs.getInt("role"), rs.getInt("active"));
            userList.add(user);
        }
        for (int i = 0; i < userList.size(); i++) {
            System.out.print(userList.get(i).getId_user()+ "---" +userList.get(i).getUsername() + "---" +userList.get(i).getPassword());
        }
    }
}
