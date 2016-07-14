package com.hcb.uiautomator.aciton;

import java.util.Map;

import org.json.JSONException;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.hcb.uiautomator.constants.FindBy;
import com.hcb.uiautomator.server.ActionResult;
import com.hcb.uiautomator.server.PraseCommand;
import com.hcb.uiautomator.utils.Utf7ImeHelper;

/** 
 * @author  linhong: 
 * @date 2016年7月1日 下午2:22:39 
 * @Description: TODO
 * @version 1.0  
 */
public class SetText implements Action{
	private UiObject editText = null;
	@Override
	public ActionResult execute(PraseCommand command) {
		try {
			String unique = command.unique();
			Map<String, String> map = command.params();
			String content = map.get("content");
			if(unique.equals(FindBy.ID.toString())){
				String resourceId = map.get("resourceId");
				editText = new UiObject(new UiSelector().resourceId(resourceId));
			}else if(unique.equals(FindBy.TEXT.toString())){
				String text = map.get("text");
				editText = new UiObject(new UiSelector().text(text));
			}else if(unique.equals(FindBy.CONTENTDESC.toString())){
				String contentdesc = map.get("contentdesc");
				editText = new UiObject(new UiSelector().description(contentdesc));
			}else if(unique.equals(FindBy.CONTENTDESC.toString())){
				String classname = map.get("className");
				int index = Integer.parseInt(map.get("index"));
				editText = new UiObject(new UiSelector().className(classname).index(index));
			}
			editText.setText(Utf7ImeHelper.e(content));
		} catch (Exception e) {
			e.printStackTrace();
		}
			return null;
		}
}

