package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.UtilAccount;
import entities.Account;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
        super();
    }

	/*
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			HttpSession session = request.getSession();
		
		String userId = request.getParameter("userId");
		String pwd = request.getParameter("pwd");
		
				
		Account acc = UtilAccount.getAccount(userId);
		//String userName = request.getParameter(acc.getFirstName());

		if( acc != null && acc.getPassword().equals(pwd)){
			session.setAttribute("userAcc", acc);
			response.sendRedirect("userHome.jsp?name="+userId);
			
		} else if(!acc.getPassword().equals(pwd)){
			session.setAttribute("invalid", "true");
			response.sendRedirect("login.jsp");
		}
	}
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			doGet(request, response);
	}

}
