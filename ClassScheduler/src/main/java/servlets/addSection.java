package servlets;

import java.io.IOException;
import java.sql.SQLException;
//import java.util.List;
//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.UtilSection;
import entities.Section;

@WebServlet("/addSection")
public class addSection extends HttpServlet {
    private static final long serialVersionUID = 1L;
   
    public addSection() {
    	super();    
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
		
		try {
			Addsection(request, response);
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
		
		response.sendRedirect("addSection.jsp");
	}

	private void Addsection(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession();

        String id = request.getParameter("id");
        String courseId = request.getParameter("courseId");
        String type = request.getParameter("type");
        String method = request.getParameter("method");
        String enroll = request.getParameter("enroll");
        String room = request.getParameter("room");
        String instructor = request.getParameter("instructor");
		String meeting = request.getParameter("meeting");
		String term = request.getParameter("term");
	   
		Section s = new Section();
		UtilSection u = new UtilSection();
		
		s.setId(id);
		s.setCourseId(courseId);
		s.setType(type);
		s.setMethod(method);
		s.setEnroll(enroll);
		s.setRoom(room);
		s.setInstructor(instructor);
		s.setMeet(meeting);
		s.setTerm(term);
		
		u.saveSection(s);
	 			
		if(UtilSection.getSection(id) != null) {
			session.setAttribute("Failed", "true");
			response.sendRedirect("addSection.jsp");
		} else {
			session.setAttribute("Added", "false");
			response.sendRedirect("addSection.jsp");
		}
    }
	
}