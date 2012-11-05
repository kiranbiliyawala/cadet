package org.cadet.client.model.adaptive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.cadet.client.model.adaptive.algorithm.Adaptive_Ability_Optimization;


/**
 * @author Varun Jamdar
 *
 */
public class AdaptiveTest {

	//To do list
	
	//add userId/username private
	//add function public HashMap<Integer, String> getCategories() // returns HashMap<Integer categoryId, String categoryName>
	//add duration for current category and initialize in constructor and startNextSection
	//add scheduler in startTest method
	//create a runnable class that calls submitSection
	//split startNextQuestion into SubmitSection i.e. submit a particular category; and startSection(int categoryId)
	
	private int testId;
	private int categoryId;
	private HashMap<Integer, Integer> categoryWiseQuestions;
	private HashMap<Integer, Double> categoryWiseAbility;
	private HashMap<Integer, Double> categoryWiseTimedAbility;
	private HashMap<Integer, Integer> categoryDone;
	private Adaptive_Ability_Optimization aao;
	private Question question;
	private ArrayList<Integer> askedQuestions;
	private int skippedQuestions=0;
	private int correctAnswers=0;
	
	
	public AdaptiveTest(int testID) throws Exception{
		this.testId=testID;
		
		this.categoryWiseQuestions= AdaptiveTestDBTransactions.getCategoryWiseQuestionCount(this.testId);
		this.categoryWiseAbility=new HashMap<Integer,Double>();
		this.categoryWiseTimedAbility=new HashMap<Integer,Double>();
		this.categoryDone=new HashMap<Integer, Integer>();
		int noOfQuestions=0;
		if(!categoryWiseQuestions.isEmpty()){
			Iterator<Entry<Integer,Integer>> iterator=categoryWiseQuestions.entrySet().iterator();
			if(iterator.hasNext()){
				Map.Entry<Integer,Integer> e=iterator.next();
				noOfQuestions=e.getValue().intValue();
				//categoryDone.add(e.getKey().intValue());
				categoryId=e.getKey().intValue();
			}
			iterator=null;
		}
		
		aao= AdaptiveTestDBTransactions.generateAAO(testID, noOfQuestions);
		
		ArrayList<Integer> temp= new ArrayList<Integer>();//since initially askedQuestions would be empty and NOT IN() would give error. Also no question would have 0 as questionId ;)
		temp.add(new Integer(0));
		question=AdaptiveTestDBTransactions.fetchNextQuestion(this.testId, this.categoryId, aao.getDifficulty(), temp);
		categoryDone.put(this.categoryId, 1);
		askedQuestions.add(question.getQuestionId());
	}
	
	public Question startTest(){
		aao.start_test();
		return this.question;
	}
	
	public Question skipQuestion() throws Exception {
		
		aao.skip_question();
		this.skippedQuestions++;
		
		if (this.categoryDone.get(this.categoryId)<this.categoryWiseQuestions.get(this.categoryId)) { 
			//if no of questions already asked for current category is less than no of questions to be asked for current category.
			try {
				this.question = AdaptiveTestDBTransactions.fetchNextQuestion(this.testId, this.categoryId, aao.getDifficulty(), this.askedQuestions);
				if(this.question!=null){
					this.askedQuestions.add(this.question.getQuestionId());
					this.categoryDone.put(this.categoryId, this.categoryDone.get(this.categoryId).intValue()+1);
					return this.question;
				}
				else return null;
			} catch (Exception e) {
				if (e.getMessage().equals("Questions for the given difficulty are exhausted !")) {
					//what if questions of a difficulty level are exhausted? either increase or decrease difficulty. How to decide?
					//return startNextSection();
					throw e;
				}
				else throw e;
			}
		}
		else {
			return startNextSection();
		}
	}
	
	public Question submitQuestion(String answer) throws Exception {
		
		boolean isAnsweredCorrectly=this.question.getCorrectAnswer().equals(answer);
		if(isAnsweredCorrectly)
			this.correctAnswers++;
		
		Double d= this.aao.process_answer(isAnsweredCorrectly);
		
		if (this.categoryDone.get(this.categoryId)<this.categoryWiseQuestions.get(this.categoryId)) {
			//if no of questions already asked for current category is less than no of questions to be asked for current category.
			try {
				this.question = AdaptiveTestDBTransactions.fetchNextQuestion(this.testId, this.categoryId, d, this.askedQuestions);
				if(this.question!=null){
					this.askedQuestions.add(this.question.getQuestionId());
					this.categoryDone.put(this.categoryId, this.categoryDone.get(this.categoryId).intValue()+1);
					return this.question;
				}
				else return null;
			} catch (Exception e) {
				if (e.getMessage().equals("Questions for the given difficulty are exhausted !")) {
					//what if questions of a difficulty level are exhausted? either increase or decrease difficulty. How to decide?
					//return startNextSection();
					throw e;
				}
				else throw e;
			}
		}
		else {
			return startNextSection();
		}
	}
	
	public Question startNextSection() throws Exception {
		
		this.categoryWiseAbility.put(this.categoryId, aao.getAbility());
		this.categoryWiseTimedAbility.put(this.categoryId, aao.getTimedAbility());
		int noOfQuestions=0;
		Iterator<Entry<Integer,Integer>> iterator=categoryWiseQuestions.entrySet().iterator();
		while(iterator.hasNext()){
			Map.Entry<Integer,Integer> e=iterator.next();
			if(!categoryDone.containsKey(e.getKey())) {
				noOfQuestions=e.getValue().intValue();
				this.categoryId=e.getKey().intValue();
			}
		}
		iterator=null;
		if(categoryDone.containsKey(this.categoryId)){
			// this means if after trying to fetch new categoryId if its in the list of done. It means test is over and no category is left.
			// notify the controler to invoke finishTest() method passing the user's name(Id)
			//finishTest();
			return null;
		}
		else{
			this.aao=AdaptiveTestDBTransactions.generateAAO(this.testId, noOfQuestions);
			this.question=AdaptiveTestDBTransactions.fetchNextQuestion(this.testId, this.categoryId, aao.getDifficulty(), askedQuestions);
			this.askedQuestions.add(this.question.getQuestionId());
			this.categoryDone.put(this.categoryId, 1);
			return this.question;
		}
	}
	
	@SuppressWarnings("unused")
	private String finishTest(String username) throws Exception{
		
		int attempted=0;
		double final_ability=1.0;
		int count=0;
		
		this.categoryWiseAbility.put(this.categoryId, aao.getAbility());
		this.categoryWiseTimedAbility.put(this.categoryId, aao.getTimedAbility());
		
		Iterator<Entry<Integer,Integer>> iter=this.categoryDone.entrySet().iterator();
		while(iter.hasNext()){
			attempted+=iter.next().getValue().intValue();
		}
		iter=null;
		attempted-=this.skippedQuestions;
		
		Iterator<Entry<Integer,Double>> iterator=this.categoryWiseTimedAbility.entrySet().iterator();
		while(iterator.hasNext()){
			final_ability= iterator.next().getValue().doubleValue();
			count++;
		}
		final_ability=final_ability/count;
		
		AdaptiveTestDBTransactions.saveResult(username, this.testId, final_ability, attempted, this.correctAnswers);
		
		return null;
	}
}
