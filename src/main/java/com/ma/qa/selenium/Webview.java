package com.ma.qa.selenium;

import io.appium.java_client.AppiumDriver;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ma.qa.commons.CommonUtil;

public class Webview {
	private static AppiumDriver driver;
	private static String testType;
	private String handle = null;
	private int handleIndex;
	private ArrayList<String> handles = new ArrayList<String> ();
	private static Set<String> contextSet = null;
	private static String nativeContext = null;
	private static String webviewContext = null;

	
	public static boolean switchToWebview (WebElement keyEl) throws Exception, Exception {
		boolean wvFlag = false;
		driver = (AppiumDriver) WebDriverManager.getDriverInstance();
		
		contextSet = driver.getContextHandles();
		  for(String context : contextSet){
			  if (context.toLowerCase().contains("native")){
				  nativeContext = context;
				  continue;
			  }
			  
			  if (context.toLowerCase().contains("webview")) {
				  driver.context(context);
				  CommonUtil.sleep(1);
				  if (keyEl.isDisplayed()){
					  webviewContext = context;
					  wvFlag = true;
					  System.out.println("Switched to WebView succeeded: [" + context + "]");
					  break;
				  }
			  }
		  }
		  
		return wvFlag;
	}
	
	public static void leaveWebview() throws Exception {
		driver.context(nativeContext);
		CommonUtil.sleep(1);
	}
	

}