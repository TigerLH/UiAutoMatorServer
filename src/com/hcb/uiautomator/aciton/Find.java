package com.hcb.uiautomator.aciton;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import android.view.accessibility.AccessibilityNodeInfo;

import com.hcb.uiautomator.bean.ElementInfo;
import com.hcb.uiautomator.bean.FindElement;
import com.hcb.uiautomator.constants.FindBy;
import com.hcb.uiautomator.core.AccessibilityNodeInfoDumper;
import com.hcb.uiautomator.core.QueryController;
import com.hcb.uiautomator.core.UiAutomatorBridge;
import com.hcb.uiautomator.server.ActionResult;
import com.hcb.uiautomator.server.PraseCommand;
import com.hcb.uiautomator.utils.ElementUtil;
import com.hcb.uiautomator.utils.WDStatus;

public class Find implements Action{

	@Override
	public ActionResult execute(PraseCommand command) {
		int x = 0;
		int y = 0;
		ActionResult ar = null;
		try {
			Map<String, String> map = command.params();
			x = Integer.parseInt((map.get("x")));
			y = Integer.parseInt((map.get("y")));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> resource_id_list = new ArrayList<String>();
		List<String> text_list = new ArrayList<String>();
		List<String> contentdesc_list = new ArrayList<String>();
		UiAutomatorBridge uiAutomatorBridge = UiAutomatorBridge.getInstance();
		QueryController queryController = uiAutomatorBridge.getQueryController();
		AccessibilityNodeInfo rootNode = queryController.getAccessibilityRootNode();
		List<ElementInfo> elements = AccessibilityNodeInfoDumper.dumpWindowToList(rootNode);
		ElementInfo element = ElementUtil.findElementByCoordinate(elements,x, y);
		if(element==null){
			return new ActionResult(WDStatus.NO_SUCH_ELEMENT);
		}
		elements.remove(element);
		for(ElementInfo e:elements){
			if(!("".equals(e.getResourceId()))){
				resource_id_list.add(e.getResourceId());
			}
			
			if(!("".equals(e.getText()))){
				text_list.add(e.getText());
			}
			
			if(!("".equals(e.getContentdesc()))){
				contentdesc_list.add(e.getContentdesc());
			}
		}
			
		String resource_id = element.getResourceId();
		String text = element.getText();
		String contentdesc = element.getContentdesc();
		FindElement targetElement = new FindElement();
		targetElement.setResourceId(resource_id);
		targetElement.setText(text);
		targetElement.setContentdesc(contentdesc);
		targetElement.setPkg(element.getPkg());
		targetElement.setClassName(element.getClassName());
		targetElement.setIndex(element.getIndex());
		if(!resource_id_list.contains(resource_id)&&!("").equals(resource_id)){
			targetElement.setUnique(FindBy.ID.toString());
		}else if(!text_list.contains(text)&&!("").equals(text)){
			targetElement.setUnique(FindBy.TEXT.toString());
		}else if(!contentdesc_list.contains(contentdesc)&&!("").equals(contentdesc)){
			targetElement.setUnique(FindBy.CONTENTDESC.toString());
		}else{
			targetElement.setUnique(FindBy.CLASSNAME.toString());
		}
		try {
			ar = new ActionResult(WDStatus.SUCCESS,targetElement.toJson());
		} catch (JSONException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		return ar;
	}

}
