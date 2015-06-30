package com.ma.qa.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileUtil {
	
	//private static String basepath = "/User/Anson/AutoTest/BVES";
	public static String baselogpath = "/Users/Anson/Documents/BVESTest";
	private static String fullpath = "", readStr = "";
	private static String logfile = "log.txt";
	private static String logpath = "";
	private static File filename;
	

	//Get Dest Log folder.
	public static String getLogFolder(String baseDir){
		//File dirFile = new File(baselogpath);
		File dirFile = new File(baseDir);
		String[] files = dirFile.list();
		String destfolder;
		long maxno = 0;
		
		for(int i = 0; i< files.length; i++){
			fullpath = baselogpath + "/" + files[i];
			if(new File(fullpath).isDirectory()){
				if(Long.parseLong(files[i]) > maxno){
					maxno = Long.parseLong(files[i]);
				}
			}
		}
		
		destfolder = Long.toString(maxno);
		fullpath = baselogpath + "/" + destfolder;
		logpath = fullpath + "/" + logfile;
		return destfolder;
	}

	//Create Log text file
	public static boolean createLogFile() throws IOException{
		boolean result = true;
		logpath = fullpath + "/" + logfile;
		filename = new File(logpath);
		if(!filename.exists()){
			filename.createNewFile();
		}
		else
		{
			result = true;
		}
		
		if(!filename.exists()){
			result = false;
		}
		
		
		return result;
	}
	
	//Read Log file
	public static String readLogFile(String path) throws IOException{
		BufferedReader buffread = null;
		FileReader fileread = null;
		String read;
		filename = new File(logpath);
		
		try{
			//System.out.println(filename);
			fileread = new FileReader(filename);
			buffread = new BufferedReader(fileread);
			try{
				while((read = buffread.readLine()) != null){
					readStr = readStr + read + "\r\n";
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(buffread != null){
				try{
					buffread.close();
				}catch(IOException e1){
					e1.printStackTrace();
				}
				
				if(fileread != null){
					try{
						fileread.close();
					}catch(IOException e2){
						e2.printStackTrace();
					}
				}
			}
		}		
		//Debug
		//System.out.println("File content: " + "\r\n" + readStr);
		return readStr;
	}
	
	
	//find substring in string
	public static boolean findString(String fpath, String subStr){
		BufferedReader buffread = null;
		FileReader fileread = null;
		String read = null;
		boolean found = false;
		filename = new File(logpath);
		
		try{
			//System.out.println(filename);
			fileread = new FileReader(filename);
			buffread = new BufferedReader(fileread);
			try{
				while((read = buffread.readLine()) != null){
					if(read.contains(subStr)){
						found = true;
						break;
					}
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(buffread != null){
				try{
					buffread.close();
				}catch(IOException e1){
					e1.printStackTrace();
				}
				
				if(fileread != null){
					try{
						fileread.close();
					}catch(IOException e2){
						e2.printStackTrace();
					}
				}
			}
		}		
		
		return found;
	}
	
	
	//get line context 
	public static String getLinetext(String fpath, String subStr){
		BufferedReader buffread = null;
		FileReader fileread = null;
		String read = null, destStr = null;
		boolean found = false;
		filename = new File(logpath);
		
		try{
			//System.out.println(filename);
			fileread = new FileReader(filename);
			buffread = new BufferedReader(fileread);
			try{
				while((read = buffread.readLine()) != null){
					if(read.contains(subStr)){
						destStr= read;
						break;
					}
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(buffread != null){
				try{
					buffread.close();
				}catch(IOException e1){
					e1.printStackTrace();
				}
				
				if(fileread != null){
					try{
						fileread.close();
					}catch(IOException e2){
						e2.printStackTrace();
					}
				}
			}
		}		
		
		return destStr;
	}
	
	//get line context 
		public static long geTextLineNo(String fpath){
			BufferedReader buffread = null;
			FileReader fileread = null;
			String read = null;
			long lineno = 0;
			filename = new File(logpath);
			
			try{
				//System.out.println(filename);
				fileread = new FileReader(filename);
				buffread = new BufferedReader(fileread);
				try{
					while((read = buffread.readLine()) != null){
						++lineno;
					}
				}catch(IOException e){
					e.printStackTrace();
				}
			}catch(IOException e){
				e.printStackTrace();
			}finally{
				if(buffread != null){
					try{
						buffread.close();
					}catch(IOException e1){
						e1.printStackTrace();
					}
					
					if(fileread != null){
						try{
							fileread.close();
						}catch(IOException e2){
							e2.printStackTrace();
						}
					}
				}
			}		
			
			return lineno;
		}
	
	//Write content to log file
	public static void writeLogFile(String path, String newStr) throws IOException{
		String saveStr = readLogFile(path);
		String writeStr = saveStr + "\r\n" + newStr + "\r\n";
		
		RandomAccessFile rf = null;
		try{
			rf = new RandomAccessFile(filename, "rw");
			rf.writeBytes(writeStr);
		}catch(IOException e1){
			e1.printStackTrace();
		}finally{
			if(rf != null){
				try{
					rf.close();
				}catch(IOException e3){
					e3.printStackTrace();
				}
			}
		}
	}
	
	//Test
	/*
	public static void main(String[] args) throws IOException{
		getLogFolder();
		createLogFile();
		System.out.println(readLogFile());
		writeLogFile("testing");
	}
	*/
	
}
