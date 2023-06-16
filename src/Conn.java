import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {
    private Connection conn;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/dbecommerce";
    static final String USER = "root";
    static final String PASS = "root";

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