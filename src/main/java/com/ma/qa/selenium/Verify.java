package com.ma.qa.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Anson on 15/10/14.
 */
public class Verify {

    /**
     * verify text is present on the page or not.
     * @param driver
     *        WebDriver instance
     * @param text
     *        the text need to verify
     * @return boolean value
     */
    public static boolean TextPresent(WebDriver driver, String text) {
        try{
            return driver.findElement(By.tagName("body")).getText().contains(text);
        }
        catch(Exception e){
            return false;
        }

    }
}
