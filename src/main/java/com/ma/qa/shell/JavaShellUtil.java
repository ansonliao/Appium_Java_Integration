package com.ma.qa.shell;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class JavaShellUtil {
        private static String basePath = "/Users/Anson/Downloads";
        private static String executeShellLogFile = basePath + "/executeShell.txt";
        private static final String sendKondorShellName = basePath + "sendKondorFile.sh";
        
        public void setPath(String path){
        	basePath = path;
        }
        
        public void setLog(String logfile){
        	executeShellLogFile = basePath + "/" + logfile;
        }

        public int executeShell(String shellCommand) throws IOException {
                int success = 0;
                StringBuffer stringBuffer = new StringBuffer();
                BufferedReader bufferedReader = null;
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS ");
                try {
                        stringBuffer.append(dateFormat.format(new Date())).append("Read for Shell command ").append(shellCommand).append(" \r\n");
                        Process pid = null;
                        String[] cmd = {"/bin/sh", "-c", shellCommand};
                        pid = Runtime.getRuntime().exec(cmd);
                        if (pid != null) {
                                stringBuffer.append("Process ID: ").append(pid.toString()).append("\r\n");
                                bufferedReader = new BufferedReader(new InputStreamReader(pid.getInputStream()), 1024);
                                pid.waitFor();
                        } else {
                                stringBuffer.append("No PID.\r\n");
                        }
                        stringBuffer.append(dateFormat.format(new Date())).append("Result of Shell execution: \r\n");
                        String line = null;
                        while (bufferedReader != null &&(line = bufferedReader.readLine()) != null) {
                                stringBuffer.append(line).append("\r\n");
                        }
                        
                        BufferedReader stdError = new BufferedReader(new InputStreamReader(pid.getErrorStream()));
                        while (stdError != null &&(line = stdError.readLine()) != null) {
                            stringBuffer.append(line).append("\r\n");
                        }


                } catch (Exception ioe) {
                        stringBuffer.append("Exception from Shell execution: \r\n").append(ioe.getMessage()).append("\r\n");
                } finally {
                        if (bufferedReader != null) {
                                OutputStreamWriter outputStreamWriter = null;
                                try {
                                        bufferedReader.close();
                                        OutputStream outputStream = new FileOutputStream(executeShellLogFile);
                                        outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                                        outputStreamWriter.write(stringBuffer.toString());
                                } catch (Exception e) {
                                        e.printStackTrace();
                                } finally {
                                        outputStreamWriter.close();
                                }
                        }
                        success = 1;
                }
                return success;
        }
}