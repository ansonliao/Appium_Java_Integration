package com.ma.qa.file;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GetElement {
	private static Map elemMap = null;
	private static WebDriver driver;

	
	public GetElement(WebDriver driver, Map elemMap) {
		this.elemMap = elemMap;
		this.driver = driver;
		
	}
	
	public WebElement getElement(String key) {
		int elemSize;
		boolean elemGot = false;
		WebElement elem = null;
		Map ElemMap = null;
		
		System.out.println("paserElement: " + key);
		ElemMap = (HashMap) this.elemMap.get(key);
		while (!elemGot){
			//get properties no.
			elemSize = ElemMap.size();
			System.out.println("elem size: " + elemSize);
			//get id
			String id = String.format("%03d", 
					Integer.parseInt(ElemMap.get("id").toString().trim()));
			System.out.println("id: " + id);
			//get list
			int list = Integer.parseInt(ElemMap.get("list").toString().trim());
			System.out.println("list: " + list);
			//get index
			int index = Integer.parseInt(ElemMap.get("index").toString().trim());
			System.out.println("index: " + index);
			//get type
			String type = ElemMap.get("type").toString().trim();
			System.out.println("type: " + type);
			//get value
			String value = ElemMap.get("value").toString().trim();
			System.out.println("value: " + value);
			
			//for debug
			System.out.println("keyset: " + ElemMap.keySet());
			System.out.println("id: " + id);
			System.out.println("list: " + list);
			System.out.println("index: " + index);
			System.out.println("type: " + type);
			System.out.println("value: " + value);
			
			try {
				if (elem == null){
					if (list == 0){
						elem = this.driver.findElement(this.getBy(type, value));
					}
					else if (list == 1) {
						elem = (this.driver.findElements(this.getBy(type, value))).get(index);
					}
				}
				else {
					if (list == 0){
						elem = elem.findElement(this.getBy(type, value));
					}
					else if (list == 1) {
						elem = elem.findElements(this.getBy(type, value)).get(index);
					}
				}
			}catch (Exception e){
				System.out.println("====> ERROE!!! paserElement fail.");
			}
			
			
			if (ElemMap.keySet().contains("child") && ElemMap.get("child").getClass().getSimpleName().equals("LinkedHashMap")){
				System.out.println("    have child level...");
				ElemMap = (HashMap) ElemMap.get("child");
			}
			else {
				System.out.println("<=== element traced done.");
				elemGot = true;
			}
		}

		return elem;
		
	}
	
	private By getBy(String type, String value) {
        By by = null;
        if (type.equals("id")) {
            by = By.id(value);
        }
        if (type.equals("name")) {
            by = By.name(value);
        }
        if (type.equals("tag")) {
        	by = By.tagName(value);
        }
        if (type.equals("xpath")) {
            by = By.xpath(value);
        }
        if (type.equals("className")) {
            by = By.className(value);
        }
        if (type.equals("linkText")) {
            by = By.linkText(value);
        }
        return by;
    }

}
