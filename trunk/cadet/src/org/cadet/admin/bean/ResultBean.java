package org.cadet.admin.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ResultBean extends JSONArray{

	public boolean addScore(Integer Rank,String username,Double score,Integer Attempted,Integer Correct,Double Percentile){
		
		JSONObject resultobject = new JSONObject();
		try {
			resultobject.put("Rank",Rank);
			resultobject.put("UserName", username);
			resultobject.put("Score", score);
			resultobject.put("Attempted", Attempted);
			resultobject.put("Correct", Correct);
			resultobject.put("Percentile", Percentile);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		this.put(resultobject);
		
		return true;
	}
	
}
