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
		public static final String retrivePasswordAdmin = "SELECT Password FROM admin WHERE AUserName = "; 
		public static final String retrivePasswordClient = "SELECT Password FROM candidate WHERE CUserName = ";
		public static final String retriveTests = "SELECT * FROM test";
		public static final String retriveLastInsertID = "SELECT last_insert_id()";
		public static final String retriveTestCategoryDetails = "SELECT category.CategoryId,CategoryName,TimePerCategory,QuestionPerCategory FROM test, category, testcategory WHERE test.TestId = ? AND test.TestId = testcategory.TestId AND testcategory.CategoryId = category.CategoryId";
		public static final String retriveTestDetails = "SELECT * FROM test WHERE TestId = ?";
		public static final String isClientAvailable = "SELECT COUNT(DISTINCT CUserName) AS count FROM candidate WHERE CUserName = ";
		public static final String AddClient = "INSERT INTO candidate (CUserName, Password, Name, Contact, CandidateCategoryName, Verified) VALUES (?, ?, ?, ?, ?, ?)";
		public static final String AddAdmin = "INSERT INTO admin (AUserName, Password, Name, Contact, Verified) VALUES (?, ?, ?, ?, ?)";
		public static final String AddTest = "INSERT INTO test (TestName,TestType,TestDesc) VALUES (?,?,?)";
		public static final String VerifyClient = "UPDATE candidate SET Verified='Y' WHERE CUserName = ?";
		public static final String VerifyAdmin = "UPDATE admin SET Verified='Y' WHERE Username = ?";
		public static final String CheckClientVerification = "SELECT Verified FROM candidate WHERE CUserName = ?";
		public static final String CheckAdminVerification = "SELECT Verified FROM admin WHERE Username = ?";
		public static final String UpdateClientPassword = "UPDATE candidate SET Password=? WHERE CUserName = ?";
		public static final String UpdateTestCategoryDetails = "UPDATE testcategory SET TimePerCategory = ? , QuestionPerCategory = ? WHERE TestId = ? AND CategoryId = ?";
		public static final String deleteTest = "DELETE from test WHERE TestId = ?";

		public static final String getTest="SELECT * from test where testId = ?";
		public static final String getQuestionCountOfCategoryOfTest="SELECT CategoryId, QuestionPerCategory FROM test WHERE testId = ?";
		public static final String fetchNextQuestion1="SELECT q.QuestionId, q.LevelId, c.CategoryName, q.Question, q.OptA, q.OptB, q.OptC, q.OptD, q.CorrectAnswer, l.Marks, t.NegMark FROM testquestion t JOIN questionbank q ON t.QuestionId=q.QuestionId JOIN levelmarks l ON t.TestId=l.TestId JOIN category c ON q.CategoryId=c.CategoryId WHERE q.LevelId=l.LevelId AND t.TestId= ? AND q.CategoryId= ? AND l.LevelId= ? AND q.QuestionId NOT IN (";
		public static final String fetchNextQuestion2=") ORDER BY RAND() LIMIT 1";
		public static final String saveResult="INSERT INTO result VALUES(?,?,?,?,?)";
		
		public static String hasCategory="SELECT COUNT(*) as count FROM candidatecategory WHERE CandidateCategoryName = ?";
		public static String getUserCategories="SELECT CandidateCategoryName as Category FROM candidatecategory";
		public static String getTestDurationNA="SELECT TestDuration as duration, TestName as name, TestDate as Date FROM test WHERE TestId=?";
		public static String getTestNegativeNA="SELECT NegMark as NegativeMarks FROM testquestion WHERE TestId=?";
		public static String getTestDifficultyNA="SELECT LevelId as LID , Marks as Marks FROM levelmarks WHERE TestId=?";
		public static String getTestQuestionsNA="SELECT Q.QuestionId AS \'QID\',(SELECT C.CategoryName FROM category C where C.CategoryId=Q.CategoryId) AS \'CNAME\' , Q.LevelId AS \'LID\', Q.Question AS \'Question\', Q.OptA AS \'OptionA\', Q.OptB \'OptionB\', Q.OptC AS \'OptionC\', Q.OptD AS \'OptionD\', Q.CorrectAnswer AS \'CorrectAnswer\' FROM questionbank Q WHERE Q.QuestionId in (SELECT T.QuesitonId FROM testquestion T WHERE T.TestId=?)";
		
		public static String getDashboardTests = "SELECT DISTINCT(tcc.TestId), t.TestName, t.TestDate, t.TestDuration FROM Test t, testcandidatecategory tcc, candidate c WHERE t.TestId = tcc.TestId and DATEDIFF(t.TestDate, CURDATE())>=0 and tcc.CandidateCategoryName = ? and tcc.CandidateCategoryName=c.CandidateCategoryName";
		public static String getCandidateCategory = "SELECT CandidateCategoryName from candidate where CUserName=?";
		
		public static String getNotRegisterTestForUserSql = "select distinct a.TestName, a.TestDate, a.TestDuration, " + " a.testid from test a, testcandidatecategory c  where a.TestDate >= Sysdate() " + " and c.CandidateCategoryName = ? and a.TestId = c.TestId and a.TestId not in (select TestId from result where CUsername=?) order by TestDate;";
		public static String getViewAllTestForUserSql = "select distinct a.TestName, a.TestDate, a.TestDuration, " + " a.testid,b.marks,b.attempted,b.correct from test a, result b, testcandidatecategory c  where " + " c.CandidateCategoryName = ? and a.TestId = c.TestId and a.TestId = b.TestId and b.CUserName = ? order by TestDate;";
		public static String setRegisterTestForUserSql = "insert into result (CUserName, TestId, Marks, Attempted, Correct) values (?, ?, 0, 0, 0)";
		
		public static String test_Allowed_query1="select count(*) as allow from Result where CUserNAme=? and TestId=?";
		public static String test_Allowed_query2="SELECT * FROM test WHERE ? BETWEEN StartTime AND EndTime AND TestId =?";
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
	
	public static final class adaptive{
		public static final Double MIN_DIFFICULTY=1.0;
		public static final Double MAX_DIFFICULTY=10.0;
		public static final Double DIFFERENCE_BETWEEN_TWO_DIFFICULTIES=1.0;
	}
}
