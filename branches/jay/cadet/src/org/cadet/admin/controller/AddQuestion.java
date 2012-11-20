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
 * Servlet implementation class AddQuestion
 * 
 * @WebServlet("/AddQuestion")
 * 
 */

public class AddQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean status=false;
		PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        QuestionBank question = new QuestionBank();
        QuestionBankManagement objQuestionBankManagement = new QuestionBankManagement();
        try{
        	question.setCategoryId(session.getAttribute("categoryId").toString());
            question.setLevelId(request.getParameter("level"));
            question.setQuestion(request.getParameter("question"));
            question.setOptionA(request.getParameter("optionA"));
            question.setOptionB(request.getParameter("optionB"));
            question.setOptionC(request.getParameter("optionC"));
            question.setOptionD(request.getParameter("optionD"));
            question.setCorrectAnswer(request.getParameter("correctAnswer"));
            
            try{
            	objQuestionBankManagement.addQuestion(question);
                status = true;
            }
            catch(Exception e){
                status = false;
            }
        }catch(Exception e){
        	status=false;
        }
        if(status){
        	out.print("Question Added");
            request.setAttribute("status", "Done");
        }
        else{
        	out.print("Question Not Added");
        	request.setAttribute("status", "Fail");
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
