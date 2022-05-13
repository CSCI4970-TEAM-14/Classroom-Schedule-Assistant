package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DB.BackupDB;
import entities.Schedule;
import entities.Section;

@WebServlet("/Assign")
public class Assign extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private BackupDB db;
  private Section sched;

  public Assign() {
      super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
		HttpSession session = request.getSession();

	    String section = request.getParameter(sched.getSection());
	    String course = request.getParameter(sched.getCourseId());
	    
		/*
		 * try { List<Section> list = db.listSection(section, course); } catch
		 * (SQLException e1) { // TODO Auto-generated catch block e1.printStackTrace();
		 * }
		 */
	    
		        
		Section sh = new Section(course, section);
		System.out.println("Entered:" + section + ","+ section);
    		
        
		    db = new BackupDB();
			try {
				boolean status = db.assignSection(sh);
				if(status) {
					session.setAttribute("Assigned", status);
					response.sendRedirect("assign.jsp");
					
				} else {
					session.setAttribute("Failed", status);
					response.sendRedirect("assign.jsp");
				}
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
	  }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
		 response.sendRedirect("assign.jsp");
		
	}
}