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

import util.UtilSchedule;
//import util. UtilClass;
import entities.Schedule;
//import entities.Classroom;

@WebServlet("/addSchedule")
public class addSchedule extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public addSchedule() {
        super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
		
		try {
			Schedule(request, response);
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
		
		response.sendRedirect("addSchedule.jsp");
	}

	private void Schedule(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession();
		
		Schedule sh = new Schedule();
		UtilSchedule u = new UtilSchedule();

        String day = request.getParameter("day");
        String start= request.getParameter("start");
        String end = request.getParameter("end");
        //String sectionId = request.getParameter("method");
        
        sh.setDate(day);
        sh.setStartTime(start);
        sh.setEndTime(end);
        
        u.saveSchedule(sh);
	 			
		if(UtilSchedule.getScheduleBySection(day) != null) {
			session.setAttribute("Added", "true");
			response.sendRedirect("addSchedule.jsp");			
		} else {
			session.setAttribute("Failed", "false");
			response.sendRedirect("addSchedule.jsp");
		}
    }
	
}