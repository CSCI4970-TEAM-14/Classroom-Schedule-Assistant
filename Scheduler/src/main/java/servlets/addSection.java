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
import entities.Section;

@WebServlet("/addSection")
public class addSection extends HttpServlet {
private static final long serialVersionUID = 1L;
private BackupDB db;

public addSection() {
  super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
		HttpSession session = request.getSession();
		
	    String course = request.getParameter("crs");
		String section = request.getParameter("sec");
		String method = request.getParameter("meth");
		String enroll = request.getParameter("enr");
		String instructor = request.getParameter("inst");
		String term = request.getParameter("term");
		
		int cap = 0;
        
        try { 
        	cap = Integer.parseInt(enroll);
        	} catch (NumberFormatException nfe)
        { nfe.printStackTrace(); }
			
		Section sh = new Section(section, course, method, cap, instructor, term);	
		System.out.println("Entered:" + course + ","+ section + "," + method);
        
	    db = new BackupDB();
		    
			try {
				boolean status = db.saveSection(sh);
				if(status) {
					session.setAttribute("Added", status);
					response.sendRedirect("addSection.jsp");
					
				} else {
					session.setAttribute("Failed", status);
					response.sendRedirect("addSection.jsp");
				}
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}
	  }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
		 response.sendRedirect("addSection.jsp");
		
	}
}