package org.cadet.client.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.cadet.util.model.Constants;
import org.cadet.util.model.DatabaseConnection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NonAdaptiveTest {

	private JSONObject Categorized_Questions;
	private HashMap<String, String> Answers;
	private HashMap<String, String> CorrectAnswers;
	private JSONObject AttemptedQuestions;
	private HashMap<String,Integer> Question_difficulty;
	private HashMap<Integer,Integer> Difficulty_Marks;
	private Integer NegativeMarks;
	private Integer testid;
	private Integer totalquestions;
	private Integer attempted;
	private Integer Correct;
	private Double score;
	private long Test_Duration;
	private boolean lock;
	private String username;
	private Connection connection;
	public String[] test_details;
	
	public NonAdaptiveTest(Connection connection,int testid,String username) throws JSONException, SQLException {
	
		this.testid = testid;
		this.connection = connection;
		this.username = username;
		
		score = 0.0;
		totalquestions = 0;
		attempted = 0;
		Correct = 0;
		lock = false;
		
		fetchTestDetails();
		fetchQuestions();
		getDifficulty();
		getNegativeMarks();
		randomize();
	}

	

	private void fetchTestDetails() throws SQLException {
		PreparedStatement statement = connection.prepareStatement(Constants.sqlCommands.getTestDurationNA);
		statement.setInt(1, testid);
		ResultSet rs = statement.executeQuery();
		String name="";
		String date="";
		Integer test_hours=0;
		Integer test_minutes=0;
		while(rs.next()){
			Test_Duration = rs.getInt("duration");
			
			test_hours = (int) (Test_Duration/60);
			test_minutes = (int) (Test_Duration%60);
			
			name = rs.getString("Name");
			date = rs.getString("Date");
		}
		String details = name+","+date+","+test_hours+"::"+test_minutes;
		test_details = details.split(",");
	}

	private void randomize() throws JSONException {
		JSONObject category = Categorized_Questions;
		ArrayList<JSONObject> questions;
		
		JSONArray keys = category.names();
		Random r = new Random();
		for(int i=0;i<keys.length();i++){
			questions = (ArrayList<JSONObject>) category.get(keys.getString(i));
			Collections.shuffle(questions, r);
			JSONArray js = new JSONArray(questions);
			category.put(keys.getString(i), js);
		}
		Categorized_Questions = category;
	}

	private void getNegativeMarks() throws SQLException {
		PreparedStatement statement = connection.prepareStatement(Constants.sqlCommands.getTestNegativeNA);
		statement.setInt(1, testid);
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()){
			NegativeMarks = rs.getInt("NegativeMarks");
		}
		rs.close();
		statement.close();
	}

	private void getDifficulty() throws SQLException {
		
		PreparedStatement statement = connection.prepareStatement(Constants.sqlCommands.getTestDifficultyNA);
		statement.setInt(1, testid);
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()){
			int difficulty = rs.getInt("LID");
			int marks = rs.getInt("Marks");
			Difficulty_Marks.put(difficulty, marks);
		}

		rs.close();
		statement.close();
	}

	private void fetchQuestions() throws SQLException, JSONException {
		JSONObject category = new JSONObject();
		ArrayList<JSONObject> questions;
		
		PreparedStatement statement = connection.prepareStatement(Constants.sqlCommands.getTestQuestionsNA);
		statement.setInt(1, testid);
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()){
			String cat = rs.getString("CName");
			String QID = new Integer(rs.getInt("QID")).toString();
			String Question = rs.getString("Question");
			
			String OptionA = rs.getString("OptionA");
			String OptionB = rs.getString("OptionB");
			String OptionC = rs.getString("OptionC");
			String OptionD = rs.getString("OptionD");
			
			String CorrectAnswer = rs.getString("CorrectAnswer");
			
			Integer difficulty = rs.getInt("LID");
			
			JSONObject question = new JSONObject();
			
			question.put("QID", QID);
			question.put("QUESTION", Question);
			question.put("OPTIONA", OptionA);
			question.put("OPTIONB", OptionB);
			question.put("OPTIONC", OptionC);
			question.put("OPTIOND", OptionD);
			
			if(category.has(cat)){
				questions = (ArrayList<JSONObject>) category.get(cat);
				questions.add(question);
				category.put(cat, questions);
			}else{
				questions = new ArrayList<JSONObject>();
				questions.add(question);
				category.put(cat, questions);
			}
			
			
			CorrectAnswers.put(QID, CorrectAnswer);
			Question_difficulty.put(QID, difficulty);	
			
			totalquestions++;
		}
		Categorized_Questions = category;

		rs.close();
		statement.close();
	}
	
	public void Store_Answer(String category,String QNO,String QID,String Answer) throws JSONException{
		Answers.put(QID, Answer);
		AttemptQuestion(category,QNO);
	}
	
	private void AttemptQuestion(String category, String qNO) throws JSONException {
		JSONObject cat = AttemptedQuestions;
		JSONObject questions;
		
		if(cat.has(category)){
			questions = cat.getJSONObject(category);
			questions.put(qNO,true);
			cat.put(category, questions);
		}else{
			questions = new JSONObject();
			questions.put(qNO,true);
			cat.put(category, questions);
		}
		
		AttemptedQuestions = cat;
	}

	public void remove_Answer(String category,String QNO,String QID) throws JSONException{
			Answers.remove(QID);
			RemoveAttempt(category,QNO);
	}

	private void RemoveAttempt(String category, String qNO) throws JSONException {
		JSONObject cat = AttemptedQuestions;
		JSONObject questions;
		
		if(cat.has(category)){
			questions = cat.getJSONObject(category);
			questions.remove(qNO);
			cat.put(category, questions);
		}
		AttemptedQuestions = cat;
	}
	
	protected boolean isAttempted(String cat,String QNO) throws JSONException{
		boolean ret=false;;
		if(AttemptedQuestions.has(cat)){
			ret = AttemptedQuestions.getJSONObject(cat).has(QNO);
		}
		return ret;
	}
	
	public boolean process_Answers() throws SQLException{
		boolean ret = false;
		if(lock==true) 
			{
			return ret;
			}
		calculate_score();
		submit_result();
		lock = true;
		ret = true;
		return ret;
	}

	private void submit_result() throws SQLException {
		PreparedStatement statement = connection.prepareStatement(Constants.sqlCommands.getTestQuestionsNA);
		statement.setInt(1, testid);
		statement.setString(2, username);
		statement.setDouble(3, score);
		statement.executeUpdate();
		statement.close();
	}

	private void calculate_score() {
	Iterator<Entry<String, String>> iterator = Answers.entrySet().iterator();
	
	while(iterator.hasNext()){
		Entry<String, String> entry = iterator.next();
		String question = entry.getKey();
		String answer = entry.getValue();
		
		Integer marks = Difficulty_Marks.get(Question_difficulty.get(question));
		
		String CorrectAnswer = CorrectAnswers.get(question);
		
		attempted++;
		
		if(CorrectAnswer.equals(answer)){
			score += marks;
			Correct++;
		}else{
			score -= (marks*NegativeMarks)/100;
		}
	}
	
	}

	public String getQuestions(){
		return Categorized_Questions.toString();
	}
	
	protected JSONObject getQuestionDistribution() throws JSONException{
		JSONObject categories = new JSONObject();
		
		JSONArray keys = Categorized_Questions.names();
		for(int i=0;i<keys.length();i++){
		JSONArray questions =  Categorized_Questions.getJSONArray(keys.getString(i));
			categories.put(keys.getString(i), questions.length());
		}
		return categories;
	}
	
	public String getQuestions(String category,Integer qno) throws JSONException{
		String obj = Categorized_Questions.getJSONArray(category).getJSONObject(qno-1).toString();
		JSONObject jso = new JSONObject(obj);
		jso.put("ANSWER", get_answer(jso.getString("QID")));
		return jso.toString();
	}
	
	private String get_answer(String QID) {
		
		if(Answers.containsKey(QID)){
			return Answers.get(QID);
		}
		
		return "N";
	}



	public void startTest(){
		createScheduler(Test_Duration);
	}

	private void createScheduler(long delay){
		ScheduledExecutorService scheduler =  Executors.newSingleThreadScheduledExecutor();
		terminate term = new terminate(this);
		scheduler.schedule(term, delay, TimeUnit.MINUTES);
	}
	
	public class terminate implements Runnable{
		NonAdaptiveTest test;
		public terminate(NonAdaptiveTest test) {
			this.test = test;
		}
		
		@Override
		public void run() {
			try {
				test.process_Answers();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public static boolean isAllowed(int testid2, String username2) throws SQLException {
		boolean ret;
		Connection connection= DatabaseConnection.getInstance().getDbConnection();
		
		PreparedStatement statement = connection.prepareStatement("select count(*) as allow from Results where CUserNAme=? and TestId=?");
		statement.setString(1,username2);
		statement.setInt(2,testid2);
		ResultSet rs= statement.executeQuery();
		int allow=rs.getInt("allow");
		if(allow>0)
		{
		}
		else
		{
			ret=false;
		}
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String today=dateFormat.format(date);
		
		PreparedStatement statement1 = connection.prepareStatement("SELECT * FROM test WHERE ? BETWEEN StartTime AND EndTime AND TestId =?");
		statement1.setString(1,today);
		statement1.setInt(2,testid2);
		ResultSet rs1=statement1.executeQuery();
		int i = 0;
		while(rs1.next())
		{
			i++;
			
		}
		if(i>0)
		{
			ret = true;
		}else{
			ret = false;
		}
		rs1.close();
		rs.close();
		statement1.close();
		statement.close();
		return ret;
	}
	
}
