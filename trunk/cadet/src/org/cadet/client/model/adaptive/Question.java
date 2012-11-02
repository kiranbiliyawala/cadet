package org.cadet.client.model.adaptive;


/**
 * @author Varun Jamdar
 *
 */
public class Question {
	
	private int questionId;
	private int level;
	private int marks;
	private int negativeMarks;
	private String category;
	private String question;
	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;
	private String correctAnswer;
	
	public Question(int questionId, int level, int marks, int negativeMarks, String category, String question, String optionA, String optionB, String optionC, String optionD, String correctAnswer) {
		// TODO Auto-generated constructor stub
		this.questionId=questionId;
		this.level=level;
		this.marks=marks;
		this.negativeMarks=negativeMarks;
		this.category=category;
		this.question=question;
		this.optionA=optionA;
		this.optionB=optionB;
		this.optionC=optionC;
		this.optionD=optionD;
		this.correctAnswer=correctAnswer;
	}

	public int getQuestionId(){
		return this.questionId;
	}
	
	public int getLevel() {
		return level;
	}

	public int getMarks() {
		return marks;
	}

	public int getNegativeMarks() {
		return negativeMarks;
	}

	public String getCategory() {
		return category;
	}

	public String getQuestion() {
		return question;
	}

	public String getOptionA() {
		return optionA;
	}

	public String getOptionB() {
		return optionB;
	}

	public String getOptionC() {
		return optionC;
	}

	public String getOptionD() {
		return optionD;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

}
