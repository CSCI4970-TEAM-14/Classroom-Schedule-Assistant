package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util. UtilClass;
import entities.Classroom;

@WebServlet("/adjustCap")
public class adjustCapacity extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UtilClass Uclass;
    private Classroom cRoom;


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

        int roomNum = Integer.parseInt(request.getParameter("roomNumber"));
		String building = request.getParameter("building");
		int seats = Integer.parseInt(request.getParameter("seats"));
		int seat = cRoom.getSeat();
		
		Classroom rm = new Classroom(roomNum, building, seats);
       
        if(Uclass.getClass(seat) != null) {
        	UtilClass.updateClass(rm);
        	RequestDispatcher dispatcher = request.getRequestDispatcher("clientAccount.jsp");
			dispatcher.forward(request, response);
        } 
        else {
           
	      
        }        
    }
}
