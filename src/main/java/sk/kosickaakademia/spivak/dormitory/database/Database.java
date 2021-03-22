package sk.kosickaakademia.spivak.dormitory.database;

import sk.kosickaakademia.spivak.dormitory.entity.Resident;
import sk.kosickaakademia.spivak.dormitory.helpclasses.Connect;
import sk.kosickaakademia.spivak.dormitory.log.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Database {
    Log log = new Log();

    /**
     * Connecting to the database
     * @return
     */
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(Connect.getUrl(), Connect.getUsername(), Connect.getPassword());
            log.print("Connection is successful");
            return connection;
        }catch (Exception ex) {
            log.error("No connection to the database");
            log.error(ex.toString());
        }
        return null;
    }

    /**
     * Close the database connection
     * @param connection
     */
    public void closeConnection(Connection connection)  {
        if(connection!=null) {
            try {
                connection.close();
                log.print("Connection closed");
            }catch(SQLException e){
                log.error("Failed to close database connection");
                log.error(e.toString());
            }
        }
    }

    /**
     * Creating a list of dormitory residents
     * @return List<User>
     */
    public List<Resident> getAllResidents(){
        log.info("Creating a list of dormitory residents");
        List<Resident> list = new ArrayList<>();
        String query = "SELECT * FROM residents";
        try {
            Connection connection = getConnection();
            if(connection ==null){
                log.error("No connection to the database");
                return null;
            }
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                Date dob = rs.getDate("dob");
                String country = rs.getString("country");
                String city = rs.getString("city");
                String room = rs.getString("room");
                Resident user=new Resident(id, fname, lname, dob, country, city, room);
                list.add(user);
            }
            closeConnection(connection);
            log.print("Created a list of residents of the dormitory");
            return list;
        }catch(Exception ex){
            log.error("Failed to create a list of dormitory residents");
            log.error(ex.toString());
        }
        return null;
    }

    /**
     * Adding a new resident to the database
     * @param resident
     * @return true/false
     */
    public boolean insertNewResident(Resident resident){
        if(resident==null){
            log.error("Missing input data");
            return false;
        }
        String query = "INSERT INTO residents (fname, lname, dob, country, city, room) VALUES (?, ?, ?, ?, ?, ?)";
            try{
                Connection connection = getConnection();
                if(connection ==null){
                    log.error("No connection to the database");
                    return false;
                }
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1,resident.getFname());
                ps.setString(2,resident.getLname());
                ps.setDate(3, (java.sql.Date) resident.getDob());
                ps.setString(4,resident.getCountry());
                ps.setString(5,resident.getCity());
                ps.setString(6,resident.getRoom());
                int result = ps.executeUpdate();
                closeConnection(connection);
                if(result==1) {
                    log.print("New resident has been added to the database");
                    return true;
                }
                log.error("Couldn't add new resident to the database");
                return false;
            }catch(SQLException ex){
                log.error("Couldn't add new resident to the database");
                log.error(ex.toString());
            }
        return false;
    }
}
