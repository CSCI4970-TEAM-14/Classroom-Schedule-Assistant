package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DB.BackupDB;
import entities.Schedule;
import entities.Classroom;

public class availability extends HttpServlet {
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
                listSchedules(request, response);
                break;
            case "/unavailable":
                unAvailable(request, response);
                break;
            case "/available":
                availableRooms(request, response);
                break;
            case "/cancel":
                cancelSchedule(request, response);
                break;
            default:
                userHome(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
 
    private void listSchedules(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Schedule> sh = db.ListSchedule();
     
        request.setAttribute("schedule", sh);
        RequestDispatcher dispatcher = request.getRequestDispatcher("scheduleList.jsp");
        dispatcher.forward(request, response);
    }
 
    private void unAvailable(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
                HttpSession session = request.getSession();
                String room = request.getParameter("room");	
                String day = request.getParameter("day");
                String start= request.getParameter("start");
                String end = request.getParameter("end");
 
                List<Schedule> sh = db.getUnavailableRooms(day, start, end, room);
                /*sh.setRoom(room);
                sh.setDay(day);
                sh.setStart(start);
                sh.setEnd(end);*/

                db = new BackupDB();
                sh = db.getUnavailableRooms(day, start, end, room);

                if(sh != null) {
                    session.setAttribute("Retrieved", "false");
                    response.sendRedirect("availability.jsp");                    
                } else {
                    session.setAttribute("Failed", "true");
                    response.sendRedirect("availability.jsp");
                }
    }
 
    private void availableRooms(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
                HttpSession session = request.getSession();
                String room = request.getParameter("room");	
                String day = request.getParameter("day");
                String start= request.getParameter("start");
                String end = request.getParameter("end");
 
                //Schedule sh = new Schedule(room,  sectionId, instructor, day, start, end);
                db = new BackupDB();
                
                try {
                List <Schedule> status = db.getAvailableRooms(day, start, end, room);

                 /*{
                    session.setAttribute("Added", "false");
                    response.sendRedirect("addSchedule.jsp");                    
                } else {
                    session.setAttribute("Failed", "true");
                    response.sendRedirect("addSchedule.jsp");
                }*/
                }catch(Exception e) {
                	e.printStackTrace();
                }
                session.setAttribute("Added", "false");
                response.sendRedirect("addSchedule.jsp");
    }
 
    private void cancelSchedule(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
    			HttpSession session =  request.getSession();
    			
                String room = request.getParameter("room");
                String courseId = request.getParameter("courseId");
                String sectionId = request.getParameter("sectionId");
                String instructor = request.getParameter("instructor");	
                String day = request.getParameter("day");
                String start= request.getParameter("start");
                String end = request.getParameter("end");
 
                Schedule sh = new Schedule(courseId, sectionId, instructor, day);
                int status = db.updateSchedule(sh);
                if(status != 0) {
                    session.setAttribute("Updated", status);
                    response.sendRedirect("updateSchedule.jsp");                    
                } else {
                    session.setAttribute("Failed", status);
                    response.sendRedirect("updateSchedule.jsp");
                }
                //db.updateSchedule(sh);
                //response.sendRedirect("updateSchedule.jsp");
    }
    private void userHome(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
        response.sendRedirect("update.jsp");
    }
}