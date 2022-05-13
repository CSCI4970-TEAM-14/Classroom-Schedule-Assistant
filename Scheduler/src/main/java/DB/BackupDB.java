package DB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
import entities.Section;

public class BackupDB{
	String Driver = "com.mysql.jdbc.Driver";
	String URL = "jdbc:mysql://ec2-3-129-194-150.us-east-2.compute.amazonaws.com:3306/CSA?useSSL=false";
	String Name = "CSA";
	String Username = "nangatid";
	String Password = "TKey";	
	private Connection jdbcConnection;
	private Schedule sh;
    
/*//************************************************************Database Connection**************************************************************\\*/	
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
    
 /*//************************************************************ADD/CREATE*********************************************************************\\*/
    public boolean createAccount(Account acc) throws SQLException { 
    	PreparedStatement statement = null;
        String sql = "INSERT INTO Account"
                + "(username, firstname, lastname, email, password) "
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

	public boolean saveSection(Section sh) throws SQLException {
		PreparedStatement statement = null;
        String sql = "INSERT INTO Section"
                + "(section, course, method, enroll, instructor, term) "
                + "VALUES" + "(?,?,?,?,?,?)";
        connect();
         
        statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, sh.getSection());
        statement.setString(2, sh.getCourseId());
        statement.setString(3, sh.getMethod());
        statement.setInt(4, sh.getEnroll());
        statement.setString(5, sh.getInstructor());
        statement.setString(6, sh.getTerm());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        
        statement.close();
        disconnect();
        return rowInserted;
	}
    
	 public boolean saveDB(Classroom cl) throws SQLException {
	    	PreparedStatement statement = null;
	        String sql = "INSERT INTO Classroom"
	                + "(room, type, seat) "
	                + "VALUES" + "(?,?,?)";
			 
		        connect();  
		        statement = jdbcConnection.prepareStatement(sql);
		        //statement.setInt(1, Integer.parseInt(cl.getId()));
		        statement.setString(1, cl.getId());
		        statement.setString(2, cl.getType());
		        statement.setInt(3,    cl.getSeat());
		        
		        boolean rowInserted = statement.executeUpdate() > 0;
		        statement.close();
		        disconnect();
		        return rowInserted;
		}

		public boolean saveSchedule(Schedule sh) throws SQLException {
			String sql = "INSERT INTO Schedule (course, section,method,enroll, instructor,day,start,end, room) VALUES (?, ?, ?,?,?,?,?, ?, ?)";
	        connect();
	         
	        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
	       
	        statement.setString(1, sh.getCourse());
	        statement.setString(2, sh.getSection());
	        statement.setString(3, sh.getMethod());
	        statement.setInt(4, sh.getEnroll());
	        statement.setString(5, sh.getInstructor());
	        statement.setString(6, sh.getDay());
	        statement.setString(7, sh.getStartTime());
	        statement.setString(8, sh.getEndTime());// active
	        statement.setString(9, sh.getRoom());
	        
	        boolean rowInserted = statement.executeUpdate() > 0;
	        statement.close();
	        disconnect();
	        return rowInserted;
		}
/*//************************************************************UPDATE*********************************************************************\\*/  
 
