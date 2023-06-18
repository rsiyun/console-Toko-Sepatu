package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {
    private Connection conn;
    final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    final String DB_URL = "jdbc:mysql://localhost:3306/dbtokosepatu";
    final String USER = "root";
    final String PASS = "root";

    public Conn() throws SQLException {
        conn = DriverManager.getConnection(this.DB_URL, this.USER, this.PASS);
    }

    public Connection getConnection() {
        return conn;
    }

    public void close() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
}