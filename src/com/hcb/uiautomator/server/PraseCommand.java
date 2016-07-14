package com.hcb.uiautomator.server;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;




//{"action":"click","unique":"CLASSNAME","params":{"className":"android.widget.TextView","index":23}}
public class PraseCommand {
  private JSONObject json;
  public PraseCommand(String jsonStr) throws Exception{
    try {
		json = new JSONObject(jsonStr);
	} catch (JSONException e) {
		throw new JSONException(e.getLocalizedMessage());
	}
  }
  
  /**
   * 定位方式
   * @return
   * @throws JSONException
   */
  public String unique() throws JSONException {
    return json.getString("unique");
  }
  
  
  /**
   * 返回Action类型
   * @return
   */
  public String getType() {
	 String type = "";
    try {
		type =json.getString("action");
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    return type;
  }

  /**
   * 返回参数MAP
   * @return
   * @throws JSONException
   */
  public Map<String, String> params() throws JSONException {
    final JSONObject paramsObj = json.getJSONObject("params");
    final Map<String, String> newParams = new HashMap<String, String>();
    final Iterator<?> keys = paramsObj.keys();
    while (keys.hasNext()) {
    	final String param = (String) keys.next();
       newParams.put(param, (String) paramsObj.get(param));
    }
    return newParams;
  }
}
