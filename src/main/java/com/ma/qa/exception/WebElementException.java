package com.ma.qa.exception;

/**
 * Created by Anson on 16/12/14.
 */
public class WebElementException extends MAException{
    public WebElementException(String reason) {
        super(reason);
    }

    public WebElementException(String reason, Throwable cause) {
        super(reason, cause);
    }

}
