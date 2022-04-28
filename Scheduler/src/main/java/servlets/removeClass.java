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

@WebServlet("/removeClass")
public class removeClass extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private BackupDB db;

  public removeClass() {
      super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
		HttpSession session = request.getSession();

	    String room = request.getParameter("room");
	    String type = request.getParameter("type");
		String seats = request.getParameter("seat");
	
		Classroom rm = new Classroom(room,type,seats);
		System.out.println("Entered:" + room + ","+ type + "," + seats);
        
		    
			try {
				boolean status = db.deleteClass(rm);
				if(status) {
					session.setAttribute("Removed", status);
					response.sendRedirect("removeClass.jsp");
					
				} else {
					session.setAttribute("Failed", status);
					response.sendRedirect("removeClass.jsp");
				}
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
	  }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
		 response.sendRedirect("removeClass.jsp");
		
	}
}