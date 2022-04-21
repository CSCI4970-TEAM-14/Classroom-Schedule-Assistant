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
        Account a = new Account();
        a.setUserName(username);
        a.setPassword(password);
        HttpSession session = request.getSession();
         
        try {
            boolean acc = db.validate(a);
             
            if (acc) {
            	session.setAttribute("acc", acc);
                response.sendRedirect("userHome.jsp?name"+username);
            } else {
            	session.setAttribute("invalid", acc);
				response.sendRedirect("login.jsp");
            }             
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    		  throws ServletException, IOException {
    		    	 response.sendRedirect("login.jsp");
    		  }
}
