package org.cadet.client.model.adaptive;

import java.security.InvalidAlgorithmParameterException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.cadet.client.bean.Question;
import org.cadet.client.bean.CategoryAdaptiveTest;
import org.cadet.client.model.adaptive.algorithm.Adaptive_Ability_Optimization;
import org.cadet.util.Exceptions.DificultyExhaustException;
import org.cadet.util.Exceptions.NoSuchTestException;
import org.cadet.util.Exceptions.SectionCompleteException;
import org.cadet.util.Exceptions.TestFinishException;


/**
 * @author Varun Jamdar
 *
 */
public class AdaptiveTest {

	//To do list
	
	//add userId/username private
	//add function public HashMap<Integer, String> getCategories() // returns HashMap<Integer currentCategoryId, String categoryName>
	//add duration for current category and initialize in constructor and startNextSection
	//add scheduler in startTest method
	//create a runnable class that calls submitSection
	//split startNextQuestion into SubmitSection i.e. submit a particular category; and startSection(int currentCategoryId)
	//in startSection method handle QuestionsHaveBeenExhaustedForDifficultyLevel in try catch call increaseDifficulty. Though this would not be required as such but still worst case.
	
	private int testId;
	private int currentCategoryId;
	private String username;
	private String testName;
	private String testDescription;
	private Date testDate;
	private Double initialDifficulty;
	private HashMap<Integer, CategoryAdaptiveTest> categories;

	private Adaptive_Ability_Optimization aao;
	private Question question;
	private long TestTime;
	private int skippedQuestions=0;
	private int correctAnswers=0;
	
	
	public AdaptiveTest(int testID, String username) throws SQLException, NoSuchTestException{
		this.testId=testID;
		this.username=username;
		ArrayList<Object> testDetails=AdaptiveTestDBTransactions.getTestDetails(this.testId);
		this.testName=(String)testDetails.get(0);
		this.testDescription=(String)testDetails.get(1);
		this.testDate=(Date)testDetails.get(2);
		this.initialDifficulty=(Double)testDetails.get(3);
		
		this.categories= AdaptiveTestDBTransactions.getCategoryWiseQuestionCount(this.testId);
		Iterator<CategoryAdaptiveTest> iter=this.categories.values().iterator();
		CategoryAdaptiveTest cat;
		while(iter.hasNext()){
			cat=iter.next();
			cat.setAbility(this.initialDifficulty);
			cat.setTimedAbility(this.initialDifficulty);
			TestTime += cat.getTimePerCategory();
		}
		iter=null;
			
		
		}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getTestTime() {
		long test_hours = (int) (TestTime/60);
		long test_minutes = (int) (TestTime%60);
		return test_hours+":"+test_minutes+":00";
	}

	public String getTestName() {
		return this.testName;
	}

	public String getTestDescription() {
		return this.testDescription;
	}

	public Date getTestDate() {
		return this.testDate;
	}

	public HashMap<Integer, CategoryAdaptiveTest> getUnattemptedCategories() throws SQLException, TestFinishException{
		HashMap<Integer, CategoryAdaptiveTest> categories = new HashMap<Integer, CategoryAdaptiveTest>();
		Iterator<CategoryAdaptiveTest> iterator = this.categories.values().iterator();
		CategoryAdaptiveTest category;
		while(iterator.hasNext()){
			category= iterator.next();
			if(!category.isDone())
				categories.put(category.getCategoryId(), category);
		}
		if(categories.isEmpty())
			finishTest(this.username);
		return categories;
	}

	public Question skipQuestion() throws SQLException, SectionCompleteException,DificultyExhaustException {
		
		aao.skip_question();
		this.skippedQuestions++;
		
		//if (this.categoryDone.get(this.currentCategoryId)<this.categoryWiseQuestions.get(this.currentCategoryId)) {
		if((!this.categories.get(this.currentCategoryId).checkQuestionsForCategoryAsked())&&(!this.categories.get(this.currentCategoryId).isDone())) {
			//if no of questions already asked for current category is less than no of questions to be asked for current category.
			try {
				//this.question = AdaptiveTestDBTransactions.fetchNextQuestion(this.testId, this.currentCategoryId, aao.getDifficulty(), this.askedQuestions);
				this.question = AdaptiveTestDBTransactions.fetchNextQuestion(this.testId, this.currentCategoryId, aao.getDifficulty(), this.categories.get(this.currentCategoryId).getAskedQuestions());
				if(this.question!=null){
					//this.askedQuestions.add(this.question.getQuestionId());
					this.categories.get(this.currentCategoryId).addAskedQuestion(this.question.getQuestionId());
					//this.categoryDone.put(this.currentCategoryId, this.categoryDone.get(this.currentCategoryId).intValue()+1);
					return this.question;
				}
				else return null;
			} catch (DificultyExhaustException e) {
					return increaseDifficulty();
			}
		}
		else {
			this.categories.get(this.currentCategoryId).setAbility(this.aao.getAbility());
			this.categories.get(this.currentCategoryId).setTimedAbility(this.aao.getTimedAbility());
			this.categories.get(this.currentCategoryId).setDone(true);
			
			throw new SectionCompleteException("Section Completed!");
		}
	}
	
