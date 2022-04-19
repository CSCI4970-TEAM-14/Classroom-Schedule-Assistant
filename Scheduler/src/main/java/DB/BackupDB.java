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
        String sql = "UPDATE Classroom SET id = ?, type = ?, seat = ?, computer =?";
        sql += " WHERE id = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, room.getId());
        statement.setString(2, room.getType());
        statement.setString(3, room.getSeat());
        statement.setString(4,room.getComputers());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
    
    public boolean adjustCap(Classroom room) throws SQLException {
        String sql = "UPDATE Classroom SET id = ?, seat = ?";
        sql += " WHERE id = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, room.getId());
        statement.setString(2, room.getSeat());
         
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
    
	public Account checkLogin(int id, String email) throws SQLException, ClassNotFoundException {
		connect();
		String sql = "SELECT * FROM Account WHERE id = ? and email = ?"; //username
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		
			
		statement.setInt(1, id);
		statement.setString(2, email);
		
		ResultSet result = statement.executeQuery();

		Account acc = null;

		if (result.next()) {
			acc = new Account();
			acc.setId(result.getInt("id"));
			acc.setUserName(result.getString(email));
			//acc.setEmail(password);
		}

		jdbcConnection.close();

		return acc;
	}

    public boolean saveDB(Classroom cl) throws SQLException {
		 String sql = "INSERT INTO Classroom (id, type, seat,computers) VALUES (?, ?, ?, ?)";
	        connect();
	         
	        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
	        statement.setString(1, cl.getId());
	        statement.setString(2, cl.getType());
	        statement.setString(3, cl.getSeat());
	        statement.setString(4, cl.getComputers());
	        
	        boolean rowInserted = statement.executeUpdate() > 0;
	        statement.close();
	        disconnect();
	        return rowInserted;
	}

	public boolean saveSchedule(Schedule sh) throws SQLException {
		String sql = "INSERT INTO Schedule (room, courseId, section, instructor,day,start,end) VALUES (?, ?, ?,?,?,?,?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, Integer.parseInt(sh.getRoom()));
        statement.setString(2,sh.getCourse());
        statement.setInt(3,Integer.parseInt(sh.getSection()));
        statement.setString(4, sh.getInstructor());
        statement.setString(5, sh.getDay());
        statement.setInt(6, Integer.parseInt(sh.getStartTime()));
        statement.setInt(7,Integer.parseInt(sh.getEndTime()));// active
        
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
	}
	
	/*//************************************************************DELETE/REMOVE*********************************************************************\\*/

    public boolean deleteClass(Classroom room) throws SQLException {
        String sql = "DELETE FROM Classroom WHERE id = ?";
        connect();
         
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setString(1, room.getId());
            boolean rowDeleted = statement.executeUpdate() > 0;
        
        statement.close();
        disconnect();
        return rowDeleted;     
    }

    public boolean deleteSchedule(Schedule shed) throws SQLException {
        String sql = "DELETE FROM Schedule WHERE id = ?";
        PreparedStatement statement = null;
        connect();
        
            statement = jdbcConnection.prepareStatement(sql);
            statement.setInt(1, shed.getId());
            boolean rowDeleted = statement.executeUpdate() > 0;
        
        statement.close();
        disconnect();
        return rowDeleted;     
    }

    public boolean cancelSchedule(String roomId, String courseId,String sectionId,String instructor, String day, String startTime, String endTime) throws SQLException {
        PreparedStatement statement = null;
        String sql = "INSERT INTO Schedule"
                           + "(roomId,courseId, sectionId,instructor, day, start, end, is_active) "
                           + "VALUES" + "(?,?,?,?,?,?,?,?)";
        connect();
         try {
            //dbConnection = getDBConnection();
            statement = jdbcConnection.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(roomId));
            statement.setInt(2,Integer.parseInt(courseId));
            statement.setInt(3,Integer.parseInt(sectionId));
            statement.setString(4, instructor);
            statement.setString(5, day);
            statement.setInt(6, Integer.parseInt(startTime));
            statement.setInt(7,Integer.parseInt(endTime));
            statement.setInt(8, 1); //long?/
            statement.executeUpdate();
        } catch (SQLException e) {
             System.out.println(e.getMessage());
        } finally {
        }
        return false;//0?
    }
    
    /*//************************************************************LIST/RETRIEVE*********************************************************************\\*/

    public boolean validate(Account acc) throws SQLException {
        boolean status = false;
        String sql = "SELECT * FROM Account WHERE username = ? and password = ?";
        connect();

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

    /*public Account getAccount(String username) throws SQLException {//??
        Account acc = null;
        String sql = "SELECT * FROM Account WHERE id = ? and username=?";
        connect();
        
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
         
            if (resultSet.next()) {
                String user = resultSet.getString("username");//?
                
                acc = new Account(user);//, username, firstname, lastname, email, password);
            }
        
        resultSet.close();
        statement.close();
        return acc;
    }*/
     
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
        String sql = "SELECT * FROM Schedule WHERE Cid = ? and Ctitle=?";
         
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

    public List<Schedule> ListSchedule() throws SQLException {
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

    
    public List<Schedule> getUnavailableRooms(String day, String startTime, String endTime, String roomId) throws SQLException {
        PreparedStatement statement = null;
        List<Schedule> inactive = new ArrayList<>();
        String sql = "SELECT * FROM Classroom WHERE roomId IN (SELECT S_roomId FROM Schedule WHERE day ? AND start BETWEEN ? AND ? or end BETWEEN ? AND ?)";
        connect();
        try {
            //dbConnection = getDBConnection();
            statement = jdbcConnection.prepareStatement(sql);
            statement.setString(1, day);
            statement.setLong(2,Integer.parseInt(startTime));
            statement.setLong(3,Integer.parseInt(endTime));
            statement.setLong(4, Integer.parseInt(startTime));
            statement.setLong(5, Integer.parseInt(endTime));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
            	Schedule room = new Schedule();
                room.setDate(rs.getString("day"));
                room.setStartTime(rs.getString("start"));
                room.setEndTime(rs.getString("end"));
                room.setRoom(rs.getString("room"));
                inactive.add(room);
            }
        }catch(Exception e){
            }
        return inactive;
    }

    public List<Schedule> getAvailableRooms(String day, String startTime, String endTime, String roomId) throws SQLException {
        connect();
        PreparedStatement statement = null;
        List<Schedule> availableRooms = new ArrayList<>();
        String sql = "SELECT * FROM Classroom WHERE roomId NOT IN (SELECT S_roomId FROM Schedule WHERE day ? AND start BETWEEN ? AND ? or end BETWEEN ? AND ?)";
        try {
           //dbConnection = getDBConnection();
            statement = jdbcConnection.prepareStatement(sql);
            statement.setString(1, day);
            statement.setLong(2, Integer.parseInt(startTime));
            statement.setLong(3, Integer.parseInt(endTime));
            statement.setLong(4, Integer.parseInt(startTime));
            statement.setLong(5, Integer.parseInt(endTime));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
            	Schedule room = new Schedule();
                room.setDate(rs.getString("day"));
                room.setStartTime(rs.getString("start"));
                room.setEndTime(rs.getString("end"));
                room.setRoom(rs.getString("room"));
                availableRooms.add(room);
            }
        }catch(Exception e){
        } 
        return availableRooms;
    }

    public Classroom getClassRoom(String id) throws SQLException {
        connect();
        Classroom room = null;
        PreparedStatement statement = null;
        String sql = "SELECT * FROM Classroom WHERE id = ?";
        try {
          statement = jdbcConnection.prepareStatement(sql);
          statement.setLong(1, Integer.parseInt(id));
          ResultSet rs = statement.executeQuery();
          if(rs.next()) {
            room = new Classroom();
            System.out.println("ID: "+rs.getLong("id"));
            room.setId(rs.getString("room"));
            room.setType(rs.getString("type"));
            room.setSeats(rs.getString("seat"));
            room.setComputers(rs.getString("computers")); 
          }
          
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        //rs.close();
        statement.close();
        return room;
      }

    /*//************************************************************UPDATE*********************************************************************\\*/

    public int updateSchedule(Schedule shed) throws SQLException {
        connect();
       // ResultSet resultSet = null;
        String sql = "update Schedule set roomId=?, courseId=?, sectionId=?,instructor=?,day=?,start=?, end=? where id=?";
        int i = 0;
        
          PreparedStatement statement = jdbcConnection.prepareStatement(sql);
          statement.setString(1 , shed.getRoom());
          statement.setString(2 , shed.getCourse());
          statement.setString(3 , shed.getSection());
          statement.setString(4 , shed.getInstructor());
          statement.setString(5 , shed.getDay());
          statement.setString(6 , shed.getStartTime());
          statement.setString(7 , shed.getEndTime());
          
          //resultSet = statement.executeQuery();
           i =   statement.executeUpdate();
    
        return i;
      }

    
    public boolean updateClass(Classroom room) throws SQLException {
        String sql = "UPDATE Classroom SET id = ?, type = ?, seat = ?, computer =?";
        sql += " WHERE id = ?";
        connect();
        
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setString(1, room.getId());
            statement.setString(2, room.getType());
            statement.setString(3, room.getSeat());
            statement.setString(4,room.getComputers());
            boolean rowUpdated = statement.executeUpdate() > 0;
        
        statement.close();
        disconnect();
        return rowUpdated;     
    }
    
    /*//************************************************************ADD/CREATE*********************************************************************\\*/
    public boolean createAccount(Account acc) throws SQLException { //addAccount
    	PreparedStatement statement = null;
        String sql = "INSERT INTO Schedule"
                + "(username, firstname,lastname, email,password) "
                + "VALUES" + "(?,?,?,?,?)";
        connect();
         
        statement = jdbcConnection.prepareStatement(sql);
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

    public boolean ScheduleRoom(String roomId, String courseId,String sectionId,String instructor, String day, String startTime, String endTime) throws SQLException { //int??//addSchedule
        
        PreparedStatement statement = null;
        String sql = "INSERT INTO Schedule"
                           + "(roomId, courseId, sectionId, instructor, day, start, end, is_active) "
                           + "VALUES" + "(?,?,?,?,?,?,?,?)";
        connect();
         try {
            //dbConnection = getDBConnection();
            statement = jdbcConnection.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(roomId));
            statement.setInt(2,Integer.parseInt(courseId));
            statement.setInt(3,Integer.parseInt(sectionId));
            statement.setString(4, instructor);
            statement.setString(5, day);
            statement.setInt(6, Integer.parseInt(startTime));
            statement.setInt(7,Integer.parseInt(endTime));
            statement.setInt(8, 1); //long?/
            statement.executeUpdate();
        } catch (SQLException e) {
             System.out.println(e.getMessage());
        } finally {
        }
        return false;//0??
    }
    
    public boolean addAccount(String username, String firstname,String lastname,String email, String password) throws SQLException { //int??//addSchedule
        PreparedStatement statement = null;
        String sql = "INSERT INTO Schedule"
                           + "(username, firstname,lastname, email,password) "
                           + "VALUES" + "(?,?,?,?,?)";
        connect();
         try {
            //dbConnection = getDBConnection();
            statement = jdbcConnection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, firstname);
            statement.setString(3, lastname);
            statement.setString(4, email);
            statement.setString(5, password);
          
            statement.executeUpdate();
        } catch (SQLException e) {
             System.out.println(e.getMessage());
        } finally {
        }
        return false;//0??
    }
}