    public boolean adjustCap(Classroom room) throws SQLException {
        String sql = "UPDATE Classroom SET seat = ?";
        sql += " WHERE room = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, room.getSeat());
        statement.setString(2, room.getId());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
    
    
    public boolean updateSchedule(Schedule shed) throws SQLException {
        
        String sql = "UPDATE Schedule SET method =?, enroll =?, instructor=?, day=?, start=?, end=?, room =?";
        sql += " WHERE section = ? AND course =?";
        connect();
        
          PreparedStatement statement = jdbcConnection.prepareStatement(sql);
          statement.setString(1 , shed.getMethod());
          statement.setInt(2 , shed.getEnroll());
          statement.setString(3 , shed.getInstructor());
          statement.setString(4 , shed.getDay());
          statement.setString(5 , shed.getStartTime());
          statement.setString(6 , shed.getEndTime());
          statement.setString(7 , shed.getRoom());
          statement.setString(8 , shed.getSection());
          statement.setString(9 , shed.getCourse());
          
          
          boolean rowUpdated = statement.executeUpdate() > 0;
          
          statement.close();
          disconnect();
          return rowUpdated; 
      }
    
    public boolean updateClass(Classroom room) throws SQLException {
        String sql = "UPDATE Classroom SET type = ?, seat = ?";
        sql += " WHERE room = ?";
        connect();
        
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setString(1, room.getType());
            statement.setInt(2, room.getSeat());
            statement.setString(3, room.getId());
            
            boolean rowUpdated = statement.executeUpdate() > 0;
        
        statement.close();
        disconnect();
        return rowUpdated;     
    }
    
    public boolean updateSection(Section sh) throws SQLException {
		String sql = "UPDATE Section SET course =?, method = ?, enroll =?, instructor =?, term =?";
		sql += " WHERE section = ?";
        
        connect();
        
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setString(1, sh.getCourseId());
            statement.setString(2, sh.getMethod());
            statement.setInt(3,    sh.getEnroll());
            statement.setString(4, sh.getInstructor());
            statement.setString(5, sh.getTerm());
            statement.setString(6, sh.getSection());
    
            boolean rowUpdated = statement.executeUpdate() > 0;
        
        statement.close();
        disconnect();
        return rowUpdated;  
	}
	
	
/*//************************************************************DELETE/REMOVE*********************************************************************\\*/
	
	public boolean deleteClass(Classroom room) throws SQLException {
        String sql = "DELETE FROM Classroom WHERE room = ?"; 
        connect();
         
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setString(1, room.getId());
            
            boolean rowDeleted = statement.executeUpdate() > 0;
        
        statement.close();
        disconnect();
        return rowDeleted;     
    }

    public boolean deleteSchedule(Schedule shed) throws SQLException {
        String sql = "DELETE FROM Schedule WHERE course = ? AND section =?";
        PreparedStatement statement = null;
        connect();
        
            statement = jdbcConnection.prepareStatement(sql);
            //statement.setInt(0, shed.getId());
            statement.setString(1, shed.getCourse());
            statement.setString(2, shed.getSection());
           
            boolean rowDeleted = statement.executeUpdate() > 0;
        
        statement.close();
        disconnect();
        return rowDeleted;     
    }
    
    public boolean deleteSection(Section sec) throws SQLException {
        String sql = "DELETE FROM Section WHERE section = ? AND course =?";//??
        PreparedStatement statement = null;
        connect();
        
            statement = jdbcConnection.prepareStatement(sql);
            statement.setString(1, sec.getSection());
            statement.setString(2, sec.getCourseId());
            
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
    	PreparedStatement statement = null;
    	String sql = "SELECT * FROM Schedule";
    	  connect();
    	  try {
    	    statement = jdbcConnection.prepareStatement(sql);
    	    ResultSet rs= statement.executeQuery();
    	    while (rs.next()) {
    	    Schedule sh = new Schedule();
    	    sh.setId(rs.getInt("id"));
    	    sh.setRoom("room");
    	    sh.setCourse("courseIdsh");
    	    sh.setSection("section");
    	    sh.setInstructor("instructor");
    	    sh.setDate(rs.getString("day"));
            sh.setStartTime(rs.getString("start"));
            sh.setEndTime(rs.getString("end"));
            sh.setRoom(rs.getString("room"));
    	    list.add(sh);
    	    }
    	  } catch (Exception e) {
    	    // TODO Auto-generated catch block
    	    e.printStackTrace();
    	  }finally {
    	    statement.close();
    	  }
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
        String sql = "SELECT * FROM Classroom WHERE room = ?";
        try {
          statement = jdbcConnection.prepareStatement(sql);
          statement.setString(1, id);
          ResultSet rs = statement.executeQuery();
          while(rs.next()) {
            room = new Classroom();
            room.setId(rs.getString("room"));
            room.setType(rs.getString("type"));
            room.setSeats(rs.getInt("seat"));
        
          }
          
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        //rs.close();
        statement.close();
        return room;
      }
    
    public Account getAccount(String username) throws SQLException {
        connect();
        Account acc = null;
        PreparedStatement statement = null;
        String sql = "SELECT * FROM Account WHERE username = ?";
        try {
          statement = jdbcConnection.prepareStatement(sql);
          statement.setString(1, username);
          ResultSet rs = statement.executeQuery();
          while(rs.next()) {
            acc = new Account();
            acc.setUserName(rs.getString("username"));        
          }
          
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        //rs.close();
        statement.close();
        return acc;
      }
    
    public boolean getSchedule(String Day, String start, String end, String room) throws SQLException {
    	connect();
    	//Schedule sh = null;
        PreparedStatement statement = null;
        String sql = "SELECT start, end, room FROM Schedule WHERE day = ?";
        
        try{ 
            statement = jdbcConnection.prepareStatement(sql);
            statement.setString(1, Day);
            statement.setString(2, start);
            statement.setString(3, end);
            statement.setString(4, room);
            ResultSet rs = statement.executeQuery();
         
            while(rs.next()) {
            	//sh = new Schedule();
                rs.getString("day");
                rs.getString("start");
                rs.getString("end");
                rs.getString("room");   
            }
            rs.close();
            statement.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        //disconnect();
        return false;
    }
    
    /*//************************************************************Assign*********************************************************************\\*/
       
    public List<Schedule> listClass() throws SQLException {
        List<Schedule> list = new ArrayList<>();
         
        String sql = "SELECT room FROM Schedule WHERE course IS NULL";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
         
        while (rs.next()) {
            int id = rs.getInt("id");
            String course = rs.getString("course");
            String section = rs.getString("section");
            String method = rs.getString("method");
            int enroll = rs.getInt("enroll");
            String instructor = rs.getString("instructor");
            String day = rs.getString("day");
            String start = rs.getString("start");
            String end = rs.getString("end");
            String rm = rs.getString("room");
           Schedule sh = new Schedule(id, course, section, method, enroll, instructor, day, start, end, rm);  
            list.add(sh);
        }
         
        rs.close();
        statement.close();
         
        disconnect();
         
        return list;
    }
    
    public List<Section> listSection(String section, String course) throws SQLException {
        List<Section> list = new ArrayList<>();
        PreparedStatement statement = null;
         
        String sql = "SELECT section FROM Section WHERE course IS NOT NULL";
         
        connect();
         
        statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, section);
        statement.setString(2, course);
        ResultSet rs = statement.executeQuery();
         
        while (rs.next()) {
            int id = rs.getInt("id");
            rs.getString("course");
            rs.getString("section");
            String method = rs.getString("method");
            int enroll = rs.getInt("enroll");
            String instructor = rs.getString("instructor");
  
           Section sh = new Section(id,section, course, method, enroll, instructor);  
            list.add(sh);
        }
        rs.close();
        statement.close();
        disconnect();
         
        return list;
    }
    
    public void Assign() throws SQLException {
        connect();
        PreparedStatement statement = null;
        String sql = "SELECT room FROM Schedule WHERE course, section, method, enroll, instructor IS NULL AND (INSERT IGNORE INTO Schedule (course, section, method, enroll, instructor)";
            sql += "SELECT method, enroll, instructor FROM Section WHERE section AND course IS NOT NULL)";
        
        try{
        	sh = null;
        	sh = new Schedule();
            statement = jdbcConnection.prepareStatement(sql);
            statement.setString(1, sh.getCourse());
            statement.setString(2, sh.getSection());
            statement.setString(3, sh.getMethod());
            statement.setInt(4,    sh.getEnroll());
            statement.setString(5, sh.getInstructor());
            statement.executeUpdate();

            System.out.println(statement);
          
            statement.close();
            disconnect();

        }catch(SQLException e){
            e.printStackTrace();
        }

    }
    
    public boolean assignSection(Section sh) throws SQLException {
    	connect();  	
    	
    	String sql = "INSERT IGNORE INTO Schedule (course, section, method, enroll, instructor) VALUES (?, ?, ?, ?,?)";
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, sh.getCourseId());
        statement.setString(2, sh.getSection());
        statement.setString(3, sh.getMethod());
        statement.setInt(4, sh.getEnroll());
        statement.setString(5, sh.getInstructor());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
    	
    	return rowInserted;
    }
/*//************************************************************Import & Export*********************************************************************\\*/
    
    public void Import() throws SQLException {
        
        String csvFilePath = "c:\\csv\\schedule.csv";
        int batchSize = 20;
        connect();

        try {
        	jdbcConnection.setAutoCommit(false);
            String sql = "INSERT INTO Schedule (course, section, method, enroll, instructor, day, start, end, room) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
 
            BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
            String lineText = null;
 
            int count = 0;
            lineReader.readLine(); // skip header line
 
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                String course = data[0];
                String section = data[1];
                String method = data[2];
                String enroll = data[3];
                String instructor = data[4];
                String day = data[5];
                String start = data[6];
                String end = data[7];
                String room = data[8];
                
            
                statement.setString(1, course);
                statement.setString(2, section);
                statement.setString(3, method);
                statement.setInt(4, Integer.parseInt(enroll));
                statement.setString(5, instructor);
                statement.setString(6, day);
                statement.setString(7, start); 
                statement.setString(8, end);
                statement.setString(9, room);
                statement.addBatch();
 
                if (count % batchSize == 0) {
                    statement.executeBatch();
                }
            }
 
            lineReader.close();
            // execute remaining queries
            statement.executeBatch();
 
            jdbcConnection.commit();
            jdbcConnection.close();
            System.out.println("<b>You have Successfully imported the Csv file.</b>");
 
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (SQLException ex) {
            ex.printStackTrace();
 
            try {
                jdbcConnection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //return false
    }
    
    
    public void ExportSchedule() throws SQLException {
    String filename = "c:\\csv\\schedule.csv";
    connect();
    
    Statement statement = null;
    try {
    	FileWriter fw = new FileWriter(filename);
    	fw.append("Id");
    	fw.append(',');
    	fw.append("Course");
    	fw.append(',');
    	fw.append("Section");
    	fw.append(',');
    	fw.append("Method");
    	fw.append(',');
    	fw.append("Enrollment");
    	fw.append(',');
    	fw.append("Instructor");
    	fw.append(',');
    	fw.append("Meeting");
    	fw.append(',');
    	fw.append(',');
    	fw.append(',');
    	fw.append("Room no.");
    	fw.append('\n');
    	
    	String query = "SELECT * FROM Schedule";
    	statement = jdbcConnection.createStatement();
    	ResultSet rs = statement.executeQuery(query);
    	while (rs.next()) {
    		fw.append(rs.getString(1)); //id
    		fw.append(',');
    		fw.append(rs.getString(2)); //course
    		fw.append(',');
    		fw.append(rs.getString(3)); //section
    		fw.append(',');
    		fw.append(rs.getString(4)); //method
    		fw.append(',');
    		fw.append(rs.getString(5)); //enrollment
    		fw.append(',');
    		fw.append(rs.getString(6)); //instructor
    		fw.append(',');
    		fw.append(rs.getString(7)); //day
    		fw.append(',');
    		fw.append(rs.getString(8)); //start
    		fw.append(',');
    		fw.append(rs.getString(9)); //end
    		fw.append(',');
    		fw.append(rs.getString(10)); //room
    		fw.append('\n');
    	}
    	fw.flush();
    	fw.close();
    	jdbcConnection.close();
    	System.out.println("<b>You have Successfully Created Csv file.</b>");
    } catch (Exception e) {
    	e.printStackTrace();
    }
} 
}