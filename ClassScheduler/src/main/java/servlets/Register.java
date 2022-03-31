package servlets;

import java.io.IOException;
//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.UtilAccount;
import entities.Account;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 public Register() {
	       super();
	    }
	 
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
				
			response.sendRedirect("register.jsp");
	}
	
	private void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		HttpSession session = request.getSession();

		String id = request.getParameter("id");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
				
		System.out.println("Entered:" + id + "," + firstName + "," + email);
		
		Account acc = new Account(id, firstName, lastName, email, password);
		acc.setId(id);
		acc.setFirstName(firstName);
		acc.setLastName(lastName);
		acc.setEmail(email);
		acc.setPassword(password);
		
		UtilAccount.createAccount(id,firstName,lastName,email,password);//?
		
		if(UtilAccount.getAccount(id) != null) {
			session.setAttribute("inUse", "true");
			response.sendRedirect("register.jsp");
			
		} else if(!UtilAccount.createAccount(id,firstName,lastName,email,password)) {
			session.setAttribute("Failed", "true");
			response.sendRedirect("register.jsp");
			
		} else {
			session.setAttribute("userAcc", UtilAccount.getAccount(id));
			response.sendRedirect("login.jsp");
		}

  }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		register(request, response);
	}
}