package chess.DAO;


import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {
    private String user = "postgres";
    private String password = "postgres";
    private String url = "jdbc:postgresql://localhost/chess";
    private String driver = "org.postgresql.Driver";

    public Connection getConnection()
    {
        try{
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
