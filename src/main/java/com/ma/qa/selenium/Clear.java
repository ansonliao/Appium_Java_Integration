package com.ma.qa.selenium;

import java.io.IOException;
import com.ma.qa.shell.ShellUtil;
import com.ma.qa.sikuli.SikuliX;

public class Clear {
	
	public static void clearDriver() throws IOException {
		WebDriverManager.clear();
		iOSKeyboard.clear();
		WBAction.clear();
		ShellUtil.executeShell("rm -f res/PowerPoint/*.pptx");
		ShellUtil.executeShell("rm -f log/*.*");
        SikuliX.clear();

		
	}

}
