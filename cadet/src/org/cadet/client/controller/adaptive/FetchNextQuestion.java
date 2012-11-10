package org.cadet.client.controller.adaptive;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidAlgorithmParameterException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cadet.client.bean.Question;
import org.cadet.client.model.adaptive.AdaptiveTest;
import org.cadet.util.Exceptions.DificultyExhaustException;
import org.cadet.util.Exceptions.NoSuchTestException;
import org.cadet.util.Exceptions.SectionCompleteException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class FetchNextQuestion
 */
@WebServlet("/client/Test/Adaptive/FetchNextQuestion")
public class FetchNextQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchNextQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	
	private void getFirstQuestion(JSONObject data,AdaptiveTest test,HttpServletRequest request, HttpServletResponse response){

		int categoryId=Integer.parseInt(request.getParameter("categoryId"));
		
			Question question;
			try {
				question = test.startSection(categoryId);
				JSONArray array=new JSONArray();
				try {
					array.put(0, question.getQuestion());
					array.put(1, question.getOptionA());
					array.put(2, question.getOptionB());
					array.put(3, question.getOptionB());
					array.put(4, question.getOptionD());
					data.put("question", array);
					data.put("result", true);
					//out.println(data);
					return;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}
			} catch (InvalidAlgorithmParameterException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				try {
					data.put("result", "algorithmError");
					return;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				try {
					data.put("result", "databaseError");
					return;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}
			} catch (SectionCompleteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				try {
					data.put("result", "sectionFinished");
					return;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}
			} catch (NoSuchTestException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				try {
					data.put("result", "accessDeniedError");
					return;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}
			} catch (DificultyExhaustException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

	}
	
	private void SubmitAndNxtQuestion(JSONObject data,AdaptiveTest test,HttpServletRequest request, HttpServletResponse response){

		String answer=request.getParameter("answer");
		
			Question question;
			try {
				question = test.submitQuestion(answer);
				JSONArray array=new JSONArray();
				try {
					array.put(0, question.getQuestion());
					array.put(1, question.getOptionA());
					array.put(2, question.getOptionB());
					array.put(3, question.getOptionB());
					array.put(4, question.getOptionD());
					data.put("question", array);
					data.put("result", true);
					return;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				try {
					data.put("result", "databaseError");
					return;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}
			} catch (SectionCompleteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				try {
					data.put("result", "sectionFinished");
					return;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}
			} catch (DificultyExhaustException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
	}
	
	private void SkipQuestion(JSONObject data,AdaptiveTest test,HttpServletRequest request, HttpServletResponse response){
		 //skip question
		
			Question question;
			try {
				question = test.skipQuestion();
				JSONArray array=new JSONArray();
				try {
					array.put(0, question.getQuestion());
					array.put(1, question.getOptionA());
					array.put(2, question.getOptionB());
					array.put(3, question.getOptionB());
					array.put(4, question.getOptionD());
					data.put("question", array);
					data.put("result", true);
					return;
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					return;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					data.put("result", "databaseError");
					return;
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					return;
				}
			} catch (SectionCompleteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					data.put("result", "sectionFinished");
					return;
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					return;
				}
			} catch (DificultyExhaustException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestType=request.getParameter("requestType");
		JSONObject data=null;
		PrintWriter out;
		AdaptiveTest test;
		
		response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out=response.getWriter();
			data=new JSONObject();
		
		
		try{
			HttpSession session = request.getSession(false);

			if (session == null) {
				try {
					data.put("result", "invalidSession");
					out.println(data);
					return;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}
			}
			test = (AdaptiveTest) session.getAttribute("test");
			
			if(requestType.equals("getFirstQuestion")){
			getFirstQuestion(data,test,request, response);
				
			}
			else if(requestType.equals("submitQuestion")){
				SubmitAndNxtQuestion(data, test, request, response);
			}
			else {
				SkipQuestion(data, test, request, response);
			}
			out.println(data);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
