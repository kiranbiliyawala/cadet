package org.cadet.util.model;

public class Constants {

    public static final String logfile = "logfile.log";

    public static final class Captcha {

	public static final String VerifyUrl = "http://www.google.com/recaptcha/api/verify";
	public static final String publicKey = "6LeOsdcSAAAAAOHjj6wziFhEdSzLzxCQDjPbYPXo";
	public static final String privateKey = "6LeOsdcSAAAAAP3OwUfT_kxNXh5r9oJwvG3H4uSS";

    }

    public static final class DB {
	public static final String driver = "com.mysql.jdbc.Driver";
	public static final String dburl = "jdbc:mysql://localhost:3306/cadet";
	public static final String username = "root";
	public static final String password = "";
	public static boolean hasPassword = true;
    }

    public static final class sqlCommands{

	/* Jay Queries */

	public static final String retrivePasswordAdmin = "SELECT Password FROM admin WHERE AUserName = "; 
	public static final String retrivePasswordClient = "SELECT Password FROM candidate WHERE CUserName = ";
	public static final String isClientAvailable = "SELECT COUNT(DISTINCT CUserName) AS count FROM candidate WHERE CUserName = ";
	public static final String AddClient = "INSERT INTO candidate (CUserName, Password, Name, Contact, CandidateCategoryName, Verified) VALUES (?, ?, ?, ?, ?, ?)";
	public static final String AddAdmin = "INSERT INTO admin (AUserName, Password, Name, Contact, Verified) VALUES (?, ?, ?, ?, ?)";
	public static final String VerifyClient = "UPDATE candidate SET Verified='Y' WHERE CUserName = ?";
	public static final String VerifyAdmin = "UPDATE admin SET Verified='Y' WHERE AUsername = ?";
	public static final String CheckClientVerification = "SELECT Verified FROM candidate WHERE CUserName = ?";
	public static final String CheckAdminVerification = "SELECT Verified FROM admin WHERE AUsername = ?";
	public static final String UpdateClientPassword = "UPDATE candidate SET Password=? WHERE CUserName = ?";
	public static final String hasCategory="SELECT COUNT(*) as count FROM candidatecategory WHERE CandidateCategoryName = ?";
	public static final String getUserCategories="SELECT CandidateCategoryName as Category FROM candidatecategory";
	public static final String getTestDurationNA="SELECT TestDuration as duration, TestName as name, TestDate as Date FROM test WHERE TestId=?";
	public static final String getTestNegativeNA="SELECT NegMark FROM test WHERE TestId=?";
	public static final String getTestDifficultyNA="SELECT LevelId as LID , Marks as Marks FROM levelmarks WHERE TestId=?";
	public static final String getTestQuestionsNA="SELECT Q.QuestionId AS \'QID\',(SELECT C.CategoryName FROM category C where C.CategoryId=Q.CategoryId) AS \'CNAME\' , Q.LevelId AS \'LID\', Q.Question AS \'Question\', Q.OptA AS \'OptionA\', Q.OptB \'OptionB\', Q.OptC AS \'OptionC\', Q.OptD AS \'OptionD\', Q.CorrectAnswer AS \'CorrectAnswer\' FROM questionbank Q WHERE Q.QuestionId in (SELECT T.QuestionId FROM testquestion T WHERE T.TestId=?)";
	public static final String test_Allowed_query1="select count(*) as allow from Result where CUserNAme=? and TestId=?";
	public static final String test_Allowed_query2="SELECT * FROM test WHERE Sysdate() BETWEEN StartTime AND EndTime AND TestId =?";
	public static final String submitAnswersNA="UPDATE `result` SET `Marks`=?,`Attempted`=?,`Correct`=? WHERE `CUserName`= ? AND `TestId`=?";
	public static final String getPasswordClient = "SELECT Password FROM candidate WHERE CUserName = ?";
	public static final String GetResult = "SELECT @rn:=@rn+1 AS Rank,result_CUserName, result_Marks, result_Attempted, result_Correct, result_Percentile FROM( SELECT result.`CUserName` AS result_CUserName, result.`Marks` AS result_Marks, result.`Attempted` AS result_Attempted, result.`Correct` AS result_Correct, (SELECT (result.Marks / MAX(a.Marks))*100 FROM result a WHERE a.TestId=?) AS result_Percentile FROM `result` result WHERE  result.`TestId` = ? ORDER BY result_Marks DESC) t1,(SELECT @rn:=0) t2";
	public static final String GetCompleteTests = "SELECT `TestId`, `TestType`, `TestName`, `TestDesc`, `TestDate`, `StartTime`, `EndTime`, `TestDuration`, `InitialDifficulty` FROM `test` WHERE `TestDate`<now()";
	

