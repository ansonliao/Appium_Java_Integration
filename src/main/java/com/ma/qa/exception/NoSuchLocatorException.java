package com.ma.qa.exception;


public class NoSuchLocatorException extends MAException{
	public NoSuchLocatorException(String reason) {
	    super(reason);
	  }

	public NoSuchLocatorException(String reason, Throwable cause) {
	    super(reason, cause);
	  }

}
