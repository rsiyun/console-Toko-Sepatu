import java.sql.ResultSet;

public class App {
    public static void main(String[] args) throws Exception {
        Conn connection = new Conn();
        
        String sql = "SELECT * FROM users";
        ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
        while(rs.next()){
            System.out.println("ID cok: " + rs.getInt("id_user"));
            System.out.println("uwong: " + rs.getString("username"));
            System.out.println("role: " + rs.getString("role"));
        }
    }
}
