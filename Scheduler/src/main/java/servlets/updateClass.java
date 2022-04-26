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
import entities.Classroom;

@WebServlet("/updateClass")
public class updateClass extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private BackupDB db;

  public updateClass() {
      super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
		HttpSession session = request.getSession();

	    String room = request.getParameter("room");
	    String type = request.getParameter("type");
		String seats = request.getParameter("seat");
		
		/*int roomId = 0;
		int seat = 0;
		int comp = 0;
        
        try { 
        	roomId = Integer.parseInt(room);
        	seat = Integer.parseInt(seats);
        	} catch (NumberFormatException nfe)
        { nfe.printStackTrace(); }*/	
		Classroom rm = new Classroom(room,type,seats);
		System.out.println("Entered:" + room + ","+ type + "," + seats);
        
		    
			try {
				boolean status = db.updateClass(rm);
				if(status) {
					session.setAttribute("Updated", status);
					response.sendRedirect("updateClass.jsp");
					
				} else {
					session.setAttribute("Failed", status);
					response.sendRedirect("updateClass.jsp");
				}
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
	  }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
		 response.sendRedirect("updateClass.jsp");
		
	}
}