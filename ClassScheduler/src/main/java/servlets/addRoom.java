package servlets;

import java.io.IOException;
//import java.sql.SQLException;
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

@WebServlet("/addRoom")
public class addRoom extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public addRoom() {
        super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
		HttpSession session = request.getSession();

        String roomNum = request.getParameter("roomNumber");
        String type = request.getParameter("type");
		String building = request.getParameter("building");
		String seats = request.getParameter("seats");
		String computers = request.getParameter("computers");
		
		Classroom rm = new Classroom();
		UtilClass Uclass = new UtilClass();
		
		rm.setId(roomNum);
		rm.setType(type);
		rm.setBuilding(building);
		rm.setSeats(seats);
		rm.setComputers(computers);
		
		Uclass.saveClass(rm);	
	    
		if(Uclass.getRoom(roomNum) != null) {
			session.setAttribute("Failed", "true");
			response.sendRedirect("addRoom.jsp");
			
		} else {
			session.setAttribute("Added", "false");//Uclass.getRoom(roomNum));
			response.sendRedirect("addRoom.jsp");
		}
    }
}