	/* Udit Queries */
	public static final String retriveTests = "SELECT * FROM test";
	public static final String retriveCategories = "SELECT * FROM category WHERE CategoryId NOT IN (SELECT CategoryId FROM testcategory WHERE TestId = ?)";
	public static final String retriveLastInsertID = "SELECT last_insert_id()";
	public static final String retriveTestCategoryDetails = "SELECT category.CategoryId,CategoryName,TimePerCategory,QuestionPerCategory FROM test, category, testcategory WHERE test.TestId = ? AND test.TestId = testcategory.TestId AND testcategory.CategoryId = category.CategoryId";
	public static final String retriveTestDetails = "SELECT * FROM test WHERE TestId = ?";
	public static final String retriveSpecificTestCategoryDetails = "SELECT test.TestId,category.CategoryId,CategoryName,TimePerCategory,QuestionPerCategory FROM test, category, testcategory WHERE test.TestId = ? AND category.CategoryId = ? AND test.TestId = testcategory.TestId AND testcategory.CategoryId = category.CategoryId";
	public static final String retriveLevelMarks = "SELECT * FROM levelmarks WHERE TestId = ?";
	public static final String retriveTestCandidateCategories = "SELECT CandidateCategoryName FROM testcandidatecategory WHERE TestId = ?";
	public static final String retriveTestCatQstn = "SELECT qb.QuestionId, qb.LevelId, qb.Question, qb.OptA, qb.OptB, qb.OptC, qb.OptD, qb.CorrectAnswer FROM questionbank qb JOIN category c ON qb.CategoryId=c.CategoryId JOIN testquestion tq ON qb.QuestionId=tq.QuestionId WHERE tq.TestId = ? AND c.CategoryId = ?";
	public static final String retriveCategoryName = "SELECT categoryName FROM category WHERE CategoryId = ?";
	public static final String retriveUserCategories = getUserCategories+" WHERE CandidateCategoryName NOT IN (SELECT CandidateCategoryName FROM testcandidatecategory WHERE TestId = ?)";
	public static final String addTest = "INSERT INTO test (TestName,TestType,TestDesc,TestDate,StartTime,EndTime,TestDuration,InitialDifficulty,NegMark) VALUES (?,?,?,STR_TO_DATE(?,'%Y-%m-%d'),STR_TO_DATE(?,'%Y-%m-%d %k:%i'),STR_TO_DATE(?,'%Y-%m-%d %k:%i'),?,?,?)";
	public static final String addCategory = "INSERT INTO category (CategoryName,CategoryDescription) VALUES (?,?)";
	public static final String addCategoryToTest = "INSERT INTO testcategory (TestId,CategoryId,TimePerCategory,QuestionPerCategory) VALUES (?,?,?,?)";
	public static final String addQstnToTest = "INSERT INTO testquestion (TestId,QuestionId) VALUES (?,?)";
	public static final String addLevelMarks = "INSERT INTO levelmarks (LevelId,TestId,Marks) VALUES (?,?,?)";
	public static final String addUserCategoryToTest = "INSERT INTO testcandidatecategory (TestId,CandidateCategoryName) VALUES (?,?)";
	public static final String updateTestCategoryDetails = "UPDATE testcategory SET TimePerCategory = ? , QuestionPerCategory = ? WHERE TestId = ? AND CategoryId = ?";
	public static final String updateTestTimeSettings = "UPDATE test SET TestDate = STR_TO_DATE(?,'%d-%m-%Y'), StartTime = STR_TO_DATE(?,'%d-%m-%Y %k:%i'), EndTime = STR_TO_DATE(?,'%d-%m-%Y %k:%i') WHERE TestId = ?";
	public static final String updateInitDiff = "UPDATE test SET InitialDifficulty = ? WHERE TestId = ?";
	public static final String updateNegMark = "UPDATE test SET NegMark = ? WHERE TestId = ?";

