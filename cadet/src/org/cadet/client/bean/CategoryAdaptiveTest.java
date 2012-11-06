/**
 * 
 */
package org.cadet.client.bean;

import java.util.ArrayList;

import org.cadet.admin.bean.BeanTestCategory;

/**
 * @author Varun Jamdar
 *
 */

public class CategoryAdaptiveTest extends BeanTestCategory {

	/**
	 * 
	 */
	
	private Double ability;
	
	private Double timedAbility;
	private boolean done;
	private ArrayList<Integer> askedQuestions;
	
	public CategoryAdaptiveTest(int testId, int categoryId, String categoryName, int timePerCategory, int questionsPerCategory) {
		// TODO Auto-generated constructor stub
		super();
		this.setTestId(testId);
		this.setCategoryId(categoryId);
		this.setCategoryName(categoryName);
		this.setTimePerCategory(timePerCategory);
		this.setQuestionsPerCategory(questionsPerCategory);
	}

	public Double getAbility() {
		return ability;
	}

	public void setAbility(Double ability) {
		this.ability = ability;
	}

	public Double getTimedAbility() {
		return timedAbility;
	}

	public void setTimedAbility(Double timedAbility) {
		this.timedAbility = timedAbility;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public ArrayList<Integer> getAskedQuestions() {
		return askedQuestions;
	}

	public void addAskedQuestion(int questionId){
		this.askedQuestions.add(questionId);
	}
	
	public boolean checkQuestionsForCategoryAsked(){
		return (this.askedQuestions.size()>=this.getQuestionsPerCategory());
	}
}
