package com.ma.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.ma.qa.commons.CommonUtil;
import com.ma.qa.shell.ShellUtil;
import org.apache.commons.io.FileUtils;
import org.yaml.snakeyaml.Yaml;

import com.ma.qa.commons.Constants;


public class Utils {
	
	/*** for debug
	public static void main(String[] args){
		System.out.println(downloadFile("https://docs.google.com/presentation/d/1gxlG5hdxbDCZDVozSAqqw2Zd9-EnEcV8pqnuajj2TLA/edit?usp=sharing").toString());
	}
	***/
	
	public static File downloadFile(String URL){
    	URL downloadURL = getDownloadLink(URL);
    	if(downloadURL == null){
    		System.out.println("Error: Invalid URL.");
    		return null;
    	}
    	File destination = null;
		try {
			File directory = new File(Constants.PPTX_DIRECTORY);
			directory.mkdir();
			destination = File.createTempFile("download", ".pptx", directory);
			System.out.println("The file is being downloaded, please wait...");
			FileUtils.copyURLToFile(downloadURL, destination, 300000, 30000);
			System.out.println("Download complete.");
		} catch (IOException e) {
			System.out.println("Error while downloading the file.");
			destination = null;
		}
		catch (NullPointerException npe) {
			System.out.println("Error while downloading the file.");
			destination = null;
		}
    	
    	return destination;
    }
	
    private static URL getDownloadLink(String downloadLink){
    	// Example input: https://docs.google.com/presentation/d/1-qXEu7jYvm1Oql-hBcjgXU5zLQUGWd_uGH6mc8buRkI/export/pptx
    	//https://docs.google.com/presentation/d/1-qXEu7jYvm1Oql-hBcjgXU5zLQUGWd_uGH6mc8buRkI/export/pptx
    	if(downloadLink != null){
    		try {
				URI uri = new URI(downloadLink);
				
				// check if the domain is Google.com
				String domain = uri.getHost();
				if(domain == null){
					return null;
				}
				String domainName = domain.startsWith("www.")? domain.substring(4) : domain;
				// download the remote .pptx file
				// 1) The file is hosted on google drive
				if(domainName.equalsIgnoreCase("docs.google.com")){
					try{
						int startIndex = downloadLink.indexOf("/d/") + 3;
						int lastIndex = downloadLink.indexOf("/edit");
						String documentId = downloadLink.substring( startIndex, lastIndex);
						
						// construct GoogleDrive download link
						String gDriveDownloadLink = "https://docs.google.com/presentation/d/" + 
								documentId + "/export/pptx";
						return new URL(gDriveDownloadLink);
					}
					catch(StringIndexOutOfBoundsException e){
						System.out.println("ERROR: Invalid Google Drive link.");
					}
				}
				// 2) The file is hosted on a remote server.
				else{
					return new URL(downloadLink);
				}
			} catch (URISyntaxException e) {
				System.out.println("ERROR: Invalid share link.");
			}
    		catch (MalformedURLException e){
    			System.out.println("ERROR: Invalid share link.");
    		}
    	}
    	return null;
    }
    
    public static Map parseYaml (String yamlPath) {
    	Map yamlMap = new HashMap();
    	try{
			Yaml yaml = new Yaml();
			yamlMap = (HashMap) yaml.load(new FileInputStream(new File(yamlPath)));
		}
		catch (FileNotFoundException e){
			System.out.println("File: " + yamlPath + " was not found.");
			e.printStackTrace();
		}
				
		return yamlMap;
    }

    public static void iOSSimActive() throws InterruptedException, Exception {
        String activeSimCMD = "osascript " + Constants.SHELL_DIRECTORY + File.separator + "activate_sim";
        String resizeSimCMD = "osascript " + Constants.SHELL_DIRECTORY + File.separator + "resize_sim 3";
        ShellUtil.executeShell(activeSimCMD);
        CommonUtil.sleep(1);
        ShellUtil.executeShell(resizeSimCMD);

    }

}
