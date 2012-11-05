package org.cadet.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cadet.admin.bean.QuestionBank;
import org.cadet.admin.model.QuestionBankManagement;

/**
 * Servlet implementation class EditQuestion
 * 
 *@WebServlet("/EditQuestion") 
 * 
 */

public class EditQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		boolean status;
		try {
			HttpSession session = request.getSession();
			session.setAttribute("user", "admin");
			session.setAttribute("AdminAuth", true);
			session.setAttribute("ClientAuth", false);
			session.setAttribute("homeurl","admin/dashboard");
			
			QuestionBank question = new QuestionBank();
			QuestionBankManagement objQuestionBankManagement = new QuestionBankManagement();

			question.setQuestionId(request.getParameter("questionId"));
        	question.setCategoryId(request.getParameter("categoryId"));
            question.setLevelId(request.getParameter("level"));
            question.setQuestion(request.getParameter("question"));
            question.setOptionA(request.getParameter("optionA"));
            question.setOptionB(request.getParameter("optionB"));
            question.setOptionC(request.getParameter("optionC"));
            question.setOptionD(request.getParameter("optionD"));
            question.setCorrectAnswer(request.getParameter("correctAnswer"));

			try{
				//out.println("TRY block-2 Begins<br/><br/>");
				objQuestionBankManagement.editQuestion(question);
				status = true;
			}
			catch(Exception e){
				status = false;
				out.println("Error \"" + e.getMessage() + "\" from CATCH block-2 with status: " + status + "<br/><br/>");
			}
	        if(status){
	        	out.print("Question Edited");
	            request.setAttribute("status", "Done");
	        }
	        else{
	        	out.print("Question Not Edited");
	        	request.setAttribute("status", "Fail");
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}finally{
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
