package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DB.BackupDB;
import entities.Schedule;

@WebServlet("/removeSchedule")
public class removeSchedule extends HttpServlet {
private static final long serialVersionUID = 1L;
private BackupDB db;

public removeSchedule() {
  super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
		HttpSession session = request.getSession();
		
	    String course = request.getParameter("course");
		String section = request.getParameter("sec");
		String instructor = request.getParameter("in");
	    String day = request.getParameter("day");
			
		Schedule sh = new Schedule(course,section, instructor,day);	
		    
			try {
				boolean status = db.removeSchedule(sh);
				if(status) {
					session.setAttribute("Removed", status);
					response.sendRedirect("removeSchedule.jsp");
					
				} else {
					session.setAttribute("Failed", status);
					response.sendRedirect("removeSchedule.jsp");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	  }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
		 response.sendRedirect("removeSchedule.jsp");
		
	}
}