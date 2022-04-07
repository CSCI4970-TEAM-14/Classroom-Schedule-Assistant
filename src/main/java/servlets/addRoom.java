package servlets;

import java.io.IOException;
import java.sql.SQLException;
//import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.UtilAccount;
import util. UtilClass;
import entities.Classroom;
//import entities.Classroom;

@WebServlet("/addRoom")
public class addRoom extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UtilClass Uclass;

    public void init() {
        Uclass = new UtilClass();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
		
		try {
			Addroom(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
       
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
		
		response.sendRedirect("addRoom.jsp");
	}

	private void Addroom(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession();

        String roomNum = request.getParameter("roomNumber");
        String type = request.getParameter("type");
		String building = request.getParameter("building");
		String seats = request.getParameter("seats");
		String computers = request.getParameter("computers");

		Classroom rm = new Classroom(roomNum, type, building, seats, computers);
	    
	    /*if(UtilClass.addClassroom(roomNum, type, building, seats, computers)) {
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("addRoom2.jsp");
			dispatcher.forward(request, response);
	    } else {
	    	session.setAttribute("failed", "true");
			response.sendRedirect("addRoom.jsp"); //add
	    }*/
	    
	 			
		if(!UtilClass.addClassroom(roomNum, type, building, seats, computers)) {
			session.setAttribute("Failed", "true");
			response.sendRedirect("addRoom.jsp");
			
		} else {
			session.setAttribute("userAcc", UtilClass.updateClass(rm));
			response.sendRedirect("addRoom2.jsp");
		}
    }
	
}