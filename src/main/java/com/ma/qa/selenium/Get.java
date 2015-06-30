package com.ma.qa.selenium;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.yaml.snakeyaml.Yaml;

import com.ma.qa.exception.NoSuchLocatorException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anson on 24/10/14.
 */
public class Get {

    private static WebDriver driver;
    private static IOSDriver idr;
    private static AndroidDriver adr;
    private static Map yamlMap = null;
    private static String locatorPath = null;
    private static String confFile = null;
    private static int waitTime = 0;
    private static final int defaultWaitTime = 7;


    /**
     * conf configration yaml file path in string
     * @param conf
     */
    public static void setConfFile(String conf) {
        confFile = conf;
        parseLocatorYaml();
        driver = WebDriverManager.getWebDriverInstance();
    }

    /**
     *
     * @param key
     * @return
     * @throws NoSuchLocatorException
     */
    public static WebElement Locator(String key) throws NoSuchLocatorException {
        return getElement(key, true);
    }

    /**
     *
     * @param key
     * @return
     * @throws NoSuchLocatorException
     */
    public static WebElement LocatorNoWait(String key) throws NoSuchLocatorException {
        return getElement(key, false);
    }

    /**
     *
     * @param key
     * @param wait
     * @return
     * @throws NoSuchLocatorException
     * @throws IndexOutOfBoundsException
     */
    private static WebElement getElement(String key, boolean wait) throws NoSuchLocatorException, IndexOutOfBoundsException{
        WebElement element = null;

        if (yamlMap.containsKey(key)) {
            Map m = (HashMap) yamlMap.get(key);
            String type = m.get("type").toString();
            String value = m.get("value").toString();
            int index = 0;
            boolean list = false;

            if (m.containsKey("index")) {
                int idx = Integer.parseInt(m.get("index").toString());
                if (idx > 0) {
                    index = idx;
                    list = true;
                }
                else if (idx < 0)
                    throw new IndexOutOfBoundsException(
                                    "\n************************************************\n" +
                                    "Locator: [" + key + "].\n" +
                                    "Locator file path: [" + locatorPath + "].\n" +
                                    "Reason: Index of locator is illegal.\n" +
                                    "************************************************");
            }

            By by = getBy(type, value);
            if (wait) {
                if (!list)
                    element = waitForElement(by);
                else
                    element = waitForElements(by, index);

                boolean flag = waitElementToBeDisplayed(element);
                if (!flag)
                    element = null;
            } else {
                try {
                    if (!list)
                        element = driver.findElement(by);
                    else
                        element = driver.findElements(by).get(index);
                } catch (Exception e) {
                    element = null;
                }
            }
        }
        else
            throw new NoSuchLocatorException(
                    "Locator: [" + key + "]\n" +
                    "Locator file path: [" + locatorPath + "]\n" +
                    "Reason: Locator is not exist in locator file."
            );



        return element;
    }

    /**
     * Generate a By object of WebElement object for locating a locator of page.
     * @param type WebElement found by type, id, name, xpath, className, linkText.
     * @param value WebElement found via the specify value of type.
     * @return By object
     */
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
        if (type.equalsIgnoreCase("className")) {
            by = By.className(value);
        }
        if (type.equalsIgnoreCase("linkText")) {
            by = By.linkText(value);
        }

        // TODO: Check BY object for mobile
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

    //TODO: Check waritForElement function whether support mobile element
    /**
     * get webelement within the waiting time.
     * @param by By object
     * @return WebElement object
     */
    private static WebElement waitForElement(final By by) {
        WebElement element = null;

        try {
            element = new WebDriverWait(driver, waitTime)
                    .until(new ExpectedCondition<WebElement>() {
                        public WebElement apply(WebDriver d) {
                            return d.findElement(by);
                        }
                    });
        } catch (Exception e) {
            System.out.println(by.toString() + " is not exist until " + waitTime);
        }

        return element;
    }

    /**
     * get webelement from a webelement list within the waiting time.
     * @param by By object
     * @param index index of element list
     * @return
     */
    private static WebElement waitForElements(final By by, final int index) {
        WebElement element = null;

        try {
            element = new WebDriverWait(driver, waitTime)
                    .until(new ExpectedCondition<WebElement>() {
                        public WebElement apply(WebDriver d) {
                            return d.findElements(by).get(index);
                        }
                    });
        } catch (Exception e) {
            System.out.println(by.toString() + " is not exist until " + waitTime);
        }
        return element;
    }

    /**
     * waiting for specify element to be display
     * @param element
     * @return
     */
    private static boolean waitElementToBeDisplayed(final WebElement element) {
        boolean wait = false;
        if (element == null)
            return wait;
        try {
            wait = new WebDriverWait(driver, waitTime)
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

    /**
     * wait for the element to be displayed in waiting time.
     * @param element the element waiting to be displayed.
     * @return boolean for the element displayed or not.
     */
    public static boolean waitElementToBeNonDisplayed(final WebElement element) {
        boolean wait = false;
        if (element == null)
            return wait;
        try {
            wait = new WebDriverWait(driver, waitTime)
                    .until(new ExpectedCondition<Boolean>() {
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


    /**
     *
     */
    private static void parseLocatorYaml() {
        Map confMap = null;
        Yaml yaml = new Yaml();
        int wt = 0;

        try {
            /**
             * get locator yaml file from configration yaml file of project.
             */
            confMap = (HashMap) yaml.load(new FileInputStream(new File(confFile)));
            locatorPath = confMap.get("locatorFile").toString();

            /**
             * get waiting time of locator to appear on the page/DOM from configration yaml file of project.
             * else set waiting time of locator to default waiting time 7 seconds.
             */
            if (confMap.containsKey("waitTime"))
                wt = Integer.parseInt(confMap.get("waitTime").toString());

            if (wt > 0)
                waitTime = wt;
            else
                waitTime = defaultWaitTime;

        }
        catch (FileNotFoundException e){
            System.out.println("File: [" + confFile + "] was not found.");
            e.printStackTrace();
        }

        try {
            /**
             * get locator map from locator yaml file.
             */
            yamlMap = (HashMap) yaml.load(new FileInputStream(new File(locatorPath)));
        }
        catch (FileNotFoundException e) {
            System.out.println("File: [" + locatorPath + "] was not found.");
            e.printStackTrace();
        }

    }


}
