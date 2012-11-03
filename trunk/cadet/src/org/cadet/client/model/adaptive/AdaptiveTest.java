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
		question=AdaptiveTestDBTransactions.fetchNextQuestion(aao.getDifficulty(),askedQuestions);
		//while(askedQuestions.contains((question=AdaptiveTestDBTransactions.fetchNextQuestion()).getQuestionId())) { //to ensure that once asked; a question is never repeated }
		
	}
	
	public void startTest(){
		aao.start_test();
	}
	
	
}
