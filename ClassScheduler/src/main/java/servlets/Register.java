package servlets;

import java.io.IOException;
//import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Account;
import util.UtilAccount;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 public Register() {
	       super();
	    }
	 
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					      
		 HttpSession session = request.getSession();
			
			String user = request.getParameter("user");
			String firstName = request.getParameter("first");
			String lastName = request.getParameter("last");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			Account acc = new Account();
			UtilAccount u = new UtilAccount();
			
			acc.setUserName(user);
			acc.setFirstName(firstName);
			acc.setLastName(lastName);
			acc.setEmail(email);
			acc.setPassword(password);
			
			u.saveAccount(acc);
			
			if (UtilAccount.getAccount(user) != null) {
				session.setAttribute("inUse", "true");
				response.sendRedirect("register.jsp");
			}
			else if (!UtilAccount.createAccounts(user, firstName, lastName, email, password)) {
				session.setAttribute("notCreated", "true");
				response.sendRedirect("userHome.jsp?name="+firstName);
			}
			else {
				u.saveAccount(acc);
				session.setAttribute("account", UtilAccount.getAccount(user));
				response.sendRedirect("userHome.jsp?name=" +firstName);
			}
		   }
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}
}