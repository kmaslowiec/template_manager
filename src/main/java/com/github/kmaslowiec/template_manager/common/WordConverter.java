package com.github.kmaslowiec.template_manager.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;


public class WordConverter {
	
	public static final String RESOURCE_PATH = "./src/main/java/com/github/kmaslowiec/template_manager/common/hello.txt";
	
	/*
	 * Read from the docx and parse to txt
	 */
	public void parseDoc(File path) {
		try {
			FileInputStream fis = new FileInputStream(path);
			XWPFWordExtractor we  =new XWPFWordExtractor(new XWPFDocument(fis));
			String text = we.getText();
			System.out.println(path.getName());
			
			Files.write(Paths.get(RESOURCE_PATH + path.getName()), text.getBytes());
			 
		} catch (Exception exep) {
			System.out.println(exep);
		}
	}
}