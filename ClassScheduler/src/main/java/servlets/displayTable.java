package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Classroom;
import entities.Course;
import entities.Instructor;
import entities.Schedule;
import util.UtilClass;
import util.UtilCourse;
import util.UtilInstructor;
import util.UtilSchedule;

@WebServlet("/displayTable")
public class displayTable extends HttpServlet {
  private static final long serialVersionUID = 1L;
       
    
    public displayTable() {
        super();
    }
 
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    List<Classroom> list1 = new ArrayList<Classroom>();
    List<Course> list2 = new ArrayList<Course>();
    List<Schedule> list3 = new ArrayList<Schedule>();
    List<Instructor> list4 = new ArrayList<Instructor>();
    
    list1 = UtilClass.getClassById();
    list2 = UtilCourse.getCourseByTitle();
    list3 = UtilSchedule.getSchedulesByDay();
    list4 = UtilInstructor.getInstructorByName();
    
    System.out.print(list1.size());
    System.out.print(list2.size());
    System.out.print(list3.size());
    System.out.print(list4.size());
    
    if(list1 == null || list2 == null || list3 == null || list4 == null){
      request.setAttribute("Error", list1);
      request.getRequestDispatcher("login.jsp").forward(request, response);
    }
    
    request.setAttribute("list", list1);
    request.setAttribute("list", list2);
    request.setAttribute("list", list3);
    request.setAttribute("list", list4);
    RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
    System.out.println("home.jsp");
    rd.forward(request, response);
  }
  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }
}
