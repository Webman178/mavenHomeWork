import java.sql.*;
import java.util.*;
public class MyConnection {
    public static Connection getConnection() throws SQLException{
        final String user = "postgres";
        final String password = "19891989As";
        final String url = "jdbc:postgresql://localhost:5432/skypro";
        return DriverManager.getConnection(url, user, password);
    }
}
