package com.hcb.uiautomator.server;


import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.hcb.uiautomator.utils.Logger;

/** 
 * @author  linhong: 
 * @date 2016年6月22日 下午8:03:17 
 * @Description: TODO
 * @version 1.0  
 */
public class TestAgent extends UiAutomatorTestCase{
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	/**
	 * 开启Socket服务
	 */
	public void teststartTestAgent(){
		try{
			SocketServer socketServer = new SocketServer(6666);
			socketServer.listenForever(true,true);
		}catch (final Exception e) {
			  Logger.error(e.getLocalizedMessage());
		      System.exit(1);
		    }
	}
}
