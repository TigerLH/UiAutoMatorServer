package com.hcb.uiautomator.bean;

import android.graphics.Rect;

public class ElementInfo {
	private boolean NAF;
	private int index;
	private String text;
	private String className;
	private String pkg;
	private String contentdesc;
	private boolean checkable;
	private boolean checked;
	private boolean clickable;
	private boolean enabled;
	private boolean focusable;
	private boolean focused;
	private boolean scrollable;
	private boolean longclickable;
	private boolean password;
	private boolean selected;
	private Rect Bounds;
	private String resourceId;
	private String unique;
	public boolean isNAF() {
		return NAF;
	}
	public void setNAF(boolean nAF) {
		NAF = nAF;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getPkg() {
		return pkg;
	}
	public void setPkg(String pkg) {
		this.pkg = pkg;
	}
	public String getContentdesc() {
		return contentdesc;
	}
	public void setContentdesc(String contentdesc) {
		this.contentdesc = contentdesc;
	}
	public boolean isCheckable() {
		return checkable;
	}
	public void setCheckable(boolean checkable) {
		this.checkable = checkable;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public boolean isClickable() {
		return clickable;
	}
	public void setClickable(boolean clickable) {
		this.clickable = clickable;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public boolean isFocusable() {
		return focusable;
	}
	public void setFocusable(boolean focusable) {
		this.focusable = focusable;
	}
	public boolean isFocused() {
		return focused;
	}
	public void setFocused(boolean focused) {
		this.focused = focused;
	}
	public boolean isScrollable() {
		return scrollable;
	}
	public void setScrollable(boolean scrollable) {
		this.scrollable = scrollable;
	}
	public boolean isLongclickable() {
		return longclickable;
	}
	public void setLongclickable(boolean longclickable) {
		this.longclickable = longclickable;
	}
	public boolean isPassword() {
		return password;
	}
	public void setPassword(boolean password) {
		this.password = password;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public Rect getBounds() {
		return Bounds;
	}
	public void setBounds(Rect bounds) {
		Bounds = bounds;
	}
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public String getUnique() {
		return unique;
	}
	public void setUnique(String unique) {
		this.unique = unique;
	}
	@Override
	public String toString() {
		return "ElementInfo [NAF=" + NAF + "& index=" + index + "& text="
				+ text + "& className=" + className + "& pkg=" + pkg
				+ "& contentdesc=" + contentdesc + "& checkable=" + checkable
				+ "& checked=" + checked + "& clickable=" + clickable
				+ "& enabled=" + enabled + "& focusable=" + focusable
				+ "& focused=" + focused + "& scrollable=" + scrollable
				+ "& longclickable=" + longclickable + "& password=" + password
				+ "& selected=" + selected + "& Bounds=" + Bounds
				+ "& resourceId=" + resourceId + "& unique=" + unique + "]";
	}
	
	
}
