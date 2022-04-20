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

@WebServlet("/addRoom")
public class addRoom extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private BackupDB db;

  public addRoom() {
      super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
		HttpSession session = request.getSession();

	    String room = request.getParameter("room");
	    String type = request.getParameter("type");
		String seats = request.getParameter("seat");
		String computers = request.getParameter("computers");
			
		Classroom rm = new Classroom(room,type,seats,computers);	
		    
			try {
				boolean status = db.saveDB(rm);
				if(status) {
					session.setAttribute("Added", status);
					response.sendRedirect("addClass.jsp");
					
				} else {
					session.setAttribute("Failed", status);
					response.sendRedirect("addClass.jsp");
				}
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
	  }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
		 response.sendRedirect("addClass.jsp");
		
	}
}