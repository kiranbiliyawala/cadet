package org.cadet.client.model.adaptive;

import java.util.ArrayList;
import org.cadet.client.model.adaptive.algorithm.Adaptive_Ability_Optimization;


/**
 * @author Varun Jamdar
 *
 */
public class AdaptiveTest {

	private int testId;
		
	private Adaptive_Ability_Optimization aao;
	private Question question;
	private ArrayList<Integer> askedQuestions;
	
	public AdaptiveTest(int testID) throws Exception{
		this.testId=testID;
		aao= AdaptiveTestDBTransactions.generateAAO(testID);
		
		ArrayList<Integer> temp= new ArrayList<Integer>();//since initially askedQuestions would be empty and NOT IN() would give error. Also no question would have 0 as questionId ;)
		temp.add(new Integer(0));
		question=AdaptiveTestDBTransactions.fetchNextQuestion(this.testId, aao.getDifficulty(), temp);
		
		askedQuestions.add(question.getQuestionId());
	}
	
	public void startTest(){
		aao.start_test();
	}
	
	
}
