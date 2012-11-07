package org.cadet.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cadet.admin.bean.BeanTest;
import org.cadet.admin.bean.BeanTestCategory;
import org.cadet.admin.model.TestDbTransactions;
import org.cadet.util.model.DatabaseConnection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class GetTests
 */
@WebServlet("/admin/test/TestManagement")
public class TestManagement extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestManagement() {
	super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	response.sendError(404, "No Get Request Allowed for this Page");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	String requestType = request.getParameter("requestType");
	PrintWriter out = response.getWriter();
	JSONObject data = null;

	if (requestType.equals("getAllTests")) {

	    DatabaseConnection dbConn = DatabaseConnection.getInstance();
	    Connection dbConnection = dbConn.getDbConnection();

	    try {

		data = TestDbTransactions.getAllTests(dbConnection);
		data.put("result", true);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;

	    } catch (SQLException e) {
		e.printStackTrace();

		data = new JSONObject();
		try {
		    data.put("result", "DatabaseError");
		} catch (JSONException e1) {
		    e1.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;
	    } catch (Exception e) {

		e.printStackTrace();

		data = new JSONObject();
		try {
		    data.put("result", "ServerException");
		} catch (JSONException e1) {
		    e1.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;
	    }
	}

	else if(requestType.equals("getAllCategories")) {

	    DatabaseConnection dbConn = DatabaseConnection.getInstance();
	    Connection dbConnection = dbConn.getDbConnection();

	    try {

		data = TestDbTransactions.getAllCategories(dbConnection);
		data.put("result", true);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;

	    } catch (SQLException e) {
		e.printStackTrace();

		data = new JSONObject();
		try {
		    data.put("result", "DatabaseError");
		} catch (JSONException e1) {
		    e1.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;
	    } catch (Exception e) {

		e.printStackTrace();

		data = new JSONObject();
		try {
		    data.put("result", "ServerException");
		} catch (JSONException e1) {
		    e1.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;
	    }
	}

	else if(requestType.equals("addCategory")) {

	    int testId = Integer.parseInt(request.getParameter("testId"));
	    int categoryId = Integer.parseInt(request.getParameter("categoryId"));

	    DatabaseConnection dbConn = DatabaseConnection.getInstance();
	    Connection dbConnection = dbConn.getDbConnection();

	    try {

		TestDbTransactions.addCategoryToTest(dbConnection,testId,categoryId);
		data = new JSONObject().put("category", TestDbTransactions.getSpecificTestCategoryDetails(dbConnection, testId,categoryId));
		data.put("result", true);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;
	    } catch (SQLException e) {
		e.printStackTrace();

		data = new JSONObject();
		try {
		    data.put("result", "DatabaseError");
		} catch (JSONException e1) {
		    e1.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;
	    } catch (Exception e) {

		e.printStackTrace();

		data = new JSONObject();
		try {
		    data.put("result", "ServerException");
		} catch (JSONException e1) {
		    e1.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;
	    }
	}

	else if(requestType.equals("removeCategory")) {

	    int testId = Integer.parseInt(request.getParameter("testId"));
	    JSONArray delCatList = null;

	    DatabaseConnection dbConn = DatabaseConnection.getInstance();
	    Connection dbConnection = dbConn.getDbConnection();

	    try {
		delCatList = new JSONArray(request.getParameter("delCatList"));

		for(int i=0;i<delCatList.length();i++) {
		    int categoryId = Integer.parseInt(delCatList.getJSONObject(i).getString("checkboxValue").split("chk")[1]);

		    TestDbTransactions.removeCategory(dbConnection,testId,categoryId);
		}

		data = new JSONObject();
		data.put("result", true);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;

	    } catch (SQLException e) {
		e.printStackTrace();

		data = new JSONObject();
		try {
		    data.put("result", "DatabaseError");
		} catch (JSONException e1) {
		    e1.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;
	    } catch (Exception e) {

		e.printStackTrace();

		data = new JSONObject();
		try {
		    data.put("result", "ServerException");
		} catch (JSONException e1) {
		    e1.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;
	    }
	}

	else if(requestType.equals("newCategory")) {

	    String categoryName = request.getParameter("categoryName");

	    DatabaseConnection dbConn = DatabaseConnection.getInstance();
	    Connection dbConnection = dbConn.getDbConnection();

	    try {

		TestDbTransactions.addCategory(dbConnection,categoryName);

		data = new JSONObject();
		data.put("result", true);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;
	    } catch (SQLException e) {
		e.printStackTrace();

		data = new JSONObject();
		try {
		    data.put("result", "DatabaseError");
		} catch (JSONException e1) {
		    e1.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;
	    } catch (Exception e) {

		e.printStackTrace();

		data = new JSONObject();
		try {
		    data.put("result", "ServerException");
		} catch (JSONException e1) {
		    e1.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;
	    }
	}

	else if (requestType.equals("createTest")) {

	    String testName = request.getParameter("txtTestName");
	    String testType = request.getParameter("optTestType");
	    String testDesc = request.getParameter("taTestDesc");

	    DatabaseConnection dbConn = DatabaseConnection.getInstance();
	    Connection dbConnection = dbConn.getDbConnection();
	    int testId;

	    try {

		TestDbTransactions.createTest(dbConnection, testName, testType, testDesc);

		testId = TestDbTransactions.getLastInsertID(dbConnection);

	    } catch (SQLException e) {
		e.printStackTrace();
		response.sendRedirect("../DatabaseError.html");
		return;
	    } catch (Exception e) {

		e.printStackTrace();
		response.sendRedirect("../ServerException.html");
		return;
	    }

	    /* AFTER TEST CREATION -- Redirection to Test Page */

	    try {
		if (testId != -1) {

		    ArrayList<BeanTestCategory> categoryDetails = TestDbTransactions.getTestCategoryDetails(dbConnection, testId);

		    request.setAttribute("testId", testId);
		    request.setAttribute("testName", testName);
		    request.setAttribute("testType", testType);
		    request.setAttribute("testDuration", 0);
		    request.setAttribute("categoryDetails", categoryDetails);

		    RequestDispatcher rd = request.getRequestDispatcher("testPage.jsp");
		    rd.forward(request, response);
		} else {
		    response.sendRedirect("../ServerException.html");
		    return;
		}
	    } catch (SQLException e) {

		e.printStackTrace();
		response.sendRedirect("../DatabaseError.html");
		return;
	    } catch (Exception e) {

		e.printStackTrace();
		response.sendRedirect("../ServerException.html");
		return;
	    }
	}

	else if (requestType.equals("editTest")) {

	    DatabaseConnection dbConn = DatabaseConnection.getInstance();
	    Connection dbConnection = dbConn.getDbConnection();
	    int testId;

	    try {

		testId = Integer.parseInt(request.getParameter("testId"));

		ArrayList<BeanTestCategory> categoryDetails = TestDbTransactions.getTestCategoryDetails(dbConnection, testId);
		BeanTest test = TestDbTransactions.getTestDetails(dbConnection, testId);

		request.setAttribute("testId", testId);
		request.setAttribute("testName", test.getTestName());
		request.setAttribute("testType", test.getTestType());
		request.setAttribute("testDuration", test.getTestDuration());
		request.setAttribute("categoryDetails", categoryDetails);

		RequestDispatcher rd = request.getRequestDispatcher("testPage.jsp");
		rd.forward(request, response);
	    } catch (SQLException e) {

		e.printStackTrace();
		response.sendRedirect("../DatabaseError.html");
		return;
	    } catch (Exception e) {

		e.printStackTrace();
		response.sendRedirect("../ServerException.html");
		return;
	    }
	}

	else if(requestType.equals("saveTest")) {

	    int testId = Integer.parseInt(request.getParameter("testId"));
	    String testType = request.getParameter("testType");
	    int testDuration = Integer.parseInt(request.getParameter("testDuration"));

	    DatabaseConnection dbConn = DatabaseConnection.getInstance();
	    Connection dbConnection = dbConn.getDbConnection();

	    if(testType.equals("Adaptive")) {

		try{

		    ArrayList<BeanTestCategory> temp = TestDbTransactions.getTestCategoryDetails(dbConnection, testId);
		    BeanTestCategory categoryDetails[] = temp.toArray(new BeanTestCategory[temp.size()]);
        
		    for(int i=0;i<categoryDetails.length;i++) {

    		    	int categoryId = categoryDetails[i].getCategoryId();
    		    	int questionsPerCategory = Integer.parseInt(request.getParameter("txtNoQueCat"+categoryId));
    		    	int timePerCategory = Integer.parseInt(request.getParameter("txtTimeCat"+categoryId));

    		    	TestDbTransactions.setTestCategoryDetails(dbConnection,testId,categoryId,questionsPerCategory,timePerCategory);
    		    	categoryDetails[i].setQuestionsPerCategory(questionsPerCategory);
    		    	categoryDetails[i].setTimePerCategory(timePerCategory);
		    }

		    TestDbTransactions.setTestDuration(dbConnection,testId,testDuration);

		    request.setAttribute("testId", testId);
		    request.setAttribute("testName", request.getParameter("testName"));
		    request.setAttribute("testType", testType);
		    request.setAttribute("testDuration",testDuration);
		    request.setAttribute("categoryDetails",categoryDetails);
		    request.setAttribute("result", true);

		    RequestDispatcher rd = request.getRequestDispatcher("testPage.jsp");
		    rd.include(request, response);
		} catch (SQLException e) {

		    e.printStackTrace();
		    response.sendRedirect("../DatabaseError.html");
		    return;
		} catch (Exception e) {

		    e.printStackTrace();
		    response.sendRedirect("../ServerException.html");
		    return;
    	    	}
	    } else if(testType.equals("Non-Adaptive")) {

		try{

		    ArrayList<BeanTestCategory> temp = TestDbTransactions.getTestCategoryDetails(dbConnection, testId);
		    BeanTestCategory categoryDetails[] = temp.toArray(new BeanTestCategory[temp.size()]);
        
		    for(int i=0;i<categoryDetails.length;i++) {

    		    	int categoryId = categoryDetails[i].getCategoryId();
    		    	int timePerCategory = Integer.parseInt(request.getParameter("txtTimeCat"+categoryId));

    		    	TestDbTransactions.setTestCategoryDetails(dbConnection,testId,categoryId,0,timePerCategory);
    		    	categoryDetails[i].setTimePerCategory(timePerCategory);
		    }

		    TestDbTransactions.setTestDuration(dbConnection,testId,testDuration);

		    request.setAttribute("testId", testId);
		    request.setAttribute("testName", request.getParameter("testName"));
		    request.setAttribute("testType", testType);
		    request.setAttribute("testDuration",testDuration);
		    request.setAttribute("categoryDetails",categoryDetails);
		    request.setAttribute("result", true);

		    RequestDispatcher rd = request.getRequestDispatcher("testPage.jsp");
		    rd.include(request, response);
		} catch (SQLException e) {

		    e.printStackTrace();
		    response.sendRedirect("../DatabaseError.html");
		    return;
		} catch (Exception e) {

		    e.printStackTrace();
		    response.sendRedirect("../ServerException.html");
		    return;
    	    	}
	    }
	}

	else if (requestType.equals("deleteTest")) {

	    int testId = Integer.parseInt(request.getParameter("testId").split("delete")[1]);

	    DatabaseConnection dbConn = DatabaseConnection.getInstance();
	    Connection dbConnection = dbConn.getDbConnection();

	    try {

		TestDbTransactions.deleteTest(dbConnection, testId);

		data = new JSONObject();
		data.put("result", true);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;
	    } catch (SQLException e) {
		e.printStackTrace();

		data = new JSONObject();
		try {
		    data.put("result", "DatabaseError");
		} catch (JSONException e1) {
		    e1.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;
	    } catch (Exception e) {

		e.printStackTrace();

		data = new JSONObject();
		try {
		    data.put("result", "ServerException");
		} catch (JSONException e1) {
		    e1.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;
	    }
	}

	else if (requestType.equals("saveTimeSettings")) {

	    try {

		int testId = Integer.parseInt(request.getParameter("testId"));

		String strTestDate[] = request.getParameter("testDate").split("-");
		String strStartTime[] = request.getParameter("startTime").split(":");
		String strEndTime[] = request.getParameter("endTime").split(":");

		String testDate = strTestDate[0]+"-"+strTestDate[1]+"-"+strTestDate[2];
		String startTime = strTestDate[0]+"-"+strTestDate[1]+"-"+strTestDate[2]+" "+strStartTime[0]+":"+strStartTime[1];
		String endTime = strTestDate[0]+"-"+strTestDate[1]+"-"+strTestDate[2]+" "+strEndTime[0]+":"+strEndTime[1];

		DatabaseConnection dbConn = DatabaseConnection.getInstance();
		Connection dbConnection = dbConn.getDbConnection();

		TestDbTransactions.saveTimeSettings(dbConnection,testId,testDate,startTime,endTime);

		data = new JSONObject();
		data.put("result", true);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;
	    } catch (SQLException e) {
		e.printStackTrace();

		data = new JSONObject();
		try {
		    data.put("result", "DatabaseError");
		} catch (JSONException e1) {
		    e1.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;
	    } catch (Exception e) {
		e.printStackTrace();
		data = new JSONObject();
		try {
		    data.put("result", "ServerException");
		} catch (JSONException e1) {
		    e1.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;
	    }
	}

	else if (requestType.equals("getTestTimeSettings")) {

	    try {

		int testId = Integer.parseInt(request.getParameter("testId"));
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

		DatabaseConnection dbConn = DatabaseConnection.getInstance();
		Connection dbConnection = dbConn.getDbConnection();

		BeanTest test = TestDbTransactions.getTestDetails(dbConnection, testId);

		data = new JSONObject();
		data.put("testDate", dateFormat.format(test.getTestDate()));
		data.put("startTime", timeFormat.format(test.getStartTime()));
		data.put("endTime", timeFormat.format(test.getEndTime()));
		data.put("initDiff", test.getInitialDifficulty());
		data.put("negMark", test.getNegMark());
		data.put("result", true);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;
	    } catch (SQLException e) {
		e.printStackTrace();

		data = new JSONObject();
		try {
		    data.put("result", "DatabaseError");
		} catch (JSONException e1) {
		    e1.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;
	    } catch (Exception e) {
		e.printStackTrace();
		data = new JSONObject();
		try {
		    data.put("result", "ServerException");
		} catch (JSONException e1) {
		    e1.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;
	    }
	}

	else if(requestType.equals("saveInitDiff")) {

	    int testId = Integer.parseInt(request.getParameter("testId"));
	    int initDiff = Integer.parseInt(request.getParameter("initDiff"));

	    DatabaseConnection dbConn = DatabaseConnection.getInstance();
	    Connection dbConnection = dbConn.getDbConnection();

	    try {

		TestDbTransactions.saveInitDiff(dbConnection,initDiff,testId);

		data = new JSONObject();
		data.put("result", true);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;
	    } catch (SQLException e) {
		e.printStackTrace();

		data = new JSONObject();
		try {
		    data.put("result", "DatabaseError");
		} catch (JSONException e1) {
		    e1.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;
	    } catch (Exception e) {
		e.printStackTrace();
		data = new JSONObject();
		try {
		    data.put("result", "ServerException");
		} catch (JSONException e1) {
		    e1.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;
	    }
	}

	else if (requestType.equals("saveNegMark")) {

	    System.out.println(request.getParameter("testId"));
	    System.out.println(request.getParameter("decreaseMark").split(" %")[0]);

	    int testId = Integer.parseInt(request.getParameter("testId"));
	    String negMarkFlag = request.getParameter("negMarkFlag");
	    int decreaseMark = Integer.parseInt(request.getParameter("decreaseMark").split(" %")[0]);

	    if(negMarkFlag.equals("No")) {
		decreaseMark = 0;
	    }

	    DatabaseConnection dbConn = DatabaseConnection.getInstance();
	    Connection dbConnection = dbConn.getDbConnection();

	    try {
		TestDbTransactions.saveNegMark(dbConnection,testId,decreaseMark);

		data = new JSONObject();
		data.put("result", true);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;
	    } catch (SQLException e) {
		e.printStackTrace();

		data = new JSONObject();
		try {
		    data.put("result", "DatabaseError");
		} catch (JSONException e1) {
		    e1.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;
	    } catch (Exception e) {
		e.printStackTrace();
		data = new JSONObject();
		try {
		    data.put("result", "ServerException");
		} catch (JSONException e1) {
		    e1.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;
	    }
	}

	else if(requestType.equals("saveLevelMark")) {

	    try {
		int testId = Integer.parseInt(request.getParameter("testId"));
		JSONArray levelMarklist = new JSONArray(request.getParameter("levelMarkList"));

		DatabaseConnection dbConn = DatabaseConnection.getInstance();
		Connection dbConnection = dbConn.getDbConnection();

		for(int i =0;i<levelMarklist.length();i++) {
		    int levelId = Integer.parseInt(levelMarklist.getJSONObject(i).getString("inputName").split("txtLevel")[1]);
		    int levelMark = Integer.parseInt(levelMarklist.getJSONObject(i).getString("inputValue"));

		    TestDbTransactions.saveLevelMark(dbConnection,testId,levelId,levelMark);
		}

		System.out.println(levelMarklist);
		data = new JSONObject();
		data.put("result", true);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;

	    } catch (SQLException e) {
		e.printStackTrace();

		data = new JSONObject();
		try {
		    data.put("result", "DatabaseError");
		} catch (JSONException e1) {
		    e1.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;
	    } catch (Exception e) {

		e.printStackTrace();

		data = new JSONObject();
		try {
		    data.put("result", "ServerException");
		} catch (JSONException e1) {
		    e1.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;
	    }
	}

	else if(requestType.equals("getLevelMarks")) {

	    int testId = Integer.parseInt(request.getParameter("testId"));

	    DatabaseConnection dbConn = DatabaseConnection.getInstance();
	    Connection dbConnection = dbConn.getDbConnection();

	    try {

		data = TestDbTransactions.getLevelMarks(dbConnection,testId);
		data.put("result", true);
    
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;
	    } catch (SQLException e) {
		e.printStackTrace();

		data = new JSONObject();
		try {
		    data.put("result", "DatabaseError");
		} catch (JSONException e1) {
		    e1.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;
	    } catch (Exception e) {

		e.printStackTrace();

		data = new JSONObject();
		try {
		    data.put("result", "ServerException");
		} catch (JSONException e1) {
		    e1.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;
	    }
	}

	else if(requestType.equals("getTestCandCat")) {

	    int testId = Integer.parseInt(request.getParameter("testId"));

	    DatabaseConnection dbConn = DatabaseConnection.getInstance();
	    Connection dbConnection = dbConn.getDbConnection();

	    try {

		data = TestDbTransactions.getTestCandCat(dbConnection,testId);
		data.put("result", true);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;
	    } catch (SQLException e) {
		e.printStackTrace();

		data = new JSONObject();
		try {
		    data.put("result", "DatabaseError");
		} catch (JSONException e1) {
		    e1.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;
	    } catch (Exception e) {

		e.printStackTrace();

		data = new JSONObject();
		try {
		    data.put("result", "ServerException");
		} catch (JSONException e1) {
		    e1.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;
	    }
	}

	else if (requestType.equals("removeTestCandidateCategory")) {

	    int testId = Integer.parseInt(request.getParameter("testId"));
	    String candidateCategoryName = request.getParameter("candidateCategoryName").split("delete_")[1];

	    DatabaseConnection dbConn = DatabaseConnection.getInstance();
	    Connection dbConnection = dbConn.getDbConnection();

	    try {
	    	TestDbTransactions.removeTestCandidateCategory(dbConnection,testId,candidateCategoryName);
		data = new JSONObject();
		data.put("result", true);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;
	    } catch (SQLException e) {
		e.printStackTrace();

		data = new JSONObject();
		try {
		    data.put("result", "DatabaseError");
		} catch (JSONException e1) {
		    e1.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;
	    } catch (Exception e) {

		e.printStackTrace();

		data = new JSONObject();
		try {
		    data.put("result", "ServerException");
		} catch (JSONException e1) {
		    e1.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		return;
	    }
	}
    }
}
