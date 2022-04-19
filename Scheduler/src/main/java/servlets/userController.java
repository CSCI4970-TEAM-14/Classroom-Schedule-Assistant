package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.BackupDB;
import entities.Classroom;

public class userController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BackupDB db;
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
 
        try {
            switch (action) {
            case "/list":
                listRoom(request, response);
                break;
            case "/add":
                addRoom(request, response);
                break;
            case "/update":
                updateRoom(request, response);
                break;
            case "/remove":
                removeRoom(request, response);
                break;
            default:
                userHome(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
 
    private void listRoom(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Classroom> room = db.listClassRoom();
     
        request.setAttribute("room", room);
        RequestDispatcher dispatcher = request.getRequestDispatcher("scheduleView.jsp");
        dispatcher.forward(request, response);
    }
 
    private void removeRoom(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
    	String id = request.getParameter("id");
        String type = request.getParameter("type");
        String seat = request.getParameter("seat");
        String computers = request.getParameter("computers");
 
        Classroom cl = new Classroom(id,type,seat,computers);
        db = new BackupDB();
        db.deleteDB(cl);
        response.sendRedirect("removeClass.jsp");
    }
 
    private void addRoom(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
    	String id = request.getParameter("id");
        String type = request.getParameter("type");
        String seat = request.getParameter("seat");
        String computers = request.getParameter("computers");
 
        Classroom cl = new Classroom(id,type,seat,computers);
        db = new BackupDB();
        db.saveDB(cl);
        response.sendRedirect("addClass.jsp");
    }
 
    private void updateRoom(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String id = request.getParameter("id");
        String type  = request.getParameter("type");
        String seat = request.getParameter("seat");
        String computers = request.getParameter("computers");
 
        Classroom cl = new Classroom(id,type,seat,computers);
        db.updateDB(cl);
        response.sendRedirect("updateClass.jsp");
    }
    private void userHome(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
        response.sendRedirect("update.jsp");
    }
}
