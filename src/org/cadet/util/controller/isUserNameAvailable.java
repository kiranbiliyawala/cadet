package org.cadet.util.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cadet.util.model.DatabaseConnection;
import org.cadet.util.model.ErrorLogging;
import org.cadet.util.model.UserControl;

/**
 * Servlet implementation class isUserNameAvailable
 */
@WebServlet("/isUserNameAvailable")
public class isUserNameAvailable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public isUserNameAvailable() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DatabaseConnection.getInstance().getDbConnection();
		
		String username = request.getParameter("username");
		try {
			if(UserControl.isClientAvailable(conn, username)){
				return;
			}else{
				response.sendError(403);
				return;
			}
		} catch (SQLException e) {
			ErrorLogging.getInstance().log(Level.SEVERE, e);
			response.sendError(403);
			return;
		}
		
		
	}

}
