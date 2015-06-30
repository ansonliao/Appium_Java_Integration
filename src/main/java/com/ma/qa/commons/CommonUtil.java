package com.ma.qa.commons;

public class CommonUtil {
	
	public static void sleep(long sleepTimeBySecond) throws InterruptedException, Exception{
		if((sleepTimeBySecond <= 0) || (sleepTimeBySecond > 36000)){
	           throw new Exception("ERROR!!! Sleep time by second have to great than ZERO and less than 3600 ((0, 36000]).");
	    }
		Thread.sleep(sleepTimeBySecond * 1000);
	}

}
