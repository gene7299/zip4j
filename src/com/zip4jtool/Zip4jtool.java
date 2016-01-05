package com.zip4jtool;

import java.io.File;
import java.util.List;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

public class Zip4jtool {

	public static boolean AddFolder(String sAbsolutePath, String Destation, String Password) {
		boolean ret = false;
		File Fdest = new File(Destation);
    	if(Fdest.exists())
    	{
    		Fdest.delete();
    	}
		try {
			// Initiate ZipFile object with the path/name of the zip file.
			ZipFile zipFile = new ZipFile(Destation);
			
			// Folder to add
			String folderToAdd = sAbsolutePath;
			
			// Initiate Zip Parameters which define various properties such
			// as compression method, etc.
			ZipParameters parameters = new ZipParameters();
			
			parameters.setReadHiddenFiles(true);
			parameters.setIncludeRootFolder(false);
						
			// set compression method to store compression
			parameters.setCompressionMethod(Zip4jConstants.COMP_STORE);
			
			// Set the compression level
			//parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
			
            parameters.setEncryptFiles(true);
            parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
            parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
            parameters.setPassword(Password);
			
			// Add folder to the zip file
			zipFile.addFolder(folderToAdd, parameters);
			ret = true;
		} catch (ZipException e) {
			e.printStackTrace();
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return ret;
	}
    
	
	
	public static boolean ExtractByLoopAllFiles(String sAbsolutePath, String Destation, String Password) {
		boolean ret = false;
		try {
			// Initiate ZipFile object with the path/name of the zip file.
			ZipFile zipFile = new ZipFile(sAbsolutePath);
			
			// Check to see if the zip file is password protected 
			if (zipFile.isEncrypted()) {
				// if yes, then set the password for the zip file
				zipFile.setPassword(Password);
			}
			
			// Get the list of file headers from the zip file
			List fileHeaderList = zipFile.getFileHeaders();
			
			// Loop through the file headers
			for (int i = 0; i < fileHeaderList.size(); i++) {
				FileHeader fileHeader = (FileHeader)fileHeaderList.get(i);
				// Extract the file to the specified destination
				zipFile.extractFile(fileHeader, Destation);
			}
			ret = true;
		} catch (ZipException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
}