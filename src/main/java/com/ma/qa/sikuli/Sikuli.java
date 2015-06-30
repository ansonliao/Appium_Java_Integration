package com.ma.qa.sikuli;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.sikuli.api.*;
import org.sikuli.api.robot.Mouse;
import org.sikuli.api.robot.desktop.DesktopMouse;
import org.sikuli.api.robot.desktop.DesktopScreen;
import org.sikuli.slides.api.Context;
import org.sikuli.slides.api.ExecutionEvent;
import org.sikuli.slides.api.ExecutionFilter;
import org.sikuli.slides.api.SlideExecutionException;
import org.sikuli.slides.api.Slides;
import org.sikuli.slides.api.models.Slide;

import com.ma.qa.commons.CommonUtil;
import com.ma.qa.commons.Constants;
import com.ma.qa.shell.ShellUtil;
import com.ma.qa.utils.Utils;

public class Sikuli {
	
	private static int slideNo = 0, StartSlideNo = 0, EndSlideNo = 0;
	private static Context context = new Context();
	private static boolean slideRange = false;
	
	
	/**
	 * Run single slide of PPTX.
	 */
	public static boolean SlidesRun(String url, int SlideNo) throws InterruptedException, Exception {
		slideNo = SlideNo;
		File file = Utils.downloadFile(url);
		boolean isSucceed = false;
		
		setExecutionFilter(false);
		Utils.iOSSimActive();
		return Run(file);		
	}

	/**
	 * Run a list of slide of PPTX
	 */
	public static boolean SlidesRun(String url, List <Integer> slideList) throws InterruptedException, Exception {
		File file = Utils.downloadFile(url);
		//Context context = new Context();
		Iterator it=slideList.iterator();
		boolean isSucceed = false;
		
		while (it.hasNext()) {
			slideNo = ((Integer)it.next()).intValue();
			setExecutionFilter(false);
			Utils.iOSSimActive();
			isSucceed = Run(file);
			
			if (!isSucceed)
				break;
		}
		
		return isSucceed;
	}
	
	/**
	 * Run a range of slide of PPTX
	 */
	public static boolean SlidesRun(String url, int startSlideNo, int endSlideNo) throws InterruptedException, Exception {
		File file = Utils.downloadFile(url);
		StartSlideNo = startSlideNo;
		EndSlideNo = endSlideNo;
		boolean isSucceed = false;
		
		setExecutionFilter(true);
		Utils.iOSSimActive();
		return Run(file);
	}

    /**
     * Description: For Sikuli Script
     * @param image
     * @exception java.lang.Exception
     */
    public static boolean click(String image) throws Exception {
        Utils.iOSSimActive();
        ScreenRegion s = new DesktopScreenRegion();
        Target target = null;
        if ((image.toLowerCase().contains("https://")) || (image.toLowerCase().contains("http://"))) {
            // For URL image
            target = new ImageTarget(new URL(image));
        } else {
            // For image file of local file system
            target = new ImageTarget(new File(image));
        }

        ScreenRegion r = s.find(target);

        // Create a mouse object
        Mouse mouse = new DesktopMouse();

        try {
            // Use the mouse object to click on the center of the target region
            Utils.iOSSimActive();
            mouse.click(r.getCenter());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        CommonUtil.sleep(1);

        return true;

    }

    public static boolean exist(String image) throws Exception{
        Utils.iOSSimActive();
        ScreenRegion s = new DesktopScreenRegion();
        Target target = null;

        if ((image.toLowerCase().contains("https://")) || (image.toLowerCase().contains("http://"))) {
            // For URL image
            target = new ImageTarget(new URL(image));
        } else {
            // For image file of local file system
            target = new ImageTarget(new File(image));
        }

        ScreenRegion r = s.find(target);

        // Create a mouse object
        Mouse mouse = new DesktopMouse();

        try {
            // Use the mouse object to click on the center of the target region
            Utils.iOSSimActive();
            mouse.click(r.getCenter());
        } catch (Exception e) {
            System.out.println("**** Error! Image file can not be loaded from the screen.\n"  +
                                "     image file is [" + image + "]");
            return false;
        }

        CommonUtil.sleep(1);

        return true;


    }

	
	/**
	 * Set execution fileter for context of Sikuli slides seesion
	 */
	private static void setExecutionFilter(boolean SlideRange) {
		slideRange = SlideRange;
		context.setExecutionFilter(new ExecutionFilter(){
			public boolean accept(ExecutionEvent event) {
				Slide slide = event.getSlide();
				if (slideRange)
					return slide.getNumber() >= StartSlideNo && slide.getNumber() <= EndSlideNo;
				else
					return slide.getNumber() == slideNo;
			}

		});
	}
	
	private static boolean Run(File pptxFile) {
        int counter = 0;
		try {
			Slides.execute(new File(pptxFile.toString()), context);
		}catch (SlideExecutionException e) {
			System.err.println("Failed to execute slide no. " + e.getSlide().getNumber());
			System.err.println(" because " + e.getMessage());			
			System.err.println(" action: " + e.getAction());
			++counter;
		}

        if (counter == 0)
            return true;
        else
            return false;
		
	}



}
