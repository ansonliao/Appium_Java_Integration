package com.ma.qa.selenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.yaml.snakeyaml.Yaml;

import com.ma.qa.commons.Config;
import com.ma.qa.exception.WebDriverInstanceException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;

public class Locator {
	
	private static WebDriver driver;
	private static IOSDriver idr;
    private static AndroidDriver adr;
    private static String testType = null;
	private static String yamlPath = null;
	private static Map yamlMap = null;
	private static String[] array = {
										"AccessibilityId", 
										"AndroidUIAutomator",
										"IosUIAutomation"
									};
	private static final ArrayList<String> list = new ArrayList<String>(Arrays.asList(array));
	
	
	public static void setYamlFile(String yamlFile) throws WebDriverInstanceException {
		yamlPath = null;
		yamlPath = yamlFile;
		parseYaml();
		initDriver();
	}
	
	public static WebElement getElement(String key) {
		Map ml = null;
		int index = 0;
		WebElement el = null;
		ml = (HashMap) yamlMap.get(key);
        
        String type = ml.get("type").toString().trim();
        String value = ml.get("value").toString().trim();
        if(ml.containsKey("index")){
        	index = Integer.parseInt(ml.get("index").toString().trim());
        	if(index < 0) 				//check index value
            	throw new IndexOutOfBoundsException("Index have to greater than or equals to ZERO: [index=" + index + "]");
        }
        
        //System.out.println("type: " + type + "; value: " + value + "; index= " + index);
        if(index == 0) 
        {
        	if(!list.contains(type))
        	{
        		//el = driver.findElement(getBy(type, value));
        		el = waitForElement(getBy(type, value));
        	}
        	else
        	{
                if (testType.equalsIgnoreCase("ios")) {                         //for iOS
                    if(type.equalsIgnoreCase("AccessibilityId"))
                        el = idr.findElementByAccessibilityId(value);
                    if (type.equalsIgnoreCase("IosUIAutomation"))
                        el = idr.findElementByIosUIAutomation(value);
                }

                if (testType.equalsIgnoreCase("android")) {                     //for Android
                    if(type.equalsIgnoreCase("AccessibilityId"))
                        el = adr.findElementByAccessibilityId(value);
                    if (type.equalsIgnoreCase("IosUIAutomation"))
                        el = adr.findElementByAndroidUIAutomator(value);
                }
        	}
        }
        else            //get element from a list
        {
        	if(!list.contains(type))
        	{
        		el = driver.findElements(getBy(type, value)).get(index);
        	}
        	else {
                if (testType.equalsIgnoreCase("ios")) {                         //for iOS
                    if (type.equalsIgnoreCase("AccessibilityId"))
                        el = idr.findElementsByAccessibilityId(value).get(index);
                    if (type.equalsIgnoreCase("IosUIAutomation"))
                        el = idr.findElementsByIosUIAutomation(value).get(index);
                }
                if (testType.equalsIgnoreCase("android")) {                     //for Android
                    if (type.equalsIgnoreCase("AccessibilityId"))
                        el = adr.findElementsByAccessibilityId(value).get(index);
                    if (type.equalsIgnoreCase("AndroidUIAutomator"))
                        el = adr.findElementsByAndroidUIAutomator(value).get(index);
                }
            }

        }
        
        if(!waitElementToBeDisplayed(el))
            el = null;
        return el;
 
    }
	
	public static WebElement getElementNoWait(String key) {
		Map ml = null;
		int index = 0;
		WebElement el = null;
		ml = (HashMap) yamlMap.get(key);
        
        String type = ml.get("type").toString().trim();
        String value = ml.get("value").toString().trim();
        if(ml.containsKey("index")){
        	index = Integer.parseInt(ml.get("index").toString().trim());
        	if(index < 0) 				//check index value
            	throw new IndexOutOfBoundsException("Index have to greater than or equals to ZERO: [index=" + index + "]");
        }
        
        if (index == 0) {
        	el = driver.findElement(getBy(type,value));
        }
        else
        {
        	el = driver.findElements(getBy(type, value)).get(index);
        }
        
        /*
        if(index == 0) 
        {
        	if(!list.contains(type))
        	{
        		el = driver.findElement(getBy(type, value));
        	}
        	else
        	{
        		if(type.equalsIgnoreCase("AccessibilityId"))
        			el = dr.findElementByAccessibilityId(value);
        		if(type.equalsIgnoreCase("AndroidUIAutomator"))
        			el = dr.findElementByAndroidUIAutomator(value);
        		if(type.equalsIgnoreCase("IosUIAutomation"))
        			el = dr.findElementByIosUIAutomation(value);
        	}
        }
        else
        {
        	if(!list.contains(type))
        	{
        		el = driver.findElements(getBy(type, value)).get(index);
        	}
        	else
        	{
        		if(type.equalsIgnoreCase("AccessibilityId"))
        			el = dr.findElementsByAccessibilityId(value).get(index);
        		if(type.equalsIgnoreCase("AndroidUIAutomator"))
        			el = dr.findElementsByAndroidUIAutomator(value).get(index);
        		if(type.equalsIgnoreCase("IosUIAutomation"))
        			el = dr.findElementsByIosUIAutomation(value).get(index);
        	}
        }
        */

        return el;
	}
	
