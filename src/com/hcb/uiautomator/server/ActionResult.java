package com.hcb.uiautomator.server;



import org.json.JSONException;
import org.json.JSONObject;

import com.hcb.uiautomator.utils.WDStatus;


public class ActionResult {

  JSONObject json;

  public ActionResult(final WDStatus status) {
    try {
      json = new JSONObject();
      json.put("status", status.code());
      json.put("content", status.message());
    } catch (final JSONException e) {
      e.printStackTrace();
    }
  }

  public ActionResult(final WDStatus status, final JSONObject val) {
    json = new JSONObject();
    try {
      json.put("status", status.code());
      json.put("content", val);
    } catch (final JSONException e) {
    	e.printStackTrace();
    }
  }

  public ActionResult(final WDStatus status, final Object val) {
    json = new JSONObject();
    try {
      json.put("status", status.code());
      json.put("content", val);
    } catch (final JSONException e) {
    	e.printStackTrace();
    }
  }

  public ActionResult(final WDStatus status, final String val) {
    try {
      json = new JSONObject();
      json.put("status", status.code());
      json.put("content", val);
    } catch (final JSONException e) {
    	e.printStackTrace();
    }
  }

  @Override
  public String toString() {
    return json.toString();
  }
}
