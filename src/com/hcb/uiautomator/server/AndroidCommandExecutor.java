package com.hcb.uiautomator.server;

import java.util.HashMap;

import com.hcb.uiautomator.aciton.Action;
import com.hcb.uiautomator.aciton.Find;
import com.hcb.uiautomator.aciton.SetText;
import com.hcb.uiautomator.utils.Logger;
import com.hcb.uiautomator.utils.WDStatus;


class AndroidCommandExecutor {

  private static HashMap<String, Action> map = new HashMap<String, Action>();
  
  public AndroidCommandExecutor() {
	 
  }
  
  static {
    map.put("Find", new Find());
    map.put("SetText", new SetText());
  }

  
  /**
   * 执行Command返回执行结果
   * @param command
   * @return
   */
  public ActionResult execute(PraseCommand command) {
	Logger.debug("Got command action: " + command.getType());
    if(map.containsKey(command.getType())) {
        return map.get(command.getType()).execute(command);
      } else {
        return new ActionResult(WDStatus.UNKNOWN_COMMAND,
            "Unknown command: " + command.getType());
      }
  }
}
