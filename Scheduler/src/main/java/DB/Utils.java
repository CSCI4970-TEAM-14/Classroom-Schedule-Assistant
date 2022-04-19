package DB;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Account;
import entities.Classroom;
import entities.Course;
import entities.Instructor;
import entities.Schedule;

public class Utils {
	private Connection connection;
	
	protected void connect() throws SQLException {
		connection = null;
		DBConnection.getDBConnection();
		connection = DBConnection.connection;
    }
	
	public String register(Account acc) throws SQLException {//throws ClassNotFoundException {
		connect();
		String result;
		String insertSql = " INSERT INTO Account (username, firstname, lastname, email, password) values (?, ?, ?, ?, ?)";//id?
		result = "Data Added!";

		try {		
			PreparedStatement statement = connection.prepareStatement(insertSql);
			//statement.setInt(1, acc.getId());
			statement.setString(1, acc.getUserName());// userName);
			statement.setString(2, acc.getFirstName());// firstName);
			statement.setString(3, acc.getLastName());// lastName);
			statement.setString(4, acc.getEmail());// email);
			statement.setString(5, acc.getPassword());// password);

			System.out.println(statement);
			statement.executeUpdate();
			//connection.close();
		} catch (SQLException e) {
			result = "Data not added!";
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Classroom> listClassRoom() throws SQLException {
        List<Classroom> list = new ArrayList<>();
        String result;
        String sql = "SELECT * FROM Classroom where id=?";
        result = "Data Selected!";
         
        connect();
        try {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println(statement);
         
        while (resultSet.next()) {
            String id = resultSet.getString("id");
             
            Classroom c = new Classroom(id);
            list.add(c);
        }
        resultSet.close();
        statement.close();
        }catch(SQLException e) {
			result = "Data not added!";
			e.printStackTrace();
        } 
        //disconnect();
         
        return list;
    }

    public List<Course> listCourse() throws SQLException {
        List<Course> list = new ArrayList<>();
        
        String result;
        String sql = "SELECT * FROM Course WHERE id = ? and title=?";
        result = "Data Selected!";
         
        connect();
        try { 
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println(statement);
         
        while (resultSet.next()) {
            String title = resultSet.getString("title");
            String id = resultSet.getString("id");
             
           // Course c = new Course(id,title);
            //list.add(c);
        }
        resultSet.close();
        statement.close();
         
        }catch(SQLException e) {
			result = "Data not added!";
			e.printStackTrace();
        } 
        //disconnect();
         
        return list;
    }

    public List<Instructor> listInstructor() throws SQLException {
        List<Instructor> list = new ArrayList<>();
        String result;
        String sql = "SELECT * FROM Instructor WHERE first=? and last = ?";
        result = "Data Selected!";
        connect();
        try {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println(statement);
         
        while (resultSet.next()) {
            String first = resultSet.getString("first");
            String last = resultSet.getString("last");
             
           // Instructor in = new Instructor(id, first, last, department);
            //list.add(in);
        }
        resultSet.close();
        statement.close();
        } catch(SQLException e) {
			result = "Data not added!";
			e.printStackTrace();
        }
        //disconnect();
        return list;
    }

    public List<Schedule> listSchedule() throws SQLException {
        List<Schedule> list = new ArrayList<>();
        String result;
        String sql = "SELECT * FROM Schedule WHERE day=?";
        result ="Data selected!";
         
        connect();
        try { 
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println(statement);
         
        while (resultSet.next()) {
            String day = resultSet.getString("day");
            String start = resultSet.getString("start");
            String end = resultSet.getString("end");
           // Schedule sh = new Schedule(day, start, end);
           // list.add(sh);
        }
        resultSet.close();
        statement.close();
        } catch(SQLException e) {
        	result = "Data not added!";
        	e.printStackTrace();
        }
        //disconnect();
        return list;
    }
 
    public boolean deleteDB(Classroom room) throws SQLException {
    	String result;
        String sql = "DELETE FROM Classroom WHERE id = ?";
        result = "Data Selected!";
         
        connect();
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, room.getId());
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        /*} catch(SQLException e) {
			result = "Data not added!";
			e.printStackTrace();
        }*/
        //disconnect();
        return rowDeleted;     
    }
     
    public boolean updateDB(Classroom room) throws SQLException {
    	String result;
        String sql = "UPDATE Classroom SET id = ?, building = ?, seat = ?, computer =?";
        sql += " WHERE id = ?";
        result = "Data Selected!";
        connect();
         
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, room.getId());
        statement.setString(2, room.getBuilding());
        statement.setString(3, room.getSeat());
        statement.setString(4,room.getComputers());
        System.out.println(statement);
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        //disconnect();
        return rowUpdated;     
    }
     
    public Account getAccount(String id) throws SQLException {
        Account acc = null;
        String result;
        String sql = "SELECT * FROM Account WHERE id = ?";
        result = "Data Selected!";
         
        connect();
        try { 
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);
         
        ResultSet resultSet = statement.executeQuery();
        System.out.println(statement);
         
        if (resultSet.next()) {
            String username = resultSet.getString("username");//?
            acc = new Account(username);
        }
        resultSet.close();
        statement.close();
        }catch(SQLException e) {
        	result = "Data not added!";
        	e.printStackTrace();
        } 
        return acc;
    }

    public boolean validate(Account acc) throws SQLException {
        boolean status = false;
        String result;
        String sql = "SELECT * FROM Account WHERE username = ? and password = ?";
        result = "Data Selected!";

        connect();

        try (
            PreparedStatement statement = connection
            .prepareStatement(sql)) {
            statement.setString(1, acc.getUserName());
            statement.setString(2, acc.getPassword());
            
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();
            status = resultSet.next();

        } catch(SQLException e) {
			result = "Data not added!";
			e.printStackTrace();
        }
//        resultSet.close();
  //      statement.close();
        return status;
    }

}
