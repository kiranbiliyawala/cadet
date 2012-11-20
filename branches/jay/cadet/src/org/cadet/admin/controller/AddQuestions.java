package org.cadet.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cadet.admin.bean.Category;
import org.cadet.admin.model.CategoryManagement;

/**
 * Servlet implementation class AddQuestionPrior
 * 
 *@WebServlet("/AddQuestionPrior") 
 * 
 */

public class AddQuestions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddQuestions() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String error="";
		try {
			HttpSession session = request.getSession();
			
			
        	int categoryId = Integer.parseInt(request.getParameter("hdnCategoryId"));
			ArrayList<Category> categories = null;
			CategoryManagement objCategoryManagement = new CategoryManagement();
			String status = "";
			        
			try{
			    categories = objCategoryManagement.viewCategory();
			    session.setAttribute("categoryId", Integer.toString(categoryId));
			    session.setAttribute("categoryName", objCategoryManagement.viewCategoryByCategoryId(categoryId));
			    status = "success";
			}
			catch(Exception e){
			    e.printStackTrace();
			    error = e.getMessage();
			    status = "fail";
			}
			
			request.setAttribute("status", status);
			request.setAttribute("categories", categories);
			System.out.println(categories);
			out.println("<html>");
			out.println("<body>");
			out.println("<h1>" + status + "</h1><br/>");
			out.println("<h3>" + categories + "</h3>");
			
			RequestDispatcher rd = request.getRequestDispatcher("/admin/questionbank/AddQuestion.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error=e.getMessage();
		}finally{
		}
		out.println("<h1>" + error + "</h1>");
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
