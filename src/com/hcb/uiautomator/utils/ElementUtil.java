package com.hcb.uiautomator.utils;

import java.util.List;

import com.hcb.uiautomator.bean.ElementInfo;

import android.graphics.Rect;

/** 
 * @author  linhong: 
 * @date 2016年6月21日 上午10:55:01 
 * @Description: TODO
 * @version 1.0  
 */
public class ElementUtil {
	
	/**
	 * 计算坐标点到控件左上角和右下角的距离之和
	 * @param rect
	 * @param x
	 * @param y
	 * @return
	 */
	private static double getDistance(Rect rect,int x,int y){
		int left = rect.left;
		int top = rect.top;
		int right = rect.right;
		int bottom = rect.bottom;
		double top_distance = Math.sqrt((x - left)*(x - left) + (y - top)*(y - top));
		double buttom_distance = Math.sqrt((x - right)*(x - right) + (y - bottom)*(y - bottom));
		double distance = top_distance+buttom_distance;
		return distance;
	}
	
	/**
	 * 通过点击的坐标获取控件的唯一属性
	 * @param elements
	 * @param x
	 * @param y
	 */
	public static ElementInfo findElementByCoordinate(List<ElementInfo> elements,int x,int y){
		double min_distance = getDistance(elements.get(0).getBounds(), x, y);
		ElementInfo elementInfo = null;
		for(ElementInfo element:elements){
			Rect rect = element.getBounds();
			int top = rect.top;
			int left = rect.left;
			int bottom = rect.bottom;
			int right = rect.right;
			if(!(x>left&&x<right&&y>top&&y<bottom)){  //第一次过滤,点击的坐标在目标区域内
				continue;			
			}
			double tmp = getDistance(element.getBounds(), x, y);
			if(tmp<min_distance){
				elementInfo = element;
				min_distance = tmp;
			}
		}
		return elementInfo;
	}
}
