package org.cadet.admin.controller;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.cadet.util.controller.StringEscapeUtils.escapeHtml3;

import org.cadet.admin.bean.QuestionBank;
import org.cadet.admin.model.QuestionBankManagement;

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
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		boolean status=true;
        String saveFile = "";
        String contentType = request.getContentType();
        out.println("<html>");
        out.println("<body>");
        if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0)) 
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
            saveFile = file.substring(file.indexOf("filename=\"") + 10);
            out.println(saveFile);
            saveFile = saveFile.substring(0, saveFile.indexOf("\n"));
            out.println(saveFile);
            saveFile = "C:\\" + saveFile.substring(saveFile.lastIndexOf("\\") + 1, saveFile.indexOf("\""));
            
            out.println(saveFile);
            int lastIndex = contentType.lastIndexOf("=");
            String boundary = contentType.substring(lastIndex + 1, contentType.length());
            int pos;
            pos = file.indexOf("filename=\"");
            pos = file.indexOf("\n", pos) + 1;
            pos = file.indexOf("\n", pos) + 1;
            pos = file.indexOf("\n", pos) + 1;
            int boundaryLocation = file.indexOf(boundary, pos) - 4;
            int startPos = ((file.substring(0, pos)).getBytes()).length;
            int endPos = ((file.substring(0, boundaryLocation)).getBytes()).length;
            File ff = new File(saveFile);
            FileOutputStream fileOut = new FileOutputStream(ff);
            fileOut.write(dataBytes, startPos, (endPos - startPos));
            fileOut.flush();
            fileOut.close();
            out.println("You have successfully upload the file:" + saveFile);
            BufferedReader br = null;
            try{
            	QuestionBank question = new QuestionBank();
            	QuestionBankManagement objQuestionBankManagement = new QuestionBankManagement();
	            //create BufferedReader to read csv file
	            br = new BufferedReader(new FileReader(saveFile));
	            String line = "";
	            StringTokenizer st = null;
	            int lineNumber = 0;
	            int tokenNumber = 0;
	            //read comma separated file line by line
	            line = br.readLine();
	            while ((line = br.readLine()) != null) {
	                lineNumber++;
	                //use comma as token separator
	                st = new StringTokenizer(line, ",");
	                while (st.hasMoreTokens()) {
	                    tokenNumber++;
	                    //Set csv values
	                    question.setCategoryId(st.nextToken());
	                    question.setLevelId(st.nextToken());
	                    question.setQuestion(st.nextToken());
	                    question.setOptionA(st.nextToken());
	                    question.setOptionB(st.nextToken());
	                    question.setOptionC(st.nextToken());
	                    question.setOptionD(st.nextToken());
	                    question.setCorrectAnswer(st.nextToken());
	                    if (tokenNumber >= 1) {
	                    	objQuestionBankManagement.addQuestion(question);
	                         //new org.cadet.admin.model.insertData().insertdata("0", in1.getQuestion_cat(), in1.getLid() ,in1.getQuestion(), in1.getOptionA(), in1.getOptionB(), in1.getOptionC(), in1.getOptionD(), in1.getAnswer());
	                    }
	                }
	                out.println("<h3>lenNumber=" + lineNumber + "</h3>");
	                out.println("<h3>tokenNumber=" + tokenNumber + "</h3>");
	                //reset token number
	                tokenNumber = 0;
	            }
	            status=true;
	        } 
            catch (Exception e) {
	            out.println("CSV file cannot be read : " + e.getMessage());
	            status = false;
            }
            finally{
            	br.close();
            }
            out.println("<h1>Status: " + status + "</h1>");
            if(status)
            	request.setAttribute("status", "success");
            else
            	request.setAttribute("status", "fail");
            out.println("</body>");
            out.println("</html>");
			RequestDispatcher rd = request.getRequestDispatcher("/admin/questionbank/UploadByFile.jsp");
			rd.forward(request, response);
        }
    }
}