	public static final String CreateTestLevels = "INSERT INTO `levelmarks`(`LevelId`, `TestId`, `Marks`) VALUES (?,?,0)";
	public static final String updateLevelMark = "UPDATE levelmarks SET Marks = ? WHERE TestId = ? AND LevelId = ?";
	public static final String updateTestDuration = "UPDATE test t SET t.TestDuration = (SELECT SUM(tc.TimePerCategory) FROM testcategory tc WHERE tc.TestId=t.TestId) WHERE t.TestId = ?";
	public static final String updateTestDurationOnDeleteCategory = "UPDATE test SET TestDuration = TestDuration - ( SELECT TimePerCategory FROM testcategory WHERE TestId = ? AND CategoryId = ? ) WHERE TestId = ?";
	public static final String deleteTest = "DELETE FROM test WHERE TestId = ?";
	public static final String removeCategory = "DELETE FROM testcategory WHERE TestId = ? AND CategoryId = ?";
	public static final String removeCategoryQstnFromTest = "DELETE FROM testquestion WHERE TestId = ? AND QuestionId IN (SELECT QuestionId FROM questionbank WHERE CategoryId = ?)";
	public static final String removeTestCandidateCategory = "DELETE FROM testcandidatecategory WHERE TestId = ? AND CandidateCategoryName = ?";
	public static final String removeQuestionFromTest = "DELETE FROM testquestion WHERE TestId = ? AND QuestionId = ?";


	/* Rajan Queries */

	public static final String getDashboardTests = "SELECT DISTINCT(tcc.TestId),t.TestType, t.TestName, t.TestDate, t.TestDuration, r.Marks as marks, r.Attempted as attempt, r.Correct as correct FROM Test t, testcandidatecategory tcc, candidate c, result r WHERE t.TestId = tcc.TestId and TIMEDIFF(t.EndTime, SysDate())>=0 and DATEDIFF(t.StartTime, SysDate())>=0 and tcc.CandidateCategoryName = ? and c.CandidateCategoryName=tcc.CandidateCategoryName and r.CUserName = c.CUserName and r.TestId = tcc.TestId and r.Marks=0.0 and r.Attempted=0 and r.Correct=0 and c.CUserName = ?";
	public static final String getCandidateCategory = "SELECT CandidateCategoryName from candidate where CUserName=?";

	/* Shailee Queries */
	public static final String getNotRegisterTestForUserSql = "select distinct a.TestName, a.TestDate, a.TestDuration, a.testid from test a, testcandidatecategory c  where DATEDIFF(a.StartTime, Sysdate())>=0 and c.CandidateCategoryName =? and a.TestId = c.TestId and a.TestId not in (select TestId from result where CUsername=?) order by TestDate;";
	public static final String getViewAllTestForUserSql = "select distinct a.TestName, a.TestDate, a.TestDuration, " + " a.testid,b.marks,b.attempted,b.correct from test a, result b, testcandidatecategory c  where " + " c.CandidateCategoryName = ? and a.TestId = c.TestId and a.TestId = b.TestId and b.CUserName = ? order by TestDate;";
	public static final String setRegisterTestForUserSql = "insert into result (CUserName, TestId, Marks, Attempted, Correct) values (?, ?, 0.0, 0, 0)";
	public static final String getAllUserCategoriesSql = "Select CandidateCategoryName FROM candidatecategory";


	/* Sadhana Queries */
	public static final String getCandidateProfileDataSql = "Select CUserName, Name, Contact, CandidateCategoryName FROM candidate where CUserName=?";
	public static final String updateCandidateProfileDataSql = "Update Candidate SET Name=?,  Contact=? ,CandidateCategoryName=? WHERE CUserName=?";
	public static final String updateCandidatePasswordSql = "Update Candidate SET Password =? where cusername=? ";
	public static final String addUserCategoriesSql = "Insert INTO CandidateCategory Values (?)";
	public static final String deleteCandidateCategorySql = "DELETE FROM candidatecategory WHERE CandidateCategoryName = ?";

