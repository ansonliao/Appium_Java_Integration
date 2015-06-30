package com.ma.qa.selenium;

import org.openqa.selenium.WebElement;

import com.ma.qa.commons.CommonUtil;

public class ElementUtil {
	
	public static String getAttribute(WebElement el, String aValue) {
		return el.getAttribute(aValue).trim();
	}
	
	public static String getText(WebElement el) {
		return el.getText().trim();
	}
	
	public static void sendKeys(WebElement el, String key, boolean clear) throws Exception {
		if(clear)
			el.clear();
		el.sendKeys(key);
		CommonUtil.sleep(1);
	}
	
	public static void click(WebElement el) throws Exception {
		el.click();
		CommonUtil.sleep(1);			//each click action trigger 0.5 seconds sleep.
	}
	
	public static boolean isDisplayed(WebElement el) {
		return el.isDisplayed();
	}

}
