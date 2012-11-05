package org.cadet.admin.bean;

/**
 * @author KIRAN
 *
 */

public class QuestionBank {
	private String questionId;
	private String categoryId;
	private String levelId;
	private String question;
	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;
	private String correctAnswer;
	
	public String getQuestionId(){
		return questionId;
	}
	
	public void setQuestionId(String questionId){
		this.questionId = questionId;
	}

	public String getCategoryId(){
		return categoryId;
	}
	
	public void setCategoryId(String categoryId){
		this.categoryId = categoryId;
	}

	public String getLevelId(){
		return levelId;
	}
	
	public void setLevelId(String levelId){
		this.levelId = levelId;
	}

	public String getQuestion(){
		return question;
	}
	
	public void setQuestion(String question){
		this.question = question;
	}

	public String getOptionA(){
		return optionA;
	}
	
	public void setOptionA(String optionA){
		this.optionA = optionA;
	}

	public String getOptionB(){
		return optionB;
	}
	
	public void setOptionB(String optionB){
		this.optionB = optionB;
	}

	public String getOptionC(){
		return optionC;
	}
	
	public void setOptionC(String optionC){
		this.optionC = optionC;
	}

	public String getOptionD(){
		return optionD;
	}
	
	public void setOptionD(String optionD){
		this.optionD = optionD;
	}

	public String getCorrectAnswer(){
		return correctAnswer;
	}
	
	public void setCorrectAnswer(String correctAnswer){
		this.correctAnswer = correctAnswer;
	}
}
