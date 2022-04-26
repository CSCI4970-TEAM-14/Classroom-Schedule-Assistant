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

@WebServlet("/removeSection")
public class removeSection extends HttpServlet {
private static final long serialVersionUID = 1L;
private BackupDB db;

public removeSection() {
  super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
		HttpSession session = request.getSession();
		
	    String course = request.getParameter("course");
		String section = request.getParameter("sec");
		String method = request.getParameter("meth");
			
		Section sh = new Section(course,section,method);	
		    
			try {
				boolean status = db.deleteSection(sh);
				if(status) {
					session.setAttribute("Removed", status);
					response.sendRedirect("removeSection.jsp");
					
				} else {
					session.setAttribute("Failed", status);
					response.sendRedirect("removeSection.jsp");
				}
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}
	  }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
		 response.sendRedirect("removeSection.jsp");
		
	}
}