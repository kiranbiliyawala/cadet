package org.cadet.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.cadet.admin.bean.Category;
import org.cadet.admin.bean.QuestionBank;
import org.cadet.admin.model.CategoryManagement;
import org.cadet.admin.model.QuestionBankManagement;

/**
 * Servlet implementation class ViewQuestion
 */
@WebServlet("/ViewQuestion")
public class ViewQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		try {
			out.println("TRY block-1 Begins<br/><br/>");
			HttpSession session = request.getSession();
			session.setAttribute("user", "admin");
			session.setAttribute("AdminAuth", true);
			session.setAttribute("ClientAuth", false);
			session.setAttribute("homeurl","admin/dashboard");
			
			ArrayList<QuestionBank> questions = null;
			QuestionBankManagement objQuestionBankManagement = new QuestionBankManagement();
			
			ArrayList<Category> categories = null;
			CategoryManagement objCategoryManagement = new CategoryManagement();

			String status = "", error="", categoryId="";
			try{
				//out.println("TRY block-2 Begins<br/><br/>");
				try{
					categoryId = request.getParameter("hdnCategoryId").toString();
					session.setAttribute("categoryId", categoryId);
					session.setAttribute("categoryName", objCategoryManagement.viewCategoryByCategoryId(Integer.parseInt(categoryId)));
				}
				catch(Exception e){
					categoryId = (String)session.getAttribute("categoryId");
				}
				//out.println("<h3>CategoryId: " + categoryId + " </h3><br/>");
				if(categoryId.compareTo("0") == 0){
					questions = objQuestionBankManagement.viewAllQuestion();
					out.println("Length : " + questions.size() + "<br/><br/>");
				}
				else
					questions = objQuestionBankManagement.viewCategorywiseQuestion(Integer.parseInt(categoryId));
			    categories = objCategoryManagement.viewCategory();
				out.println("End of TRY block-2<br/><br/>");
				status = "success";
			}
			catch(Exception e){
				status = "fail";
				out.println("Error \"" + e.getMessage() + "\" from CATCH block-2 with status: " + status + "<br/><br/>");
			}
			
			request.setAttribute("status", status);
			request.setAttribute("questions", questions);
			request.setAttribute("categories", categories);
			request.setAttribute("categoryId", categoryId);
			out.println("<h3>Status: " + status + "</h3>");
			out.println("<h3>Categories: " + categories + "</h3>");
			out.println("<h3>Questions: " + questions + "</h3>");
			out.println("<h3>Error: " + error + "</h3>");
			
			RequestDispatcher rd = request.getRequestDispatcher("/admin/questionbank/ViewQuestion.jsp");
			rd.forward(request, response);
			out.println("End of TRY block-1<br/><br/>");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			out.println("OUTER CATCH BLOCK<br/><br/>");
			e.printStackTrace();
			out.println("<h2>" + e.getMessage() + "</h2>");
		}finally{
			out.println("OUTER FINALLY BLOCK<br/><br/>");
			out.println("</body>");
			out.println("</html>");
			out.close();
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
