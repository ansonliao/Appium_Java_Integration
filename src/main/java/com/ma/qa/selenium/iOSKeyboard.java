package com.ma.qa.selenium;

import java.util.List;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.ma.qa.commons.CommonUtil;

import io.appium.java_client.AppiumDriver;

public class iOSKeyboard {
	private static IOSDriver driver = null;

    /**
	public static void setDriver(AppiumDriver dr) {
		driver = dr;
	}
     */
	
	public static void clear() {
		driver = null;
	}
	
	public static void tap(String keyString) throws Exception {
		if (driver == null)
			getDriverInstance();
		WebElement keyboard = driver.findElement(By.className("UIAKeyboard"));
		keyboard.findElement(By.name(keyString)).click();
		CommonUtil.sleep(1);
	}
	
	public static WebElement getKey(String keyString) throws Exception {
		if (driver == null)
			getDriverInstance();
		
		return ((WebElement) driver.findElement(By.className("UIAKeyboard"))).findElement(By.name(keyString));
	}
	
	public static boolean keyboardShown() {
		if (driver == null)
			getDriverInstance();
		  
		if (driver.findElementsByName("Hide keyboard").size() > 0)
			return true;
		else
			return false;
	}
	
	public static boolean keyboardHidden() {
		if (driver == null)
			getDriverInstance();
		  
		if (driver.findElementsByName("Hide keyboard").size() == 0)
			return true;
		else
			return false;
	}
	
	public static void hide() throws Exception{
		if (driver == null)
			getDriverInstance();
		
		driver.findElement(By.className("UIAKeyboard")).findElement(By.name("Hide keyboard")).click();
		CommonUtil.sleep(1);
		
	}
	
	private static AppiumDriver getDriverInstance() {
		driver = WebDriverManager.getIOSDriverInstance();
		return driver;
	}

}
