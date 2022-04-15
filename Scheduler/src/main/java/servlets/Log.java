package servlets;

import java.io.*;
import java.sql.SQLException;
 
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import DB.BackupDB;
import entities.Account;
 
@WebServlet("/log")
public class Log extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public Log() {
        super();
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
         
        BackupDB db = new BackupDB();
        HttpSession session = request.getSession();
         
        try {
            Account acc = db.checkLogin(username, password);
             
            if (acc != null) {
            	session.setAttribute("acc", acc);
                response.sendRedirect("userHome.jsp?name="+username);
            } else {
            	session.setAttribute("invalid", "true");
				response.sendRedirect("userHome.jsp?name="+username);
            }             
        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    		  throws ServletException, IOException {
    		    	 response.sendRedirect("login.jsp");
    		  }
}
