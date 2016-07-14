package com.hcb.uiautomator.aciton;

import com.hcb.uiautomator.server.ActionResult;
import com.hcb.uiautomator.server.PraseCommand;

public interface Action {

	ActionResult execute(PraseCommand command);
	
}
