package com.hcb.uiautomator.utils;

import java.util.List;

import com.hcb.uiautomator.bean.ElementInfo;

import android.graphics.Rect;

/** 
 * @author  linhong: 
 * @date 2016��6��21�� ����10:55:01 
 * @Description: TODO
 * @version 1.0  
 */
public class ElementUtil {
	
	/**
	 * ��������㵽�ؼ����ϽǺ����½ǵľ���֮��
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
	 * ͨ������������ȡ�ؼ���Ψһ����
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
			if(!(x>left&&x<right&&y>top&&y<bottom)){  //��һ�ι���,�����������Ŀ��������
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
