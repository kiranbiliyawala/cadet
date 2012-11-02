package org.cadet.client.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NAHTMLConvert {

	static String text_div_start = "<div ";
	static String tag_end = ">";
    static String text_class_start = " class=\"";
    static String text_id_start = " id=\"";
    static String text_text_end = "\"";
    static String text_div_end = "<\\div>";
    static String text_onclick = " onclick=\"change_que(";
    static String text_onclick_cat = " onclick=\"show_cat(";
    static String text_onclick_end = ");\"";
	
	
	public static String CatSelectorHTML(NonAdaptiveTest test) throws JSONException{
		
		
        
        JSONObject catdistriution = test.getQuestionDistribution();
        
        JSONArray names = catdistriution.names();
        
        String out="";
        
        for(int i =0;i<names.length();i++){
        	String cat=names.getString(i);
        	out = out+generate_cat_selector(test, cat, catdistriution.getInt(cat));
        }
        
		return out;
	}
	
	public static String QuestionSelectorHTML(NonAdaptiveTest test) throws JSONException{
		
		
        
        JSONObject catdistriution = test.getQuestionDistribution();
        
        JSONArray names = catdistriution.names();
        
        String out="";
        
        for(int i =0;i<names.length();i++){
        	String cat=names.getString(i);
        	out = out+generate_section(test, cat, catdistriution.getInt(cat));
        }
        
		return out;
	}
	
	private static String generate_section(NonAdaptiveTest test,String category,int no_ofQuestion) throws JSONException{
		String cover_div = "";
		cover_div = cover_div+text_div_start+
				text_class_start+"CatCover"+text_text_end+
				text_id_start+"cover"+category+text_text_end+
				tag_end+category;

		String out="";
		out = out+cover_div+"\n";
		for(int i=1;i<=no_ofQuestion;i++){
			Integer qno = i;
			out = out+generate_stub(test, category, qno.toString())+"\n";
		}
		out = out+text_div_end;
		
		return out;
	}
	
	private static String generate_cat_selector(NonAdaptiveTest test,String category,int no_ofQuestion) throws JSONException{
			
		String Cat_div = "";
		
		
		Cat_div = Cat_div+text_div_start+
				text_class_start+"Category"+text_text_end+
				text_id_start+"cat"+category+text_text_end+tag_end+
				text_div_start+text_class_start+"CatQuestion"+text_text_end+
				text_onclick_cat+"\'cover"+category+"\'"+text_onclick_end+
				tag_end+category;
		
		String out="";
		out = out+Cat_div+"\n"+generate_section(test, category, no_ofQuestion)+"\n";
		out = out+text_div_end;
		
		return out;
	}
	
	private static String generate_stub(NonAdaptiveTest test,String category,String QNO) throws JSONException{
		
		String status = "NotAttempted";
		
		if(test.isAttempted(category, QNO)){
			status = "Attempted";
		}
		
		String out = "";
		
		out = out+text_div_start+text_class_start+"stub "+status+text_text_end+text_id_start+"Qstub"+category+QNO+text_text_end+text_onclick+"\'"+category+"\'"+","+QNO+text_onclick_end+tag_end+QNO+text_div_end;		
		
		return out;
	}
	
}
