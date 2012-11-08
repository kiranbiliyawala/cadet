package org.cadet.client.bean;


/**
 * @author Varun Jamdar
 *
 */
public class Question {
	
	private int questionId;
	private int level;
	private int marks;//marks might not be of any use but still.
	private String category;
	private String question;
	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;
	private String correctAnswer;
	
	public Question(int questionId, int level, int marks, String category, String question, String optionA, String optionB, String optionC, String optionD, String correctAnswer) {
		// TODO Auto-generated constructor stub
		this.questionId=questionId;
		this.level=level;
		this.marks=marks;
		this.category=category;
		this.question=question;
		this.optionA=optionA;
		this.optionB=optionB;
		this.optionC=optionC;
		this.optionD=optionD;
		this.correctAnswer=correctAnswer;
	}

	public Question() {
	    //empty constructor
	    // for re - usability
	    // updated by udit
	}

	public int getQuestionId() {
	    return questionId;
	}

	public void setQuestionId(int questionId) {
	    this.questionId = questionId;
	}

	public int getLevel() {
	    return level;
	}

	public void setLevel(int level) {
	    this.level = level;
	}

	public int getMarks() {
	    return marks;
	}

	public void setMarks(int marks) {
	    this.marks = marks;
	}

	public String getCategory() {
	    return category;
	}

	public void setCategory(String category) {
	    this.category = category;
	}

	public String getQuestion() {
	    return question;
	}

	public void setQuestion(String question) {
	    this.question = question;
	}

	public String getOptionA() {
	    return optionA;
	}

	public void setOptionA(String optionA) {
	    this.optionA = optionA;
	}

	public String getOptionB() {
	    return optionB;
	}

	public void setOptionB(String optionB) {
	    this.optionB = optionB;
	}

	public String getOptionC() {
	    return optionC;
	}

	public void setOptionC(String optionC) {
	    this.optionC = optionC;
	}

	public String getOptionD() {
	    return optionD;
	}

	public void setOptionD(String optionD) {
	    this.optionD = optionD;
	}

	public String getCorrectAnswer() {
	    return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
	    this.correctAnswer = correctAnswer;
	}

	@Override
	public String toString() {
	    return "Question [questionId=" + questionId + ", level=" + level + ", marks=" + marks + ", category=" + category + ", question=" + question + ", optionA=" + optionA + ", optionB="
		    + optionB + ", optionC=" + optionC + ", optionD=" + optionD + ", correctAnswer=" + correctAnswer + "]";
	}
	
}
