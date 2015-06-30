package com.ma.qa.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

/**
 * Created by Anson on 16/7/14.
 */

public class Config {
	private static String yamlFile = null;
    private static Map yamlMap = null;

	/*
	public getConf(String yamlPath) {
		this.yamlFile = yamlPath;
	}
	*/

    public static void setYamlFilePath(String yamlPath) throws FileNotFoundException {
        yamlFile = yamlPath;
        parseYaml(yamlFile);
    }

    public static void clear() {
        yamlFile = null;
        yamlMap = null;
    }

    public static String getTestType() {
        return yamlMap.get("testType").toString().trim();
    }

    public static String getAppDir() {
        return yamlMap.get("appDir").toString().trim();
    }

    public static String getApp() {
        return yamlMap.get("app").toString().trim();
    }

    public static String getBrowserName() {
        if(yamlMap.get("browserName") == null)
            return "";
        else
            return yamlMap.get("browserName").toString().trim();
    }

    public static String getDeviceName() {
        return yamlMap.get("deviceName").toString().trim();
    }

    public static String getPlatformName() {
        return yamlMap.get("platformName").toString().trim();
    }

    public static String getPlatformVersion() {
        return yamlMap.get("platformVersion").toString().trim();
    }

    public static String getURL() {
        return yamlMap.get("url").toString().trim();
    }
    
    public static boolean getNativeWebTap() {
    	if(yamlMap.get("nativeWebTap").toString().trim().equalsIgnoreCase("true"))
    		return true;
    	else
    		return false;
    }

    public static float getImageSimilar() {
        return (float) Double.parseDouble(
                yamlMap.get("imageSimilar").toString().trim()
        );
    }
    
    public static String getConfig(String configKey) {
    	if(yamlMap.containsKey(configKey)){
    		if(yamlMap.get(configKey) != null)
    			return yamlMap.get(configKey).toString().trim();
    		else
    			return "";
    	}
    	else 
    		return "";
    	
    }
    

    private static Map parseYaml(String yamlPath) throws FileNotFoundException {
        InputStream input = new FileInputStream(new File(yamlPath));
        Yaml yaml = new Yaml();
        yamlMap = (HashMap) yaml.load(input);
        return yamlMap;
    }

	/**
	public static void main(String[] args) throws FileNotFoundException {
		Map yamlMap = null;
		String yamlFile = "res/conf/conf1.yaml";
		getConf g = new getConf(yamlFile);
		InputStream input = new FileInputStream(new File(yamlFile));
		Yaml yaml = new Yaml();
		yamlMap = (HashMap) yaml.load(input);

		System.out.println(yamlMap);

		String device = yamlMap.get("device").toString();
		System.out.println("device: " + device);

	}
	*/

}
