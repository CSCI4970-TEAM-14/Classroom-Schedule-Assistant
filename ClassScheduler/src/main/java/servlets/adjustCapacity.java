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

import util. UtilClass;
import entities.Classroom;

@WebServlet("/adjustCap")
public class adjustCapacity extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UtilClass Uclass;
    private Classroom c;


    public void init(){
        Uclass = new UtilClass();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	
    	try {
			adjust(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    	response.sendRedirect("adjust.jsp");
    }

    private void adjust(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
    	
    	HttpSession session = request.getSession();

        String roomNum = request.getParameter(c.getId());
		String building = request.getParameter(c.getBuilding());
		String seats = request.getParameter("seats");
		//String seat = c.getSeat();
		
		Classroom rm = new Classroom();
		rm.setSeats(seats);
		rm.setId(roomNum);
		rm.setBuilding(building);
		Uclass.updateClass(rm);
       
        if(Uclass.getClass(roomNum, building) != null) {
        	session.setAttribute("adjusted", "true");
			response.sendRedirect("adjust.jsp");
        } 
        else {
        	session.setAttribute("failed", "false");
			response.sendRedirect("adjust.jsp");
           }        
    }
}
