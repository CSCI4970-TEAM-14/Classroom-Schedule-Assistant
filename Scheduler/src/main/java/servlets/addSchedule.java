package servlets;

import java.io.IOException;
//import java.sql.SQLException;
//import java.util.List;
import java.sql.SQLException;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DB.BackupDB;
import entities.Schedule;

@WebServlet("/addSchedule")
public class addSchedule extends HttpServlet {
private static final long serialVersionUID = 1L;
private BackupDB db;

public addSchedule() {
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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
		 response.sendRedirect("addSchedule.jsp");
		
	}
}