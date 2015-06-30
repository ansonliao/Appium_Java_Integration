package com.ma.qa.selenium;

import com.ma.qa.commons.CommonUtil;
import com.ma.qa.commons.Config;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anson on 15/10/14.
 */
public class testcase_BOCG3RDIns_Wording_Check_1A_1 {

    private WebDriver driver;
    private HashMap<String, String> capMap = new HashMap();
    private Map eMsg = new HashMap();
    private static String confFile = "res/conf/BOCG3RDInsurance.yaml";

    @BeforeMethod
    public void setup() throws Exception {
        Config.setYamlFilePath(confFile);
        capMap.put("testType", Config.getTestType());
        capMap.put("browserName", Config.getBrowserName());
        capMap.put("url", Config.getURL());

        WebDriverManager.initDriver(capMap);
        WebDriverManager.startWebDriver();
        driver = (WebDriver) WebDriverManager.getDriverInstance();

        //Locator.setYamlFile("locator/locatorInCht.yaml");
        Get.setConfFile(confFile);

        Login();
    }

    @AfterMethod
    public void teardown() {
        driver.quit();

    }

    @Test (groups = { "Page_1A.NoAction"})
    public void NoActionTextCheckCht() throws Exception {
        Assert.assertTrue("[投保資料 詳情] was not present on the page.",
                Verify.TextPresent(driver, "投保資料 詳情"));
        Assert.assertTrue("[(可獨立投保項目I或項目III) (如適用)] was not present on the page.",
                Verify.TextPresent(driver, "(可獨立投保項目I或項目III) (如適用)"));
        Assert.assertTrue("[I. 家居財物保障] was not present on the page.",
                Verify.TextPresent(driver, "I. 家居財物保障"));
        Assert.assertTrue("[投保家居住所面積 (平方尺)] was not present on the page.",
                Verify.TextPresent(driver, "投保家居住所面積 (平方尺)"));
        Assert.assertTrue("[II. 家居財物自選保障] was not present on the page.",
                Verify.TextPresent(driver, "II. 家居財物自選保障"));
        Assert.assertTrue("[(適用於業主 (自住) 或租客) (必須投保項目I方可選擇項目II)] was not present on the page.",
                Verify.TextPresent(driver, "(適用於業主 (自住) 或租客) (必須投保項目I方可選擇項目II)"));
        Assert.assertTrue("[(適用於業主 (自住) 或業主 (出租)) (可獨立投保)] was not present on the page.",
                Verify.TextPresent(driver, "(適用於業主 (自住) 或業主 (出租)) (可獨立投保)"));
        Assert.assertTrue("[您的投保樓宇/住宅樓齡是] was not present on the page.",
                Verify.TextPresent(driver, "您的投保樓宇/住宅樓齡是"));
        Assert.assertTrue("[(註︰網上投保只適用於樓齡不超過45年的家居樓宇/住所。)] was not present on the page.",
                Verify.TextPresent(driver, "(註︰網上投保只適用於樓齡不超過45年的家居樓宇/住所。)"));
        Assert.assertTrue("[起有效1年] was not present on the page.",
                Verify.TextPresent(driver, "起有效1年"));
        Assert.assertTrue("[起有效1年] was not present on the page.",
                Verify.TextPresent(driver, "起有效1年"));
        Assert.assertTrue("[40年或以下] was not present on the page.",
                Verify.TextPresent(driver, "40年或以下"));
        Assert.assertTrue("[41至45年] was not present on the page.",
                Verify.TextPresent(driver, "41至45年"));
        Assert.assertTrue("[承保期] was not present on the page.",
                Verify.TextPresent(driver, "承保期"));
        Assert.assertTrue("[請輸入中銀信用卡首6個位卡號，以享優惠。] was not present on the page.",
                Verify.TextPresent(driver, "請輸入中銀信用卡首6個位卡號，以享優惠。"));
        Assert.assertTrue("[優惠編碼 (如適用)] was not present on the page.",
                Verify.TextPresent(driver, "優惠編碼 (如適用)"));
        Assert.assertTrue("[常見問題] was not present on the page.",
                Verify.TextPresent(driver, "常見問題"));
        Assert.assertTrue("[聯絡本公司] was not present on the page.",
                Verify.TextPresent(driver, "聯絡本公司"));
        Assert.assertTrue("[保費表] was not present on the page.",
                Verify.TextPresent(driver, "保費表"));
        Assert.assertTrue("[保障範圍] was not present on the page.",
                Verify.TextPresent(driver, "保障範圍"));

    }

    @Test (groups = { "Page_1A.ActionOnItem2"})
    public void ActionOnItem2TextCheck() throws Exception {
        /**
         * select a plan of insurance
         */
        ElementUtil.click(Get.LocatorNoWait("item1Selector"));
        ElementUtil.click(Get.Locator("plan1"));

        ElementUtil.click(Get.LocatorNoWait("item1Selector"));

        /**
         * click item 2 radio button
         */
        ElementUtil.click(Get.LocatorNoWait("item2"));
        CommonUtil.sleep(1);
        Assert.assertTrue("[家傭人數 詳情] was not present on the page.",
                Verify.TextPresent(driver, "家傭人數 詳情"));
        Assert.assertTrue("(投保限制：投保家傭必須為18-60歲)] was not present on the page.",
                Verify.TextPresent(driver, "(投保限制：投保家傭必須為18-60歲)"));
        Assert.assertTrue("全球個人物品附加保障] was not present on the page.",
                Verify.TextPresent(driver, "全球個人物品附加保障"));

    }

