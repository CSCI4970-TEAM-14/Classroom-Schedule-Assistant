package servlets;

import java.io.IOException;
import java.sql.SQLException;
//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DB.BackupDB;
import entities.Classroom;

@WebServlet("/adjustCap")
public class adjustCapacity extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BackupDB db;

    public adjustCapacity() {
        super();
  	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	
    	String room = request.getParameter("room");
		String seats = request.getParameter("seats");
		//String computers = request.getParameter("comp");
		
		Classroom rm = new Classroom(room,seats);	
	    
		try {
			boolean status = db.adjustCap(rm);
			if(status) {
				session.setAttribute("Adjusted", status);
				response.sendRedirect("adjustClass.jsp");
				
			} else {
				session.setAttribute("Failed", status);
				response.sendRedirect("adjustClass.jsp");
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}	
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    	response.sendRedirect("adjustClass.jsp");
    }
}
