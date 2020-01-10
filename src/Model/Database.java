package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
       private final String Path = "DB/SinhVien.db";
       private Connection connection;
       final String url = "jdbc:sqlite:" + Path;
       public Database(){

           try {
               connection = DriverManager.getConnection(url);
               createSinhVienTable(connection);
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
    public static void createSinhVienTable(Connection connection) throws SQLException {

        String SQL_CREATE_SINHVIEN_TABLE = "CREATE TABLE IF NOT EXISTS SinhVienTable (\n"
                + "    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n"
                + "    FullName TEXT NOT NULL,\n"
                + "    Mark FLOAT,\n"
                + "    Date STRING\n"
                + ");";
        Statement statement = connection.createStatement();
        statement.execute(SQL_CREATE_SINHVIEN_TABLE);
    }
    public Connection getConnection() {
        return connection;
    }
    public  void CloseConnect(){
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