	/* Kiran Queries */
	public static final String AddQuestionCategory = "INSERT INTO category (CategoryName, CategoryDescription, Ausername) VALUES(?,?,?)";
	public static final String UpdateCategory = "UPDATE category SET CategoryName = ?, CategoryDescription = ? , Ausername = ? WHERE CategoryId = ?";
	public static final String RemoveCategory = "DELETE FROM category WHERE CategoryId=?";
	public static final String RetrieveCategories = "SELECT * FROM category";
	public static final String RetrieveCategoryByCategoryID = "SELECT CategoryName FROM category WHERE CategoryId = ?";
	public static final String AddQuestion = "INSERT INTO questionbank(CategoryId, LevelId, Question, OptA, OptB, OptC, OptD, CorrectAnswer) VALUES (?,?,?,?,?,?,?,?)";
	public static final String UpdateQuestion = "UPDATE questionbank SET CategoryId = ?, LevelId = ?, Question = ?, OptA = ?, OptB = ?, OptC = ?, OptD = ?, CorrectAnswer = ? WHERE QuestionId= ?";
	public static final String RemoveQuestion = "DELETE from questionbank WHERE QuestionId = ?";
	public static final String RetrieveAllQuestion = "SELECT * FROM questionbank";
	public static final String RetrieveCategorywiseQuestion = "SELECT * FROM questionbank WHERE CategoryId = ?";
	public static final String RetrieveQuestionByQuestionID = "SELECT * FROM questionbank WHERE QuestionId = ?";

	/* Udit Single Query*/

	public static final String retriveCategoryQuestions = RetrieveCategorywiseQuestion +" AND QuestionId NOT IN (SELECT QuestionId FROM testquestion WHERE TestId = ?)";

	/* Komal Queries */
	
	public static final String GetAdminDashboardTests = "SELECT TestId,TestName,TestDate,TestDuration FROM test where TestDate>=now()";
	
	/* Varun Queries */

	public static final String getTest="SELECT * from test where testId = ?";
	public static final String getTestDetails="SELECT TestName, TestDesc, TestDate, InitialDifficulty FROM test WHERE TestId = ?";
	public static final String getQuestionCountAndCategoryOfTest="SELECT tc.CategoryId, c.CategoryName, tc.TimePerCategory, tc.QuestionPerCategory FROM testcategory tc JOIN category c on tc.CategoryId = c.CategoryId WHERE testId = ?";
	public static final String fetchNextQuestion1="SELECT q.QuestionId, q.LevelId, c.CategoryName, q.Question, q.OptA, q.OptB, q.OptC, q.OptD, q.CorrectAnswer, q.LevelId FROM questionbank q,category c, testcategory tc WHERE q.CategoryId = tc.CategoryId AND c.CategoryId = tc.CategoryId AND tc.TestID = ? AND tc.CategoryId = ? AND q.LevelId =? AND q.QuestionId NOT IN (";
	public static final String fetchNextQuestion2=") ORDER BY RAND() LIMIT 1";
	public static final String saveResult="UPDATE result SET Marks=?, Attempted=?, Correct=? WHERE CUserName=? AND TestId=?";
  }
	
	public static final class email{
		public static final String SMTP_HOST_NAME = "smtp.gmail.com";
		public static final String EmailUsername = "cadet.daiict@gmail.com";
		public static final String EmailPassword = "cadet.daiict";
		public static final Integer SMTP_PORT = 465;
		public static final String SMTP_SSL = "true";
		public static final String SMTP_AUTH = "true";
		public static final String EmailMessage = "Please Click following link to verify your email address http://localhost:8080/cadet/verifyEmail?";
		public static final String EmailSubject = "[EMAIL Verification] CADET";
		public static final String EmailRegex = "^(.+)@(.+)$";
		public static final String ForgotEmailMessage = "Please Click following link to verify your email address http://localhost:8080/cadet/ForgotPassword?";
		
    }
	
    public static final class adaptive{
	public static final Double MIN_DIFFICULTY=1.0;
	public static final Double MAX_DIFFICULTY=10.0;
	public static final Double DIFFERENCE_BETWEEN_TWO_DIFFICULTIES=1.0;
    }
}
