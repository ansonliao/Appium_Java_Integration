package com.ma.qa.selenium;

import io.appium.java_client.AppiumDriver;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ma.qa.commons.CommonUtil;

public class WBAction {
	
	private static WebDriver driver = null;
    private static IOSDriver idr = null;
    private static AppiumDriver adr = null;
    private static String testType = null;
	
	public static void click(WebElement el) throws InterruptedException, Exception {
		el.click();
		CommonUtil.sleep(1);
	}
	
	public static void dragAndDrop(WebElement startEl, WebElement endEl, int duration) throws Exception {
        if ((driver == null) && (idr == null) && (adr == null))
            getDriverInstance();
		
		AppiumDriver dr = (AppiumDriver) driver;

        if (testType.equalsIgnoreCase("ios")) {
            idr.swipe(
                    Common.getCentre(startEl).getX(),
                    Common.getCentre(startEl).getY(),
                    Common.getCentre(endEl).getX(),
                    Common.getCentre(endEl).getY(),
                    duration
            );
        }
        if (testType.equalsIgnoreCase("android")) {
            adr.swipe(
                    Common.getCentre(startEl).getX(),
                    Common.getCentre(startEl).getY(),
                    Common.getCentre(endEl).getX(),
                    Common.getCentre(endEl).getY(),
                    duration
            );
        }

		CommonUtil.sleep(1);		
		
	}
	
	public static void dragAndDrop(WebElement startEl, int endX, int endY, int duration) throws Exception {
        if ((driver == null) && (idr == null) && (adr == null))
            getDriverInstance();

        if (testType.equalsIgnoreCase("ios")) {
            idr.swipe(
                    Common.getCentre(startEl).getX(),
                    Common.getCentre(startEl).getY(),
                    endX,
                    endY,
                    duration);
        }
        if (testType.equalsIgnoreCase("android")) {
            adr.swipe(
                    Common.getCentre(startEl).getX(),
                    Common.getCentre(startEl).getY(),
                    endX,
                    endY,
                    duration);
        }

		CommonUtil.sleep(1);
	}
	
	public static void dragAndDrop(int startX, int startY, int endX, int endY, int duration) throws Exception {
		if ((driver == null) && (idr == null) && (adr == null))
            getDriverInstance();

        if (testType.equalsIgnoreCase("ios"))
            idr.swipe(startX, startY, endX, endY, duration);
        if (testType.equalsIgnoreCase("android"))
            adr.swipe(startX, startY, endX, endY, duration);

        /** Selenium no swipe method
        if (testType.equalsIgnoreCase("selenium"))
            driver.swipe(startX, startY, endX, endY, duration);
         */

		CommonUtil.sleep(1);
	}

    public static void sendKeys(WebElement el, String str) {
        el.sendKeys(str);
    }

    public static void clear(WebElement el) {
        el.clear();
    }
	
	private static void getDriverInstance () {
		//setDriver(WebDriverManager.getDriverInstance());
        if (testType.equalsIgnoreCase("ios"))
            idr = WebDriverManager.getIOSDriverInstance();
        if (testType.equalsIgnoreCase("android"))
            adr = WebDriverManager.getAndroidDriverInstance();
        if (testType.equalsIgnoreCase("selenium"))
            driver = WebDriverManager.getWebDriverInstance();

	}

    public static void getTestType() {
        testType = WebDriverManager.getTestType();
    }
	
	public static void clear() {
		driver = null;
	}


}
