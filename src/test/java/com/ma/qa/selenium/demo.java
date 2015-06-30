package com.ma.qa.selenium;


import com.ma.qa.commons.CommonUtil;
import com.ma.qa.commons.Config;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anson on 4/11/14.
 */
public class demo {

    private WebDriver driver;
    private HashMap<String, String> capMap = new HashMap();
    private Map eMsg = new HashMap();
    private static String confFile = "res/conf/BOCG3RDInsurance.yaml";

    @Test(groups = { "Page_1A.SelectAge2"})
    public void ActionOnAge2TextCheck() throws Exception {
        /**
         * click building age option 2
         */
        Get.Locator("age2").click();
        CommonUtil.sleep(1);
        Assert.assertTrue("1. 您在投保任何家居財物、樓宇或個人物品保險時，曾否遭拒絕及/或被附加特別條款及/或繳付額外保費？] was not present on the page.",
                Verify.TextPresent(driver, "1. 您在投保任何家居財物、樓宇或個人物品保險時，曾否遭拒絕及/或被附加特別條款及/或繳付額外保費？"));
        Assert.assertTrue("2. 您在過去兩年內，曾否就任何家居財物、樓宇或個人物品保險申請索償？] was not present on the page.",
                Verify.TextPresent(driver, "2. 您在過去兩年內，曾否就任何家居財物、樓宇或個人物品保險申請索償？"));
        Assert.assertTrue("3. 您所投保的家居樓宇/住所於過去五年內曾進行維修水喉、電線、外牆翻新等工程？] was not present on the page.",
                Verify.TextPresent(driver, "3. 您所投保的家居樓宇/住所於過去五年內曾進行維修水喉、電線、外牆翻新等工程？"));
        Assert.assertTrue("[是] was not present on the page.",
                Verify.TextPresent(driver, "是"));
        Assert.assertTrue("[是] was not present on the page.",
                Verify.TextPresent(driver, "否"));

        /**
         * answer the third questions
         */
        Get.Locator("1AQ3").click();
        Assert.assertTrue("[(保單的自負額將增加至水浸損失及水浸引致第三者財物損毀或人身傷亡的索償為港幣15,000或損失額之10%， 以較高者為準。)] was not present on the page.",
                Verify.TextPresent(driver, "(保單的自負額將增加至水浸損失及水浸引致第三者財物損毀或人身傷亡的索償為港幣15,000或損失額之10%， 以較高者為準。)"));

    }

    public void Login() throws Exception {
        Get.LocatorNoWait("loginBtn").click();
        CommonUtil.sleep(1);

        Assert.assertTrue("[網上投保] was not present on the page.",
                Verify.TextPresent(driver, "網上投保"));
        Assert.assertTrue("[周全家居綜合險] was not present on the page.",
                Verify.TextPresent(driver, "周全家居綜合險"));

    }

    @BeforeMethod
    public void setup() throws Exception {
        Config.setYamlFilePath(confFile);
        capMap.put("testType", Config.getTestType());
        capMap.put("browserName", Config.getBrowserName());
        capMap.put("url", Config.getURL());

        WebDriverManager.initDriver(capMap);
        WebDriverManager.startWebDriver();
        driver = (WebDriver) WebDriverManager.getDriverInstance();

        Get.setConfFile(confFile);

        Login();
    }

    @AfterMethod
    public void teardown() throws Exception {
        driver.quit();

    }
}
