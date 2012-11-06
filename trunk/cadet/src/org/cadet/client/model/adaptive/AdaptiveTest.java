package org.cadet.client.model.adaptive;

import java.security.InvalidAlgorithmParameterException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.cadet.client.bean.Question;
import org.cadet.client.bean.CategoryAdaptiveTest;
import org.cadet.client.model.NonAdaptiveTest;
import org.cadet.client.model.NonAdaptiveTest.terminate;
import org.cadet.client.model.adaptive.algorithm.Adaptive_Ability_Optimization;


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
	
	private int testId;
	private int currentCategoryId;
	private String username;
	private HashMap<Integer, CategoryAdaptiveTest> categories;
	//private HashMap<Integer, Integer> categoryWiseQuestions;
	//private HashMap<Integer, Double> categoryWiseAbility;
	//private HashMap<Integer, Double> categoryWiseTimedAbility;
	//private HashMap<Integer, Integer> categoryDone;
	private Adaptive_Ability_Optimization aao;
	private Question question;
	//private ArrayList<Integer> askedQuestions;
	private int skippedQuestions=0;
	private int correctAnswers=0;
	
	
	public AdaptiveTest(int testID, String username) throws Exception{
		this.testId=testID;
		this.username=username;		
		this.categories= AdaptiveTestDBTransactions.getCategoryWiseQuestionCount(this.testId);
		
		/*this.categoryWiseAbility=new HashMap<Integer,Double>();
		this.categoryWiseTimedAbility=new HashMap<Integer,Double>();
		this.categoryDone=new HashMap<Integer, Integer>();*/
		
		/*int noOfQuestions=0;
		if(!categories.isEmpty()){
			//Iterator<Entry<Integer,Integer>> iterator=categories.entrySet().iterator();
			Iterator<CategoryAdaptiveTest> iterator=categories.values().iterator();
			if(iterator.hasNext()){
				//Map.Entry<Integer,Integer> e=iterator.next();
				CategoryAdaptiveTest cat=iterator.next();
				//noOfQuestions=e.getValue().intValue();
				noOfQuestions = cat.getQuestionsPerCategory();
				//categoryDone.add(e.getKey().intValue());
				//currentCategoryId=e.getKey().intValue();
				currentCategoryId=cat.getCategoryId();
			}
			iterator=null;
		}
		
		aao= AdaptiveTestDBTransactions.generateAAO(testID, noOfQuestions);
		
		ArrayList<Integer> temp= new ArrayList<Integer>();//since initially askedQuestions would be empty and NOT IN() would give error. Also no question would have 0 as questionId ;)
		temp.add(new Integer(0));
		this.question=AdaptiveTestDBTransactions.fetchNextQuestion(this.testId, this.currentCategoryId, aao.getDifficulty(), temp);
		//categoryDone.put(this.currentCategoryId, 1);
		this.categories.get(this.currentCategoryId).addAskedQuestion(this.question.getQuestionId());
		//askedQuestions.add(question.getQuestionId());
*/	
		}
	
	public void startTest(){
		aao.start_test();
	}
	
	public HashMap<Integer, CategoryAdaptiveTest> getUnattemptedCategories() throws Exception{
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

	public Question skipQuestion() throws Exception {
		
		aao.skip_question();
		this.skippedQuestions++;
		
		//if (this.categoryDone.get(this.currentCategoryId)<this.categoryWiseQuestions.get(this.currentCategoryId)) {
		if(!this.categories.get(this.currentCategoryId).checkQuestionsForCategoryAsked()) {
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
			} catch (Exception e) {
				if (e.getMessage().equals("Questions for the given difficulty are exhausted !")) {
					return increaseDifficulty();
				}
				else throw e;
			}
		}
		else {
			this.categories.get(this.currentCategoryId).setAbility(this.aao.getAbility());
			this.categories.get(this.currentCategoryId).setTimedAbility(this.aao.getTimedAbility());
			this.categories.get(this.currentCategoryId).setDone(true);
			
			throw new Exception("Section Completed!");
		}
	}
	
	public Question submitQuestion(String answer) throws Exception {
		
		boolean isAnsweredCorrectly=this.question.getCorrectAnswer().equals(answer);
		if(isAnsweredCorrectly)
			this.correctAnswers++;
		
		Double d= this.aao.process_answer(isAnsweredCorrectly);
		
		//if (this.categoryDone.get(this.currentCategoryId)<this.categoryWiseQuestions.get(this.currentCategoryId)) {
		if(!this.categories.get(this.currentCategoryId).checkQuestionsForCategoryAsked()) {
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
			} catch (Exception e) {
				if (e.getMessage().equals("Questions for the given difficulty are exhausted !")) {				
					return increaseDifficulty();
				}
				else throw e;
			}
		}
		else {
			this.categories.get(this.currentCategoryId).setAbility(this.aao.getAbility());
			this.categories.get(this.currentCategoryId).setTimedAbility(this.aao.getTimedAbility());
			this.categories.get(this.currentCategoryId).setDone(true);
			
			throw new Exception("Section completed!");
		}
	}
	
	public Question startSection(int categoryId) throws Exception {
		
		/*
		this.categoryWiseAbility.put(this.currentCategoryId, aao.getAbility());
		this.categoryWiseTimedAbility.put(this.currentCategoryId, aao.getTimedAbility());
		int noOfQuestions=0;
		Iterator<Entry<Integer,Integer>> iterator=categoryWiseQuestions.entrySet().iterator();
		while(iterator.hasNext()){
			Map.Entry<Integer,Integer> e=iterator.next();
			if(!categoryDone.containsKey(e.getKey())) {
				noOfQuestions=e.getValue().intValue();
				this.currentCategoryId=e.getKey().intValue();
			}
		}
		iterator=null;
		if(categoryDone.containsKey(this.currentCategoryId)){
			// this means if after trying to fetch new currentCategoryId if its in the list of done. It means test is over and no category is left.
			// notify the controller to invoke finishTest() method passing the user's name(Id)
			//finishTest();
			return null;
		}
		else{
			this.aao=AdaptiveTestDBTransactions.generateAAO(this.testId, noOfQuestions);
			this.question=AdaptiveTestDBTransactions.fetchNextQuestion(this.testId, this.currentCategoryId, aao.getDifficulty(), askedQuestions);
			this.askedQuestions.add(this.question.getQuestionId());
			this.categoryDone.put(this.currentCategoryId, 1);
			return this.question;
		}
		*/
		
		if(this.categories.get(categoryId).isDone())
			throw new Exception("This section is already completed!");
		else{
			this.currentCategoryId=categoryId;
			this.aao=AdaptiveTestDBTransactions.generateAAO(this.testId, this.categories.get(this.currentCategoryId).getQuestionsPerCategory());
			this.question=AdaptiveTestDBTransactions.fetchNextQuestion(this.testId, this.currentCategoryId, this.aao.getDifficulty(), this.categories.get(this.currentCategoryId).getAskedQuestions());
			this.categories.get(this.currentCategoryId).addAskedQuestion(this.question.getQuestionId());
			createScheduler(this.categories.get(this.currentCategoryId).getTimePerCategory());
			return this.question;
		}
	}
	
	private Question increaseDifficulty() throws Exception{
		try {
			this.aao.increase_difficulty();
			return AdaptiveTestDBTransactions.fetchNextQuestion(this.testId, this.currentCategoryId, this.aao.getDifficulty(), this.categories.get(this.currentCategoryId).getAskedQuestions());
		} catch (InvalidAlgorithmParameterException e) {
			throw new Exception("Section completed!");
		} catch(Exception ex){
			if(ex.getMessage().equals("Questions for the given difficulty are exhausted !"))
				throw new Exception("Section completed!", ex);
			else
				throw ex;
		}
	}

	private void submitSection(){
		this.categories.get(this.currentCategoryId).setDone(true);
	}
	
	private void finishTest(String username) throws Exception{
		
		int attempted=0;
		double final_ability=1.0;
		int count=0;
		
		//this.categoryWiseAbility.put(this.currentCategoryId, aao.getAbility());
		//this.categoryWiseTimedAbility.put(this.currentCategoryId, aao.getTimedAbility());
		this.categories.get(this.currentCategoryId).setAbility(this.aao.getAbility());
		this.categories.get(this.currentCategoryId).setTimedAbility(this.aao.getTimedAbility());
		this.categories.get(this.currentCategoryId).setDone(true);
		
		
		/*Iterator<Entry<Integer,Integer>> iter=this.categoryDone.entrySet().iterator();
		while(iter.hasNext()){
			attempted+=iter.next().getValue().intValue();
		}
		iter=null;
		
		attempted-=this.skippedQuestions;
		
		Iterator<Entry<Integer,Double>> iterator=this.categoryWiseTimedAbility.entrySet().iterator();
		while(iterator.hasNext()){
			final_ability += iterator.next().getValue().doubleValue();
			count++;
		}*/
		
		Iterator<CategoryAdaptiveTest> iterator=this.categories.values().iterator();
		CategoryAdaptiveTest category;
		while(iterator.hasNext()){
			category = iterator.next();
			attempted += category.getQuestionsPerCategory();
			final_ability += category.getTimedAbility();
			count++;
		}
		iterator=null;
		
		attempted -= this.skippedQuestions;
		
		final_ability = final_ability/count;
		
		AdaptiveTestDBTransactions.saveResult(username, this.testId, final_ability, attempted, this.correctAnswers);
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
