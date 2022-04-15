package servlets;

import java.io.IOException;
//import java.sql.SQLException;
//import java.util.List;
import java.sql.SQLException;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DB.BackupDB;
import entities.Section;

@WebServlet("/addSection")
public class addSection extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BackupDB db;

	public addSection() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		String courseId = request.getParameter("courseId");
		String type = request.getParameter("type");
		String method = request.getParameter("method");
		String enroll = request.getParameter("enroll");
		String room = request.getParameter("room");
		String instructor = request.getParameter("instructor");
		String meeting = request.getParameter("meeting");
		String term = request.getParameter("term");

		Section s = new Section(id, courseId, type, method, enroll, room, instructor, meeting, term);

		try {
			boolean status = db.saveSection(s);
			if (status) {
				session.setAttribute("Added", status);
				response.sendRedirect("addSection.jsp");// look into object

			} else {
				session.setAttribute("Failed", status);
				response.sendRedirect("addSection.jsp");
			}
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("addSection.jsp");

	}
}