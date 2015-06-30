package com.ma.qa.selenium;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.ma.qa.exception.WebDriverInstanceException;

public class WebDriverManager {

	private static WebDriver driver = null;
    private static IOSDriver idr = null;
    private static AndroidDriver adr = null;
	private static File classpathRoot = new File(System.getProperty("user.dir"));
	private static HashMap<String, String> hashMap = new HashMap<String, String>();
	public static String 	testType = null;
	private static String	appDir = null,
							app = null,
							url = null,
							browserName = null,
							deviceName = null,
							platformVersion = null,
							os = System.getProperty ("os.name"),
							separator = System.getProperties().getProperty("file.separator");

	
	
	public static void setDriver (WebDriver wd){
		driver = wd;
	}
	//public static WebDriver getDriverInstance() {
	//	return driver;
	//}
	
	public static String getTestType() {
		return testType;
	}
	
	public static void clear() {		//reset
		driver = null;
        idr = null;
        adr = null;
		testType = null;
		appDir = null;
		app = null;
		url = null;
		browserName = null;
		deviceName = null;
		platformVersion = null;
	}

    /**
	public static Object startDriver() throws Exception {
		if (testType.equalsIgnoreCase("appium")){
			if ((url == null) || (url.length() == 0)){
				File appPath = new File(appDir);
			    File appFileName = new File(appPath, app);
			    DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, browserName);
                capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
                //capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
                capabilities.setCapability(MobileCapabilityType.APP, appFileName.getAbsolutePath());
                capabilities.setCapability("nativeWebTap", true);
                driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			}
		}
		else if(testType.equalsIgnoreCase("selenium")){			
			if (browserName.trim().equalsIgnoreCase("firefox")){
				driver = new FirefoxDriver();
				driver.get(url);
			}
			if (browserName.trim().equalsIgnoreCase("ie")){
				//add code here
			}
			if (browserName.trim().equalsIgnoreCase("chrome")){
				//add code here
			}
			if (browserName.trim().equalsIgnoreCase("safari")){
				//add code here
			}
		}
		return driver;
	}
     */

    /**
     * start a driver for iOS test
     * @return idr IOSDriver instance
     * @throws MalformedURLException
     */
    public static IOSDriver startIOSDriver() throws MalformedURLException {
        File appPath = new File(appDir);
        File appFileName = new File(appPath, app);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, browserName);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        capabilities.setCapability(MobileCapabilityType.APP, appFileName.getAbsolutePath());
        idr = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        return idr;
    }

    /**
     * start a driver for Android test
     * @return adr AndroidDriver instance
     * @throws MalformedURLException
     */
    public static AndroidDriver startAndroidDriver() throws MalformedURLException {
        File appPath = new File(appDir);
        File appFileName = new File(appPath, app);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, browserName);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        capabilities.setCapability(MobileCapabilityType.APP, appFileName.getAbsolutePath());
        adr = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        return adr;
    }

    /**
     * start a driver for website of desktop platform test, i.e. Windows OS, Mac OS X
     * @return driver WebDriver instance
     */
    public static WebDriver startWebDriver() {
        if (browserName.trim().equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();
            driver.get(url);
        }
        if (browserName.trim().equalsIgnoreCase("ie")){
            //add code here
        }
        if (browserName.trim().equalsIgnoreCase("chrome")){
            //add code here
        }
        if (browserName.trim().equalsIgnoreCase("safari")){
            //add code here
        }

        return driver;
    }

    /**
     * close test session
     */
	public static void stopDriver() {
        if (driver != null)
		    driver.close();
        if (idr != null)
            ((WebDriver) idr).close();
        if (adr != null)
            adr.close();

	}

    /**
	public static void setAppiumDriver(AppiumDriver driver) {
		dr = driver;
	}
     */

    /**
     * get WebDriver instance, includes IOSDriver, AndroidDriver, website WebDriver of desktop platform
     * @return WebDriver, IOSDriver, AndroidDriver instance,
     * @throws WebDriverInstanceException
     */
    public static Object getDriverInstance() throws WebDriverInstanceException {
        Object obj = null;
        driverInstanceCheck();
        if (idr != null)
            obj = idr;
        if (adr != null)
            obj = adr;
        if (driver != null)
            obj = driver;

        return obj;
    }

    /**
     * check WebDriver instance, only one instance to be initialized in one test session
     * @throws WebDriverInstanceException
     */
    private static void driverInstanceCheck() throws WebDriverInstanceException {
        int instanceNo = 0;
        if (idr != null)
            ++instanceNo;
        if (adr != null)
            ++instanceNo;
        if (driver != null)
            ++instanceNo;

        if (instanceNo == 0)
            throw new WebDriverInstanceException("No WedDriver instance created.");

        if (instanceNo > 1)
            throw new WebDriverInstanceException("Multiple WebDriver instances created, only one WebDriver instance to be initialized.");
    }

    /**
     * get IOSDriver instance
     * @return idr: IOSDriver instance
     */
    public static IOSDriver getIOSDriverInstance() {
        return idr;
    }

    /**
     * get AndroidDriver instance
     * @return adr: AndroidDriver instance
     */
    public static AndroidDriver getAndroidDriverInstance() {
        return adr;
    }

    /**
     * get WebDriver instance, this instance only for website of desktop platform
     * @return driver: WebDriver instance
     */
    public static WebDriver getWebDriverInstance() {
        return driver;
    }

    /**
	public static AppiumDriver getAppiumDriverInstance() {
		return dr;
	}
     */


    /**
     * initialize test parameters
     * @param infoMap store test parameters.
     */
	public static void initDriver(HashMap <String, String> infoMap) {
		
		int typeCode = 0;
		testType = infoMap.get("testType").trim();
		if (testType.equalsIgnoreCase("selenium"))
			typeCode = 1;

        /**
		if (testType.equalsIgnoreCase("appium"))
			typeCode = 2;
         */

        if (testType.equalsIgnoreCase("ios"))
            typeCode = 2;

        if (testType.equalsIgnoreCase("android"))
            typeCode = 3;
		
		switch (typeCode)
		{
			case 1:
				browserName = infoMap.get("browserName").trim();
				url = infoMap.get("url").trim();
				break;
			case 2:
				appDir = infoMap.get("appDir").trim();
				app = infoMap.get("app").trim();
				browserName = infoMap.get("browserName").trim();
				deviceName = infoMap.get("deviceName").trim();
				platformVersion = infoMap.get("platformVersion").trim();
            case 3:
                appDir = infoMap.get("appDir").trim();
                app = infoMap.get("app").trim();
                browserName = infoMap.get("browserName").trim();
                deviceName = infoMap.get("deviceName").trim();
                break;

			default:

		}
		
		/***
		testType = SessionType;
		appDir = AppDir;
		app = App;
		url = URL;
		browserNAME = BrowserNAME;
		device = Device;
		capPLATFORM = CapPLATFORM;
		capVERSION = CapVERSION;
		***/
	}

}