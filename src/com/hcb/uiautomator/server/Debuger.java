package com.hcb.uiautomator.server;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;

import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.hcb.uiautomator.bean.ElementInfo;
import com.hcb.uiautomator.constants.FindBy;
import com.hcb.uiautomator.core.AccessibilityNodeInfoDumper;
import com.hcb.uiautomator.core.QueryController;
import com.hcb.uiautomator.core.UiAutomatorBridge;
import com.hcb.uiautomator.utils.ElementUtil;

/** 
 * @author  linhong: 
 * @date 2016��6��20�� ����4:38:06 
 * @Description: TODO
 * @version 1.0  
 */
public class Debuger extends UiAutomatorTestCase{
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	

	public void testGetWindowInfo() {
		Bundle bundle = getParams();
		String[] coordinate = bundle.getString("element").split("x");  
		int x = Integer.parseInt(coordinate[0]);
		int y = Integer.parseInt(coordinate[1]);
		List<String> resource_id_list = new ArrayList<String>();
		List<String> text_list = new ArrayList<String>();
		List<String> contentdesc_list = new ArrayList<String>();
		UiAutomatorBridge uiAutomatorBridge = UiAutomatorBridge.getInstance();
		QueryController queryController = uiAutomatorBridge.getQueryController();
		AccessibilityNodeInfo rootNode = queryController.getAccessibilityRootNode();
		List<ElementInfo> elements = AccessibilityNodeInfoDumper.dumpWindowToList(rootNode);
		ElementInfo element = ElementUtil.findElementByCoordinate(elements,x, y);
		if(element==null){
			return;
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
		if(!resource_id_list.contains(resource_id)&&!("").equals(resource_id)){
			element.setUnique(FindBy.ID.toString());
		}else if(!text_list.contains(text)&&!("").equals(text)){
			element.setUnique(FindBy.TEXT.toString());
		}else if(!contentdesc_list.contains(contentdesc)&&!("").equals(contentdesc)){
			element.setUnique(FindBy.CONTENTDESC.toString());
		}else{
			element.setUnique(FindBy.CLASSNAME.toString());
		}
		System.out.println("FINDELEMENT:"+element.toString());
    }
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
