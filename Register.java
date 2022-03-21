package servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Util.UtilAccount;
import entities.Account;
/**
 * Servlet implementation class SignUp
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilAccount sign;
	
	 public void init() {
	        sign = new UtilAccount();
	    }
	
	private void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		HttpSession session = request.getSession();

		//All fields associated with account creation.
		int id = Integer.parseInt(request.getParameter("int"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Account acc = new Account();
		acc.setId(id);
		acc.setFirstName(firstName);
		acc.setLastName(lastName);
		acc.setEmail(email);
		acc.setPassword(password);
		
		Account ac = new Account(id,firstName,lastName,email,password);
		
		if(UtilAccount.saveAccount(ac)) {
			session.setAttribute("account", UtilAccount.createAccount(id,firstName,lastName,email,password));
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
    		dispatcher.forward(request, response);
		} else {
			session.setAttribute("failed", "true");
			response.sendRedirect("register.jsp");
		}

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		response.sendRedirect("register.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		register(request, response);
	}
}