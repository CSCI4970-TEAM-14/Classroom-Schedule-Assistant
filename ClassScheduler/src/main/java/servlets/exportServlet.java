package servlets;

import java.io.*;
import java.sql.*;
import javax.servlet.annotation.WebServlet;
import  java.io.IOException;
import  javax.servlet.ServletException;
import  javax.servlet.http.HttpServlet;
import  javax.servlet.http.HttpServletRequest;
import  javax.servlet.http.HttpServletResponse;

import util.ExportDB;

@WebServlet("/exportServlet")
public class exportServlet extends HttpServlet {

    private static final long serialVersionUID= 1L;
	//private static final String ExportDB = null;
	ExportDB db = new ExportDB();

    public exportServlet(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
  
    	response.sendRedirect("userHome.jsp");
    	String table = request.getParameter("table");
        db.export(table);
        
        }	
        /*if() {
        	session.setAttribute("Found", "false");
			response.sendRedirect("userHome.jsp");
        }	
		} else {
			session.setAttribute("notFound", "true");
			response.sendRedirect("userHome.jsp");
		}  */ 

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
    	doGet(request, response);
    }
}