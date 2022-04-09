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

import util. UtilClass;
import entities.Classroom;

@WebServlet("/updateRoom")
public class updateRoom extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UtilClass Uclass;

    public updateRoom() {
        super();
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
		
		try {
			Update(request, response);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
       
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
		
		response.sendRedirect("updateRoom.jsp");
	}

	private void Update(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession();
		Classroom rm = new Classroom();

        String roomNum = request.getParameter(rm.getId());
        String type = request.getParameter("type");
		String building = request.getParameter(rm.getBuilding());
		String seats = request.getParameter("seats");
		String computers = request.getParameter("computers");
		
		rm.setId(roomNum);
		rm.setBuilding(building);
		rm.setType(type);
		rm.setSeats(seats);
		rm.setComputers(computers);
		
		Uclass.updateClass(rm);
	   
		if(rm != null && rm.getBuilding().equals(building)) {
			session.setAttribute("updated", "true");
			response.sendRedirect("updateRoom.jsp");
			
		} else {
			session.setAttribute("Failed", "false");
			response.sendRedirect("updateRoom.jsp");
		}
    }
	
}