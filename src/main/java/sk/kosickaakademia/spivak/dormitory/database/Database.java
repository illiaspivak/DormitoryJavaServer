package sk.kosickaakademia.spivak.dormitory.database;

import sk.kosickaakademia.spivak.dormitory.helpclasses.Connect;
import sk.kosickaakademia.spivak.dormitory.log.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    Log log = new Log();

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(Connect.getUrl(), Connect.getUsername(), Connect.getPassword());
            log.print("Connection is successful");
            return connection;
        }catch (Exception ex) {
            log.error(ex.toString());
        }
        return null;
    }

    public void closeConnection(Connection connection)  {
        if(connection!=null) {
            try {
                connection.close();
                log.print("Connection closed");
            }catch(SQLException e){
                log.error(e.toString());
            }
        }
    }
}
