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
 
@WebServlet("/importServlet")
public class importServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BackupDB db;

    public importServlet(){
        super();
    }
     
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession();
        // gets values of text fields
        //String csvFilePath = "schedule.csv";

        db = new BackupDB();

        try {
           db.Import(); //calling import function
				   session.setAttribute("Imported", db);
				   response.sendRedirect("import.jsp");
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        }
    }
}