	private static WebElement waitForElement(final By by) {
	    WebElement element = null;
	    int waitTime = Integer.parseInt(Config.getConfig("waitTime"));
	    try {
	        	element = new WebDriverWait(driver, waitTime).until(
	        			new ExpectedCondition<WebElement>() 
	        			{
	        				public WebElement apply(WebDriver d) 
	        				{
	        					return d.findElement(by);
	        				}
	        			}
	        	);
	    } catch (Exception e) {
	        System.out.println("Element: " + by.toString() + " is not exist until " + waitTime + " seconds.");
	    }

	    return element;
	    
	}
	
	private WebElement getLocator(String key, boolean wait) {
	    WebElement element = null;
	    
	    if (yamlMap.containsKey(key)) {
	        HashMap<String, String> m = (HashMap) yamlMap.get(key);
	        String type = m.get("type");
	        String value = m.get("value");          
	        By by = getBy(type, value);
	        if (wait) {
	            element = waitForElement(by);
	            boolean flag = waitElementToBeDisplayed(element);
	            if (!flag)
	                element = null;
	        } else {
	            try {
	                element = driver.findElement(by);
	            } catch (Exception e) {
	                element = null;
	            }
	        }
	    } else
	        System.out.println("Locator " + key + " is not exist in " + yamlPath);
	    return element;
	}

	
	private static boolean waitElementToBeDisplayed(final WebElement element) {
	    boolean wait = false;
	    if (element == null)
	        return wait;
	    try {
	    		wait = new WebDriverWait(driver, 
	    								Integer.parseInt(Config.getConfig("waitTime")))
	    								.until(new ExpectedCondition<Boolean>() {
	    										public Boolean apply(WebDriver d) {
	    												return element.isDisplayed();
	    										}
	                });
	    } catch (Exception e) {
	        System.out.println(element.toString() + " is not displayed");
	    }
	    return wait;
	}

	
	private static boolean waitElementToBeNonDisplayed(final WebElement element) {
	    boolean wait = false;
	    
	    if (element == null)
	        return wait;
	    
	    try {
	        	wait = new WebDriverWait(driver, Integer.parseInt(Config.getConfig("waitTime"))).until(new ExpectedCondition<Boolean>() {
	                    	public Boolean apply(WebDriver d) {
	                    		return !element.isDisplayed();
	                    	}
	                });
	    } catch (Exception e) {
	        System.out.println("Locator [" + element.toString()
	                + "] is also displayed");
	    }
	    
	    return wait;
	}
	
	private static By getBy(String type, String value) {
	    By by = null;
	    if (type.equalsIgnoreCase("id")) {
	        by = By.id(value);
	    }
	    if (type.equalsIgnoreCase("name")) {
	        by = By.name(value);
	    }
	    if (type.equalsIgnoreCase("xpath")) {
	        by = By.xpath(value);
	    }
	    if (type.equalsIgnoreCase("className") || type.equalsIgnoreCase("class")) {
	        by = By.className(value);
	    }
	    if (type.equalsIgnoreCase("linkText")) {
	        by = By.linkText(value);
	    }
	    
	    // For mobileBy, Appium 
	    if (type.equalsIgnoreCase("AccessibilityId")){
			by = MobileBy.AccessibilityId(value);
		}
		
		if (type.equalsIgnoreCase("AndroidUIAutomator")){
			by = MobileBy.AndroidUIAutomator(value);
		}
		
		if (type.equalsIgnoreCase("IosUIAutomation")){
			by = MobileBy.IosUIAutomation(value);
		}
	    
	    return by;
	}
	
	private static Map parseYaml() {
		try{
			Yaml yaml = new Yaml();
			yamlMap = (HashMap) yaml.load(new FileInputStream(new File(yamlPath)));
		}
		catch (FileNotFoundException e){
			System.out.println("File: " + yamlPath + " was not found.");
			e.printStackTrace();
		}
				
		return yamlMap;
	}
	
	private static void initDriver() throws WebDriverInstanceException {
        testType = WebDriverManager.getTestType();
		if(testType.equalsIgnoreCase("Selenium")) {
            driver = (WebDriver) WebDriverManager.getDriverInstance();
        }

        if (testType.equalsIgnoreCase("ios"))
            idr = (IOSDriver) WebDriverManager.getDriverInstance();
        if (testType.equalsIgnoreCase("android"))
            adr = (AndroidDriver) WebDriverManager.getDriverInstance();
	}
 
}
