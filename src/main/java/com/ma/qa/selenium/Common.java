package com.ma.qa.selenium;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

public class Common {
	
	public static Point getCentre(WebElement el) {
		Point upperLeft = el.getLocation();
	    Dimension dimensions = el.getSize();
	    return new Point(upperLeft.getX() + dimensions.getWidth()/2, upperLeft.getY() + dimensions.getHeight()/2);
	}

}
