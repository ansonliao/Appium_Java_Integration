package com.ma.qa.commons;

import java.io.File;

public class Constants {
	
	/**
	 * The directory for Sikuli PowerPoint file. 
	 */
	public static final String PPTX_DIRECTORY = "res" + File.separator + "PowerPoint";
	/**
	 * The directory for Shell Script. 
	 */
	public static final String SHELL_DIRECTORY= "bin";
	/**
	 * The directory for execution log. 
	 */
	public static final String LOG_DIRECTORY = "log";
	/**
	 * The directory for Shell Script execution log file. 
	 */
	public static final String SHELL_EXECUTION_LOG = "ShellExecutionLog.txt";
	/**
	 * The directory for screenshot. 
	 */
	public static final String SCREENSHOT_DIRECTORY = "target" + File.separator + "surefire-reports" + File.separator + "Screenshots";
	/**
	 * The Jenkins link of workspace 
	 * i.e. http://jenkins.motherapp.com/job/Sikuli_HKTDCMag_iPhone/ws/000063/2014-06-12%2003:17:40.174_1V66Z1ZKVHuWbO0bLrhJ2yDHg9hyMrcF4o8-ljL8uaKM_Input.png
	 * http://localhost:8080/job/Appium_BBC_ST/ws/TestHistory/000042/
	 */
	//public static final String JENKINS_WS_DIRECTORY = "http://localhost:8080/job/" + System.getProperty("jobName") + "/ws/TestHistory/"
	//												+ String.format("%06d", Integer.parseInt(System.getProperty("buildNumber")));
	public static final String JENKINS_WS_DIRECTORY = "http://jenkins.motherapp.com/job/" + System.getProperty("jobName") + "/ws";

}
