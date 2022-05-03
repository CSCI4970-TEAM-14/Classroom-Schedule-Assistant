package servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DB.BackupDB;
import entities.Schedule;

@WebServlet("/updateSchedule")
public class updateSchedule extends HttpServlet {
private static final long serialVersionUID = 1L;
private BackupDB db;

public updateSchedule() {
  super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
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
		
		int cap = 0;
        
        try { 
        	cap = Integer.parseInt(enroll);
        	} catch (NumberFormatException nfe)
        { nfe.printStackTrace(); }
			
		Schedule sh = new Schedule(course,section,method, cap, instructor,day, start,end,room);	
		db = new BackupDB();
		    
		try {
			boolean status = db.updateSchedule(sh);
			boolean get = db.getSchedule(day, start, end, room);
			if(get) {
				session.setAttribute("UnAvailable", get);
                response.sendRedirect("updateSchedule.jsp");
                
            }else if(status){
                session.setAttribute("Updated", status);
				response.sendRedirect("updateSchedule.jsp");
            }
			else {
				session.setAttribute("Failed", status);
				response.sendRedirect("updateSchedule.jsp");
			}
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}
	  }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
		 response.sendRedirect("updateSchedule.jsp");
		
	}
}