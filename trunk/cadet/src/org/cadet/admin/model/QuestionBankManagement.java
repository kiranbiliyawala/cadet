package org.cadet.admin.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.cadet.admin.bean.QuestionBank;
import org.cadet.util.model.Constants;
import org.cadet.util.model.DatabaseConnection;

/**
 * @author KIRAN
 * 
 */

public class QuestionBankManagement {

	DatabaseConnection objConnection = DatabaseConnection.getInstance();

	public void addQuestion(QuestionBank question) throws SQLException {
		PreparedStatement pst = objConnection.getDbConnection()
				.prepareStatement(Constants.sqlCommands.AddQuestion);
		pst.setString(1, question.getCategoryId());
		pst.setString(2, question.getLevelId());
		pst.setString(3, question.getQuestion());
		pst.setString(4, question.getOptionA());
		pst.setString(5, question.getOptionB());
		pst.setString(6, question.getOptionC());
		pst.setString(7, question.getOptionD());
		pst.setString(8, question.getCorrectAnswer());
		pst.executeUpdate();
		pst.close();
	}

	public void editQuestion(QuestionBank question) throws SQLException,
			Exception {
		PreparedStatement pst = objConnection.getDbConnection()
				.prepareStatement(Constants.sqlCommands.UpdateQuestion);
		pst.setString(1, question.getCategoryId());
		pst.setString(2, question.getLevelId());
		pst.setString(3, question.getQuestion());
		pst.setString(4, question.getOptionA());
		pst.setString(5, question.getOptionB());
		pst.setString(6, question.getOptionC());
		pst.setString(7, question.getOptionD());
		pst.setString(8, question.getCorrectAnswer());
		pst.setString(9, question.getQuestionId());
		pst.executeUpdate();
		pst.close();
	}

	public void removeQuestion(int questionId) throws SQLException {
		PreparedStatement pst = objConnection.getDbConnection()
				.prepareStatement(Constants.sqlCommands.RemoveQuestion);
		pst.setInt(1, questionId);
		pst.executeUpdate();
		pst.close();
	}

	public ArrayList<QuestionBank> viewAllQuestion() throws SQLException {
		Statement st = objConnection.getDbConnection().createStatement();
		ResultSet rs = st
				.executeQuery(Constants.sqlCommands.RetrieveAllQuestion);
		ArrayList<QuestionBank> questionList = new ArrayList<QuestionBank>();
		QuestionBank question;

		while (rs.next()) {
			question = new QuestionBank();
			question.setQuestionId(rs.getString(1));
			question.setCategoryId(rs.getString(2));
			question.setLevelId(rs.getString(3));
			question.setQuestion(rs.getString(4));
			question.setOptionA(rs.getString(5));
			question.setOptionB(rs.getString(6));
			question.setOptionC(rs.getString(7));
			question.setOptionD(rs.getString(8));
			question.setCorrectAnswer(rs.getString(9));
			questionList.add(question);
		}
		return questionList;
	}

	public QuestionBank viewQuestionByQuestionId(int questionId)
			throws SQLException {
		PreparedStatement pst = objConnection.getDbConnection()
				.prepareStatement(
						Constants.sqlCommands.RetrieveQuestionByQuestionID);
		pst.setInt(1, questionId);
		ResultSet rs = pst.executeQuery();
		QuestionBank question = new QuestionBank();
		;
		if (rs.next()) {
			question.setQuestionId(rs.getString(1));
			question.setCategoryId(rs.getString(2));
			question.setLevelId(rs.getString(3));
			question.setQuestion(rs.getString(4));
			question.setOptionA(rs.getString(5));
			question.setOptionB(rs.getString(6));
			question.setOptionC(rs.getString(7));
			question.setOptionD(rs.getString(8));
			question.setCorrectAnswer(rs.getString(9));
		}
		return question;
	}

	public ArrayList<QuestionBank> viewCategorywiseQuestion(int CategoryId)
			throws SQLException {
		PreparedStatement pst = objConnection.getDbConnection()
				.prepareStatement(
						Constants.sqlCommands.RetrieveCategorywiseQuestion);
		pst.setInt(1, CategoryId);
		ResultSet rs = pst.executeQuery();
		ArrayList<QuestionBank> questionList = new ArrayList<QuestionBank>();
		QuestionBank question;

		while (rs.next()) {
			question = new QuestionBank();
			question.setQuestionId(rs.getString(1));
			question.setCategoryId(rs.getString(2));
			question.setLevelId(rs.getString(3));
			question.setQuestion(rs.getString(4));
			question.setOptionA(rs.getString(5));
			question.setOptionB(rs.getString(6));
			question.setOptionC(rs.getString(7));
			question.setOptionD(rs.getString(8));
			question.setCorrectAnswer(rs.getString(9));
			questionList.add(question);
		}
		return questionList;
	}

	public ArrayList<String> getCatId() {

		ArrayList<String> Catlist = new ArrayList<String>();
		try {
			String query = "SELECT CategoryId ,CategoryName from category;";
			PreparedStatement pst = objConnection.getDbConnection()
					.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Catlist.add("CategoryId");
				Catlist.add("CategoryName");
			}

		} catch (Exception e) {
		} finally {
		}
		return Catlist;
	}

	public int getCategoryIdByName(String categoryName) throws SQLException,
			Exception {

		ResultSet rs;
		PreparedStatement ps = DatabaseConnection
				.getInstance()
				.getDbConnection()
				.prepareStatement(
						"SELECT CategoryId FROM category WHERE CategoryName=?");
		ps.setString(1, categoryName);
		rs = ps.executeQuery();
		
		if (rs.next()) {
			System.out.println(rs.getInt("CategoryId"));
			return rs.getInt("CategoryId");
		} else {
			throw new Exception("No such Category Exists!");
		}
		
	}
}

