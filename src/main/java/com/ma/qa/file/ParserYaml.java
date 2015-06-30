package com.ma.qa.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class ParserYaml {
	private static String yamlPath = null;
	private static String separator = File.separator;
	
	public ParserYaml (String yamlPath){
		this.yamlPath = yamlPath;
	}
	
	public Map getYamlFile() {
		Map yamlMap = null;
		File f = new File(this.yamlPath);
		try{
			InputStream input = new FileInputStream(new File(this.yamlPath));
			Yaml yaml = new Yaml();
			yamlMap = (HashMap) yaml.load(input);
		}
		catch (FileNotFoundException e){
			System.out.println("File: " + this.yamlPath + " was not found.");
			e.printStackTrace();
		}
				
		return yamlMap;
	}
	
	public static void main(String [] agrv) throws FileNotFoundException{
		String yamlPath = "Resource" + separator + "ElemMap.yaml";
		ParserYaml y = new ParserYaml(yamlPath);
		System.out.println(y.getYamlFile().keySet());
	}

}
