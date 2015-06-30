package com.ma.qa.exception;

/**
 * Created by Anson on 9/10/14.
 */
public class WebDriverInstanceException extends MAException{
    public WebDriverInstanceException(String reason) {
        super(reason);
    }

    public WebDriverInstanceException(String reason, Throwable cause) {
        super(reason, cause);
    }

}
