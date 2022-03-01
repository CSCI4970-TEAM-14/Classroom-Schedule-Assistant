package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Account;
import Util.UtilAccount;
/**
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignUp() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		//All fields associated with account creation.
		String userName = request.getParameter("username");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");


		//Checks if all fields were filled
		if (userName.isEmpty() || firstName.isEmpty() || lastName.isEmpty() ||  email.isEmpty() || password.isEmpty()) {
			session.setAttribute("missing", "true");
			response.sendRedirect("register.jsp");
		}

		//Checks if any fields inserted exceeded 20 characters
		else if (userName.length() > 20 || firstName.length() > 20 || lastName.length() > 20 || email.length() > 20 || password.length() > 20) {
			session.setAttribute("fields", "true");
			response.sendRedirect("register.jsp");
		}
		//Checks if the username is taken
		else if (UtilAccount.getAccount(userName) != null) {
			session.setAttribute("username", "true");
			response.sendRedirect("register.jsp");
		}

		//Adds the account to the database or fails to
		else if (!UtilAccount.createAccount(userName,firstName,lastName,email,password)) {
			session.setAttribute("failed", "true");
			response.sendRedirect("register.jsp");
		}

		else {
			//Adds the account to the session (attributes are stored as objects so all account methods should work).
			session.setAttribute("account", UtilAccount.getAccount(userName));
			response.sendRedirect("clientAccount.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}