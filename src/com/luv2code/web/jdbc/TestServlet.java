package com.luv2code.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@Resource(name="/jdbc/web_student_tracker")
	private DataSource dataSource;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// printWriter
		PrintWriter printWriter = response.getWriter();
		response.setContentType("text/plain");
		// get connection to db
		Connection myConn = null;
		Statement myStatement = null;
		ResultSet myRs = null;
		try {
		
			myConn = dataSource.getConnection();
		// create sql statements
			
			String sql = "select * from student";
			myStatement = myConn.createStatement();			
		// execute sql query

			myRs = myStatement.executeQuery(sql);		
		
		// process the result
			while (myRs.next()) {
				String email = myRs.getString("email");
				printWriter.println(email);		
			}

		
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
		
		
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
