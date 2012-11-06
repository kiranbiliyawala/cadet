package org.cadet.admin.model;

/**
 * @author KIRAN
 *
 */

import java.sql.*;
import java.util.*;

import org.cadet.admin.bean.Category;
import org.cadet.util.model.Constants;
import org.cadet.util.model.DatabaseConnection;

public class CategoryManagement {
	DatabaseConnection objConnection = DatabaseConnection.getInstance();
	
	public void addCategory(Category category) throws SQLException { 
		PreparedStatement pst = objConnection.getDbConnection().prepareStatement(Constants.sqlCommands.AddQuestionCategory);
        pst.setString(1, category.getCategoryName());
        pst.setString(2, category.getCategoryDescription());
        pst.setString(3, category.getAdminUsername());
        pst.executeUpdate();
        pst.close();
	}
	
	public void editCategory(Category category) throws SQLException, Exception {
		PreparedStatement pst = objConnection.getDbConnection().prepareStatement(Constants.sqlCommands.UpdateCategory);
		pst.setString(1, category.getCategoryName());
		pst.setString(2, category.getCategoryDescription());
        pst.setString(3, category.getAdminUsername());
        pst.setString(4, category.getCategoryId());
        pst.executeUpdate();
        pst.close();
	}
	
	public void removeCategory(String categoryId) throws SQLException {
		PreparedStatement pst = objConnection.getDbConnection().prepareStatement(Constants.sqlCommands.RemoveCategory);
        pst.setString(1, categoryId);
        pst.executeUpdate();
        pst.close();
	}
	
	public String viewCategoryByCategoryId(int categoryId) throws SQLException{
		PreparedStatement pst = objConnection.getDbConnection().prepareStatement(Constants.sqlCommands.RetrieveCategoryByCategoryID);
		pst.setInt(1, categoryId);
		ResultSet rs = pst.executeQuery();
		rs.next();
		return rs.getString(1);
	}
	
	public ArrayList<Category> viewCategory() throws SQLException {
        Statement st = objConnection.getDbConnection().createStatement();
        ResultSet rs = st.executeQuery(Constants.sqlCommands.RetrieveCategories);
        ArrayList<Category> categoryList = new ArrayList<Category>();
        Category objCategory;
        while (rs.next()){

        	objCategory = new Category();
        	objCategory.setCategoryId(rs.getString("CategoryId"));
            objCategory.setCategoryName(rs.getString("CategoryName"));
            objCategory.setCategoryDescription(rs.getString("Description"));
            objCategory.setAdminUsername(rs.getString("Ausername"));
            categoryList.add(objCategory);
        }
        return categoryList;
	}
}
