package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Account;
import entities.Classroom;
import entities.Instructor;
import entities.Section;
import entities.Course;
import entities.Schedule;


public class BackupDB{
	String Driver = "com.mysql.jdbc.Driver";
	String URL = "jdbc:mysql://ec2-3-129-194-150.us-east-2.compute.amazonaws.com:3306/CSA?useSSL=false";
	String Name = "CSA";
	String Username = "nangatid";
	String Password = "TKey";	
	private Connection jdbcConnection;
    
	protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
            	System.out.println("Where is your MySQL JDBC Driver?");
                throw new SQLException(e);
            }
            System.out.println("MySQL JDBC Driver Registered!");
            try {
            jdbcConnection = DriverManager.getConnection(
                                        URL, Username, Password);
        }catch (Exception e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
         }
            if (jdbcConnection != null) {
                System.out.println("You made it, take control your database now!");
             } else {
                System.out.println("Failed to make connection!");
             }
        }
    }
     
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
     
    public boolean createAccount(Account acc) throws SQLException {
        String sql = "INSERT INTO Account (username, firstname, lastname, email, password) VALUES (?, ?, ?, ?, ?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        //statement.setInt(1, acc.getId());
        statement.setString(1, acc.getUserName());
        statement.setString(2, acc.getFirstName());
        statement.setString(3, acc.getLastName());
        statement.setString(4, acc.getEmail());
        statement.setString(5, acc.getPassword());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
     
    public List<Classroom> listClassRoom() throws SQLException {
        List<Classroom> list = new ArrayList<>();
         
        String sql = "SELECT * FROM Classroom where id=?";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            String id = resultSet.getString("id");
             
            Classroom c = new Classroom(id);
            list.add(c);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return list;
    }

    public List<Course> listCourse() throws SQLException {
        List<Course> list = new ArrayList<>();
         
        String sql = "SELECT * FROM Course WHERE id = ? and title=?";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            String title = resultSet.getString("title");
            String id = resultSet.getString("id");
             
           Course c = new Course(id,title);
           list.add(c);
        } 
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return list;
    }

    public List<Instructor> listInstructor() throws SQLException {
        List<Instructor> list = new ArrayList<>();
         
        String sql = "SELECT * FROM Instructor WHERE first=? and last = ?";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            String first = resultSet.getString("first");
            String last = resultSet.getString("last");
             
           Instructor in = new Instructor(first, last);
           list.add(in);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return list;
    }

    public List<Schedule> listSchedule() throws SQLException {
        List<Schedule> list = new ArrayList<>();
         
        String sql = "SELECT * FROM Schedule WHERE day=?";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            String day = resultSet.getString("day");
            String start = resultSet.getString("start");
            String end = resultSet.getString("end");

             
           Schedule sh = new Schedule(day, start, end);
           list.add(sh);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return list;
    }

    public boolean deleteDB(Classroom room) throws SQLException {
        String sql = "DELETE FROM Classroom WHERE id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, room.getId());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
     
    public boolean updateDB(Classroom room) throws SQLException {
        String sql = "UPDATE Classroom SET id = ?, building = ?, seat = ?, computer =?";
        sql += " WHERE id = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, room.getId());
        statement.setString(2, room.getBuilding());
        statement.setString(3, room.getSeat());
        statement.setString(4,room.getComputers());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
    
    public boolean adjustCap(Classroom room) throws SQLException {
        String sql = "UPDATE Classroom SET id = ?, building = ?, seat = ?";
        sql += " WHERE id = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, room.getId());
        statement.setString(2, room.getBuilding());
        statement.setString(3, room.getSeat());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
     
    public Account getAccount(String username) throws SQLException {
        Account acc = null;
        String sql = "SELECT * FROM Account WHERE username = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, username);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String user = resultSet.getString("username");//?
            acc = new Account(user);
        }
         
        resultSet.close();
        statement.close();
         
        return acc;
    }

    public boolean validate(Account acc) throws SQLException {
    	 connect();
        boolean status = false;
        String sql = "SELECT * FROM Account WHERE username = ? and password = ?";
        
            PreparedStatement statement = jdbcConnection
            .prepareStatement(sql);
            statement.setString(1, acc.getUserName());
            statement.setString(2, acc.getPassword());

            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();
            status = resultSet.next();

            resultSet.close();
            statement.close();
            return status;
    }
    
	public Account checkLogin(String username, String password) throws SQLException, ClassNotFoundException {
		connect();
		String sql = "SELECT * FROM Account WHERE username = ? and password = ?";
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, username);
		statement.setString(2, password);

		ResultSet result = statement.executeQuery();

		Account acc = null;

		if (result.next()) {
			acc = new Account();
			acc.setFirstName(result.getString("firstname"));
			acc.setUserName(username);
			//acc.setEmail(password);
		}

		jdbcConnection.close();

		return acc;
	}

    public boolean saveDB(Classroom cl) throws SQLException {
		 String sql = "INSERT INTO Classroom (id, type, building, seat,computers) VALUES (?, ?, ?, ?, ?)";
	        connect();
	         
	        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
	        statement.setString(1, cl.getId());
	        statement.setString(2, cl.getType());
	        statement.setString(3, cl.getBuilding());
	        statement.setString(4, cl.getSeat());
	        statement.setString(5, cl.getComputers());
	        
	        boolean rowInserted = statement.executeUpdate() > 0;
	        statement.close();
	        disconnect();
	        return rowInserted;
	}

	public boolean saveSchedule(Schedule sh) throws SQLException {
		String sql = "INSERT INTO Schedule (day,start,end) VALUES (?, ?, ?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, sh.getDay());
        statement.setString(2, sh.getStartTime());
        statement.setString(3, sh.getEndTime());
        
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
	}

	public boolean saveSection(Section s)throws SQLException {
		String sql = "INSERT INTO Section (id,courseId,type,method,enroll,room,instructorId,meeting,term) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, s.getId());
        statement.setString(2, s.getCourseId());
        statement.setString(3, s.getType());
        statement.setString(4, s.getMethod());
        statement.setString(5, s.getEnroll());
        statement.setString(6, s.getRoom());
        statement.setString(7, s.getInstructor());
        statement.setString(8, s.getMeet());
        statement.setString(9, s.getTerm());
        
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
	}
}