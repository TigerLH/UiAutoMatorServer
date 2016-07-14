package com.hcb.uiautomator.utils;

import org.json.JSONException;
import org.json.JSONObject;



/** 
 * @author  linhong: 
 * @date 2016年6月27日 下午4:50:40 
 * @Description: TODO
 * @version 1.0  
 */
public class Test {
	  
	  public static void main(String[] args) throws JSONException {
		  JSONObject json = new JSONObject();
		  json.put("status", 0);
	      json.put("content","\"test\""+":"+"\"test\"");
	     // System.out.println(json.toString());
	  }
}
