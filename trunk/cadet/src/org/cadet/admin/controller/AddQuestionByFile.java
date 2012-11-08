package org.cadet.admin.controller;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cadet.admin.bean.QuestionBank;
import org.cadet.admin.model.QuestionBankManagement;
import org.cadet.admin.model.TestDbTransactions;
import org.cadet.util.model.DatabaseConnection;

/**
 * Servlet implementation class csv
 * 
 * @WebServlet("/csv")
 * 
 */

public class AddQuestionByFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AddQuestionByFile() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean status=true;
        String contentType = request.getContentType();
        
        if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0)) //
        {
        	
            DataInputStream in = new DataInputStream(request.getInputStream());
            int formDataLength = request.getContentLength();
            byte dataBytes[] = new byte[formDataLength];
            int byteRead = 0;
            int totalBytesRead = 0;
            while (totalBytesRead < formDataLength) {
                byteRead = in.read(dataBytes, totalBytesRead, formDataLength);
                totalBytesRead += byteRead;
         }
        
         String file = new String(dataBytes);
         BufferedReader br = null;
         
         try{
            	
            	QuestionBank question = new QuestionBank();
            	QuestionBankManagement objQuestionBankManagement = new QuestionBankManagement();
	            
            	String start_str = "Category,Level,Question,Option A,Option B,Option C,Option D,Correct Answer";
            	String end_str = "$EOF";
            	
            	int start = file.indexOf(start_str)+start_str.length();
            	int end = file.indexOf(end_str);
            	
            	file=file.substring(start,end);
	            br = new BufferedReader(new StringReader(file));
	            
	            String line = "";
	            
	            int catId = 0;
	            
	            //read comma separated file line by line
	            line = br.readLine();
	            String[] que;
	            while ((line = br.readLine()) != null) {
	               
	                que=line.split(",");
	                try{
	                	catId=objQuestionBankManagement.getCategoryIdByName(que[0]);
	                }catch(SQLException sqle){
	                	status=false;
	                	sqle.printStackTrace();
	                }catch(Exception ex){
	                	ex.printStackTrace();
	                	if(ex.getMessage().equals("No such Category Exists!")){
	                		//code to add new new category
	                		try{
	                			TestDbTransactions.addCategory(DatabaseConnection.getInstance().getDbConnection(), que[0]);
	                		}catch(Exception ex1){
	                			status = false;
	                			ex1.printStackTrace();
	                		}
	                	}else
	                		status=false;
	                }
	                if(que.length==8){
	                question.setCategoryId(Integer.toString(catId));
	                question.setLevelId(que[1]);
	                question.setQuestion(que[2]);
	                question.setOptionA(que[3]);
	                question.setOptionB(que[4]);
	                question.setOptionC(que[5]);
	                question.setOptionD(que[6]);
	                question.setCorrectAnswer(que[7]);
	                objQuestionBankManagement.addQuestion(question);
	                }      
	            }
	            status=true;
	        } 
            catch (Exception e) {
            	e.printStackTrace();
	            status = false;
            }
            finally{
            	br.close();
            }
       
            if(status)
            	request.setAttribute("status", "success");
            else
            	request.setAttribute("status", "fail");
         
			RequestDispatcher rd = request.getRequestDispatcher("/admin/questionbank/UploadByFile.jsp");
			rd.forward(request, response);
        }
    }
}
