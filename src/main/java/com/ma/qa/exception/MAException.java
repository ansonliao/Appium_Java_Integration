package com.ma.qa.exception;

/**
 * Created by Anson on 4/11/14.
 */
public class MAException extends Exception {

    public MAException(String message) {
        super(
                "\n************************************************************************************************\n" +
                message +
                "\n************************************************************************************************"
        );
    }

    public MAException(String message, Throwable cause) {
        super(message, cause);
    }
}
