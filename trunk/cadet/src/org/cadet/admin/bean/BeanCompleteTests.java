package org.cadet.admin.bean;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BeanCompleteTests extends JSONArray{

public boolean addTest(String TestName,Date date,Integer TestDuration,String TestType,Integer TestId){
		
		DateFormat dtformat = new SimpleDateFormat("yyyy-mm-dd");
		String testdate = dtformat.format(date);
		
		JSONObject testobject = new JSONObject();
		try {
			testobject.put("TestName", TestName);
			testobject.put("TestDate", testdate);
			testobject.put("TestDuration", TestDuration);
			testobject.put("TestType", TestType);
			testobject.put("TestId", TestId);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		this.put(testobject);
		
		return true;
	}
	
}
