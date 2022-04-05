package servlets;

import java.io.IOException;
//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.UtilAccount;
import entities.Account;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilAccount login;
	Account acc = null;

	public void init() {
		login = new UtilAccount();
	}

	/*
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}


	private void verify(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String userId = request.getParameter("userId");
		String pwd = request.getParameter("pwd");
		
				
		Account acc = UtilAccount.getAccount(userId);
		String userName = request.getParameter(acc.getFirstName());

		if (!login.validate(userId, pwd)) {
			session.setAttribute("userAcc", acc);
			response.sendRedirect("userHome.jsp?name="+userName);
			
		} else {
			session.setAttribute("Invalid", "true");
			response.sendRedirect("login.jsp"); 
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			verify(request, response);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