    @Test (groups = { "Page_1A.SelectAge2"})
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
        Get.LocatorNoWait("1AQ3").click();
        Assert.assertTrue("[(保單的自負額將增加至水浸損失及水浸引致第三者財物損毀或人身傷亡的索償為港幣15,000或損失額之10%， 以較高者為準。)] was not present on the page.",
                Verify.TextPresent(driver, "(保單的自負額將增加至水浸損失及水浸引致第三者財物損毀或人身傷亡的索償為港幣15,000或損失額之10%， 以較高者為準。)"));




    }

    @Test (groups = { "Page_1A.ErrorMessage"})
    public void ActionOnIncorrectTextCheck() throws Exception {
        /**
         * 1. nothing select to click submit button
         */
        Get.LocatorNoWait("1ASubmit").click();
        Assert.assertTrue("[輸入錯誤，請重新輸入] was not present on the page.",
                Verify.TextPresent(driver, "輸入錯誤，請重新輸入"));

        /**
         * 1. select a plan of insurance
         * 2. no select area type
         */
        Get.LocatorNoWait("item1Selector").click();
        Get.Locator("plan1").click();
        Get.LocatorNoWait("1ASubmit").click();
        Assert.assertTrue("[輸入錯誤，請重新輸入] was not present on the page.",
                Verify.TextPresent(driver, "輸入錯誤，請重新輸入"));

        /**
         * 1. select item 2
         * 2. nothing selected in details of item 2
         */
        Get.LocatorNoWait("crossArea").click();
        Get.Locator("item2").click();
        Assert.assertTrue("[輸入錯誤，請重新輸入] was not present on the page.",
                Verify.TextPresent(driver, "輸入錯誤，請重新輸入"));

        /**
         * 1. set yes to Q1
         * 2. set yes to Q2
         * 3. set yes to Q3
         */
        Get.Locator("1AQ1Y").click();
        Get.Locator("1AQ2Y").click();
        Get.Locator("1AQ3Y").click();
        Get.LocatorNoWait("1ASubmit").click();
        Assert.assertTrue("[按您提供的投保資料，抱歉未能為您提供此服務，如需協助，請致電3187 5100！] was not present on the page.",
                Verify.TextPresent(driver, "按您提供的投保資料，抱歉未能為您提供此服務，如需協助，請致電3187 5100！"));

        /**
         * 1. set no to Q1
         * 2. set yes to Q2
         * 3. set yes to Q3
         */
        Get.LocatorNoWait("1AQ1N").click();
        Get.LocatorNoWait("1AQ2Y").click();
        Get.LocatorNoWait("1AQ3Y").click();
        Get.LocatorNoWait("1ASubmit").click();
        Assert.assertTrue("[按您提供的投保資料，抱歉未能為您提供此服務，如需協助，請致電3187 5100！] was not present on the page.",
                Verify.TextPresent(driver, "按您提供的投保資料，抱歉未能為您提供此服務，如需協助，請致電3187 5100！"));

        /**
         * 1. set yes to Q1
         * 2. set no to Q2
         * 3. set yes to Q3
         */
        Get.LocatorNoWait("1AQ1Y").click();
        Get.LocatorNoWait("1AQ2N").click();
        Get.LocatorNoWait("1AQ3Y").click();
        Get.LocatorNoWait("1ASubmit").click();
        Assert.assertTrue("[按您提供的投保資料，抱歉未能為您提供此服務，如需協助，請致電3187 5100！] was not present on the page.",
                Verify.TextPresent(driver, "按您提供的投保資料，抱歉未能為您提供此服務，如需協助，請致電3187 5100！"));

        /**
         * 1. set no to Q1
         * 2. set no to Q2
         * 3. set no to Q3
         */
        Get.LocatorNoWait("1AQ1N").click();
        Get.LocatorNoWait("1AQ2N").click();
        Get.LocatorNoWait("1AQ3N").click();
        Get.LocatorNoWait("1ASubmit").click();
        Assert.assertTrue("[按您提供的投保資料，抱歉未能為您提供此服務，如需協助，請致電3187 5100！] was not present on the page.",
                Verify.TextPresent(driver, "按您提供的投保資料，抱歉未能為您提供此服務，如需協助，請致電3187 5100！"));

        /**
         * 1. input promotion code
         * 2. no click Declaration option
         */
        Get.LocatorNoWait("1ACreditNo").sendKeys("600000");
        Get.LocatorNoWait("1APromo").sendKeys("123456");
        Get.LocatorNoWait("1ASubmit").click();
        Assert.assertTrue("[輸入錯誤，請重新輸入] was not present on the page.",
                Verify.TextPresent(driver, "輸入錯誤，請重新輸入"));

        CommonUtil.sleep(10);


    }



    public void Login() throws Exception {
        Get.LocatorNoWait("loginBtn").click();
        CommonUtil.sleep(1);

        Assert.assertTrue("[網上投保] was not present on the page.",
                Verify.TextPresent(driver, "網上投保"));
        Assert.assertTrue("[周全家居綜合險] was not present on the page.",
                Verify.TextPresent(driver, "周全家居綜合險"));

    }
}
