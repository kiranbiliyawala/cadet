package org.cadet.admin.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperRunManager;

import org.cadet.admin.model.Result;
import org.cadet.util.model.Constants;
import org.cadet.util.model.DatabaseConnection;

/**
 * Servlet implementation class ShowResult
 */
@WebServlet("/admin/test/Result/Result.pdf")
public class ShowResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowResult() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processrequest(request,response);
	}

	private void processrequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		SendPdf(request,response);
		
	}

	private void SendPdf(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String testid= request.getParameter("testid").toString();
		Connection connection = DatabaseConnection.getInstance().getDbConnection();
		ServletOutputStream outputstream =response.getOutputStream();
		InputStream reportStream = getServletConfig().getServletContext().getResourceAsStream("/admin/js/report.jasper");
		String SUBREPORT_DIR = getServletConfig().getServletContext().getRealPath("/admin/js/")+"/";
		try {	
			
			HashMap parameterMap = new HashMap();
			parameterMap.put("SUBREPORT_DIR", SUBREPORT_DIR);
			parameterMap.put("testid", Integer.parseInt(testid));
			
			JasperRunManager.runReportToPdfStream(reportStream,outputstream, parameterMap, connection);
		}  catch (JRException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		response.setContentType("application/pdf");
		 outputstream.flush();
		 outputstream.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processrequest(request,response);
	}

}
