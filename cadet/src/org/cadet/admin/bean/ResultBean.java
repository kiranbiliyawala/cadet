package org.cadet.admin.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ResultBean extends JSONArray{

	public boolean addScore(String username,Double score,Integer Attempted,Integer Correct){
		
		JSONObject resultobject = new JSONObject();
		try {
			resultobject.put("UserName", username);
			resultobject.put("Score", score);
			resultobject.put("Attempted", Attempted);
			resultobject.put("Correct", Correct);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		this.put(resultobject);
		
		return true;
	}
	
}
