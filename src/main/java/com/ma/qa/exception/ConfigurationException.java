package com.ma.qa.exception;

/**
 * Created by Anson on 16/12/14.
 */
public class ConfigurationException extends MAException{
    public ConfigurationException(String reason) {
        super(reason);
    }

    public ConfigurationException(String reason, Throwable cause) {
        super(reason, cause);
    }

}