	public Question submitQuestion(String answer) throws SQLException, SectionCompleteException,DificultyExhaustException {
		
		boolean isAnsweredCorrectly=this.question.getCorrectAnswer().equals(answer);
		if(isAnsweredCorrectly)
			this.correctAnswers++;
		
		Double d= this.aao.process_answer(isAnsweredCorrectly);
		
		//if (this.categoryDone.get(this.currentCategoryId)<this.categoryWiseQuestions.get(this.currentCategoryId)) {
		if((!this.categories.get(this.currentCategoryId).checkQuestionsForCategoryAsked())&&(!this.categories.get(this.currentCategoryId).isDone())) {
			//if no of questions already asked for current category is less than no of questions to be asked for current category.
			try {
				//this.question = AdaptiveTestDBTransactions.fetchNextQuestion(this.testId, this.currentCategoryId, d, this.askedQuestions);
				this.question = AdaptiveTestDBTransactions.fetchNextQuestion(this.testId, this.currentCategoryId, d, this.categories.get(this.currentCategoryId).getAskedQuestions());
				if(this.question!=null){
					//this.askedQuestions.add(this.question.getQuestionId());
					this.categories.get(this.currentCategoryId).getAskedQuestions().add(this.question.getQuestionId());
					//this.categoryDone.put(this.currentCategoryId, this.categoryDone.get(this.currentCategoryId).intValue()+1);
					return this.question;
				}
				else return null;
			} catch (DificultyExhaustException e) {
				System.out.println(e.getMessage());
					return increaseDifficulty();
			}
		}
		else {
			this.categories.get(this.currentCategoryId).setAbility(this.aao.getAbility());
			this.categories.get(this.currentCategoryId).setTimedAbility(this.aao.getTimedAbility());
			this.categories.get(this.currentCategoryId).setDone(true);
			
			throw new SectionCompleteException("Section completed!");
		}
	}
	
	public Question startSection(int categoryId) throws SQLException, SectionCompleteException, InvalidAlgorithmParameterException, NoSuchTestException, DificultyExhaustException {
		
		
		
		if(this.categories.get(categoryId).isDone())
			throw new SectionCompleteException("This section is already completed!");
		else{
			this.currentCategoryId=categoryId;
			this.aao=AdaptiveTestDBTransactions.generateAAO(this.testId, this.categories.get(this.currentCategoryId).getQuestionsPerCategory());
			try {
				this.question=AdaptiveTestDBTransactions.fetchNextQuestion(this.testId, this.currentCategoryId, this.aao.getDifficulty(), this.categories.get(this.currentCategoryId).getAskedQuestions());
			} catch (DificultyExhaustException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
					this.question= this.increaseDifficulty();
				
			}
			this.categories.get(this.currentCategoryId).addAskedQuestion(this.question.getQuestionId());
			this.aao.start_test();
			createScheduler(this.categories.get(this.currentCategoryId).getTimePerCategory());
			return this.question;
		}
	}
	
	private Question increaseDifficulty() throws SQLException, SectionCompleteException{
		try {
			this.aao.increase_difficulty();
			System.out.println("diif: "+this.aao.getDifficulty());
			return AdaptiveTestDBTransactions.fetchNextQuestion(this.testId, this.currentCategoryId, this.aao.getDifficulty(), this.categories.get(this.currentCategoryId).getAskedQuestions());
		} catch (InvalidAlgorithmParameterException e) {
			System.out.println(e.getMessage());
			this.categories.get(this.currentCategoryId).setAbility(this.aao.getAbility());
			this.categories.get(this.currentCategoryId).setTimedAbility(this.aao.getTimedAbility());
			this.categories.get(this.currentCategoryId).setDone(true);
			throw new SectionCompleteException("Section completed!");
		} catch(DificultyExhaustException ex){
			System.out.println("diff exhausted increase difficulty");
			return this.increaseDifficulty();
				//return AdaptiveTestDBTransactions.fetchNextQuestion(this.testId, this.currentCategoryId, this.aao.getDifficulty(), this.categories.get(this.currentCategoryId).getAskedQuestions());
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
			// TODO: handle exception
		}
	}

	private void submitSection(){
		this.categories.get(this.currentCategoryId).setAbility(this.aao.getAbility());
		this.categories.get(this.currentCategoryId).setTimedAbility(this.aao.getTimedAbility());
		this.categories.get(this.currentCategoryId).setDone(true);
	}
	
	public void finishTest(String username) throws SQLException, TestFinishException{
		
		int attempted=0;
		double final_ability=1.0;
		
		
		Iterator<CategoryAdaptiveTest> iterator=this.categories.values().iterator();
		CategoryAdaptiveTest category;
		while(iterator.hasNext()){
			category = iterator.next();
			attempted += category.getQuestionsPerCategory();
			final_ability += category.getTimedAbility();
		}
		iterator=null;
		
		attempted -= this.skippedQuestions;
		
		final_ability = final_ability/this.categories.size();
		
		AdaptiveTestDBTransactions.saveResult(username, this.testId, final_ability, attempted, this.correctAnswers);
		throw new TestFinishException("Test Finished!");
	}
	
	private void createScheduler(long delay){
		ScheduledExecutorService scheduler =  Executors.newSingleThreadScheduledExecutor();
		terminate term = new terminate(this);
		scheduler.schedule(term, delay, TimeUnit.MINUTES);
	}
	
	public class terminate implements Runnable{
		AdaptiveTest test;
		public terminate(AdaptiveTest test) {
			this.test = test;
		}
		
		@Override
		public void run() {
			test.submitSection();
		}
		
	}
}
