package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DB.BackupDB;
import entities.Schedule;

public class scheduleController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BackupDB db;
      
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
 
        try {
            switch (action) {
            case "/list":
                listSchedules(request, response);
                break;
            case "/add":
                addSchedule(request, response);
                break;
            case "/update":
                updateSchedule(request, response);
                break;
            case "/remove":
                removeSchedule(request, response);
                break;
            default:
                userHome(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
 
    private void listSchedules(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Schedule> sh = db.ListSchedule();
     
        request.setAttribute("schedule", sh);
        RequestDispatcher dispatcher = request.getRequestDispatcher("scheduleView.jsp");
        dispatcher.forward(request, response);
    }
 
    private void removeSchedule(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
                HttpSession session = request.getSession();
     
                String courseId = request.getParameter("courseId");
                String sectionId = request.getParameter("sectionId");
                String instructor = request.getParameter("instructor");	
                String day = request.getParameter("day");
           
 
        Schedule sh = new Schedule(courseId, sectionId, instructor, day);
        db = new BackupDB();
        boolean status = db.deleteSchedule(sh);

        if(status) {
            session.setAttribute("Removed", status);
            response.sendRedirect("addSchedule.jsp");                    
        } else {
            session.setAttribute("Failed", status);
            response.sendRedirect("addSchedule.jsp");
        }
        //db.deleteSchedule(sh);
        //response.sendRedirect("removeSchedule.jsp");
    }
 
    private void addSchedule(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
    		HttpSession session = request.getSession();
		
	    String course = request.getParameter("course");
		String section = request.getParameter("sec");
		String method = request.getParameter("meth");
		String enroll = request.getParameter("enr");
		String instructor = request.getParameter("in");
	    String day = request.getParameter("day");
	    String start = request.getParameter("start");
		String end = request.getParameter("end");
		String room = request.getParameter("room");
			
		Schedule sh = new Schedule(course,section,method,enroll, instructor,day, start,end,room);	
		    
			try {
				boolean status = db.saveSchedule(sh);
				if(status) {
					session.setAttribute("Added", status);
					response.sendRedirect("addSchedule.jsp");
					
				} else {
					session.setAttribute("Failed", status);
					response.sendRedirect("addSchedule.jsp");
				}
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}
	  }
 
    private void updateSchedule(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
    	 HttpSession session = request.getSession();
    	String course = request.getParameter("course");
 		String section = request.getParameter("sec");
 		String method = request.getParameter("meth");
 		String enroll = request.getParameter("enr");
 		String instructor = request.getParameter("in");
 	    String day = request.getParameter("day");
 	    String start = request.getParameter("start");
 		String end = request.getParameter("end");
 		String room = request.getParameter("room");
 			
 		Schedule sh = new Schedule(course,section,method,enroll, instructor,day, start,end,room);
                int status = db.updateSchedule(sh);
                if(status != 0 ) { //look into it
                    session.setAttribute("Updated", status);
                    response.sendRedirect("updateSchedule.jsp");                    
                } else {
                    session.setAttribute("Failed", status);
                    response.sendRedirect("updateSchedule.jsp");
                }
                //db.updateSchedule(sh);
                //response.sendRedirect("updateSchedule.jsp");
    }
    private void userHome(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
        response.sendRedirect("update.jsp");
    }
}