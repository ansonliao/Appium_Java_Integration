package com.ma.qa.selenium;

import com.ma.qa.exception.ConfigurationException;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anson on 16/12/14.
 */
public class get_cons {

    private String  testMode = null,
                    confFile = null,
                    locatorFile = null;
    private Map confMap = null;
    private int timeoutInSecond = 0, DEFAULT_TIMEOUT = 5;


    /**
     *
     * @param confFile
     */
    public get_cons (String confFile) throws IOException {
        this.confFile = confFile.trim();
    }

    /**
     *
     * @param confMap
     */
    public get_cons(Map confMap) {
        this.confMap = confMap;
    }


    /**
     *
     * @param type
     * @param value
     * @return
     */
    private By getBy(String type, String value) {
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

    /**
    private WebElement waitForElement(final By by) {
        WebElement element = null;

        try {
            element = new WebDriverWait(driver, waitTime)
                    .until(new ExpectedCondition<WebElement>() {
                        public WebElement apply(WebDriver d) {
                            return d.findElement(by);
                        }
                    });

            if (this.testMode.equalsIgnoreCase("selenium")) {
                element = WebDriverWait()
            }
        } catch (Exception e) {
            System.out.println(by.toString() + " is not exist until " + waitTime);
        }

        return element;
    }
     */

    /**
     * get test mode from configuration yaml file
     * @param confMap configuration yaml file in yaml
     * @return test mode in string
     */
    private String getTestMode(HashMap confMap) {
        return confMap.get("TestMode").toString();
    }

    /**
     * get timeout from configuration yaml file
     * @param confMap configuration yaml file in yaml
     * @return timeout
     */
    private int getWaitTime(HashMap confMap) {
        return Integer.parseInt(confMap.get("TimeoutInSecond").toString());
    }

    /**
     * get locator file path from configuration yaml file
     * @param confMap configuration yaml file in yaml
     * @return locator file path in string
     */
    private String getLocatorYamlFilePath(HashMap confMap) {
        return confMap.get("LocatorFile").toString();
    }

    /**
     * get configurations from configuration yaml file
     * @param confMap
     * @throws ConfigurationException
     */
    private void getConf(HashMap confMap) throws ConfigurationException {
        //get test mode
        if (!confMap.containsKey("TestMode"))
            throw new ConfigurationException("Test Mode setting was not found in Configuration YAML file.");
        String test_mode = confMap.get("TestMode").toString().trim();
        if ((test_mode == null) || (test_mode.trim().equals("")))
            throw new ConfigurationException("Test mode setting should not be empty or null.");
        this.testMode = test_mode;

        //get locator yaml file path
        if (!confMap.containsKey("LocatorFile"))
            throw new ConfigurationException("Locator YAML file path setting was not found in Configuration YAML file.");
        String lf = confMap.get("LocatorFile").toString();
        if ((lf == null) || (lf.trim().equals("")))
            throw new ConfigurationException("Locator file path setting should not be empty or null.");
        this.locatorFile = lf;

        //get timout setting
        if (confMap.containsKey("TimeoutInSecond")) {
            int wt = Integer.parseInt(confMap.get("TimeoutInSecond").toString());
            if (wt < 0)
                throw new ConfigurationException("Timeout value should be positive integer. [" + wt + "]");
            if (wt == 0)
                this.timeoutInSecond = this.DEFAULT_TIMEOUT;
            if (wt > 0)
                this.timeoutInSecond = Integer.parseInt(confMap.get("TimeoutInSecond").toString());
        }
        else
            this.timeoutInSecond = this.DEFAULT_TIMEOUT;

    }







}
