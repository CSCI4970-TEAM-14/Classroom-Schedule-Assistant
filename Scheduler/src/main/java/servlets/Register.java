package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DB.BackupDB;
import entities.Account;

@WebServlet("/Register")
public class Register extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BackupDB db;

    public Register() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	
    	//int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        System.out.println("Entered:" + username + ","+ firstName + "," + lastName + "," + email);
        Account acc = new Account(username, firstName, lastName, email, password);
        
        db = new BackupDB();
        
        try {
        	boolean status = db.createAccount(acc);
			if(status){
				   session.setAttribute("Registered", status);
				   response.sendRedirect("userHome.jsp? name=" +firstName);
			} else {
			    session.setAttribute("inUse", status);
				response.sendRedirect("register.jsp");
			    }
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.sendRedirect("register.jsp");
    }
}