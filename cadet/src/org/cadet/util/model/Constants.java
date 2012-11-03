package org.cadet.util.model;

public class Constants {

	public static final String logfile = "logfile.log";
	
	
	public static final class Captcha{
		
		public static String VerifyUrl = "http://www.google.com/recaptcha/api/verify";
		public static String publicKey = "6LeOsdcSAAAAAOHjj6wziFhEdSzLzxCQDjPbYPXo";
		public static String privateKey = "6LeOsdcSAAAAAP3OwUfT_kxNXh5r9oJwvG3H4uSS";
		
	}
	
	
	public static final class DB{
		public static final String driver = "com.mysql.jdbc.Driver";
		public static final String dburl = "jdbc:mysql://localhost:3306/cadet";
		public static final String username = "root";
		public static final String password = "";
		public static boolean hasPassword = true;	
	}

	public static final class sqlCommands{
		public static final String retrivePasswordAdmin = "SELECT Password FROM admin WHERE Username = "; 
		public static final String retrivePasswordClient = "SELECT Password FROM candidate WHERE CUserName = ";
		public static final String retriveTests = "SELECT * FROM test";
		public static final String retriveLastInsertID = "SELECT last_insert_id()";
		public static final String retriveTestCategoryDetails = "SELECT CategoryName,TimePerCategory,QuestionPerCategory FROM test, category, testcategory WHERE test.TestId = ? AND test.TestId = testcategory.TestId AND testcategory.CategoryId = category.CategoryId";
		public static final String retriveTestName = "SELECT TestName FROM test WHERE TestId = ?";
		public static final String isClientAvailable = "SELECT COUNT(DISTINCT CUserName) AS count FROM candidate WHERE CUserName = ";
		public static final String AddClient = "INSERT INTO candidate (CUserName, Password, Name, Contact, CandidateCategoryName, Verified) VALUES (?, ?, ?, ?, ?, ?)";
		public static final String AddAdmin = "INSERT INTO admin (Username, Password, Name, Contact, Verified) VALUES (?, ?, ?, ?, ?)";
		public static final String AddTest = "INSERT INTO test (TestName,TestType,TestDesc) VALUES (?,?,?)";
		public static final String VerifyClient = "UPDATE candidate SET Verified='Y' WHERE CUserName = ?";
		public static final String VerifyAdmin = "UPDATE admin SET Verified='Y' WHERE Username = ?";
		public static final String CheckClientVerification = "SELECT Verified FROM candidate WHERE CUserName = ?";
		public static final String CheckAdminVerification = "SELECT Verified FROM admin WHERE Username = ?";
		public static final String UpdateClientPassword = "UPDATE candidate SET Password=? WHERE CUserName = ?";
		public static final String deleteTest = "DELETE from test WHERE TestId = ?";
		
		public static final String getTest="SELECT * from test where testId = ?";
		public static String hasCategory="SELECT COUNT(*) as count FROM candidatecategory WHERE CandidateCategoryName = ?";
		public static String getUserCategories="SELECT CandidateCategoryName as Category FROM candidatecategory";
		public static String getTestDurationNA="";
		public static String getTestNegativeNA="";
		public static String getTestDifficultyNA="";
		public static String getTestQuestionsNA;
		
		public static String getDashboardTests = "SELECT t.TestName, t.TestDate, t.TestDuration FROM Test t, testcandidatecategory tcc WHERE tcc.candidatecategoryname = ? and  t.TestId = tcc.TestId and DATEDIFF(t.TestDate, CURDATE())>=0";
	}
	
	public static final class email{
		public static final String SMTP_HOST_NAME = "smtp.gmail.com";
		public static final String EmailUsername = "cadet.daiict@gmail.com";
		public static final String EmailPassword = "cadet.daiict";
		public static final Integer SMTP_PORT = 465;
		public static final String SMTP_SSL = "true";
		public static final String SMTP_AUTH = "true";
		public static final String EmailMessage = "Please Click following link to verify your email address https://localhost:9090/cadet/verifyEmail?";
		public static final String EmailSubject = "[EMAIL Verification] CADET";
		public static final String EmailRegex = "^(.+)@(.+)$";
		
	}
	
}
