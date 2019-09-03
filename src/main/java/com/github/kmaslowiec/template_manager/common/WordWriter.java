package com.github.kmaslowiec.template_manager.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.Writer;
	
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;


public class WordWriter {

	/*
	 * Read from the docx and parse to txt
	 */
	public void parseDoc() {
		// POIFSFileSystem fs = null;
		try {
		//	fs = new POIFSFileSystem(new FileInputStream("test.docx"));
			FileInputStream fis = new FileInputStream("test.docx");
			XWPFWordExtractor we  =new XWPFWordExtractor(new XWPFDocument(fis));
			String text = we.getText();
			System.out.println(text);
			/*
			 * File fil = new File("//common//hello.txt"); Writer output = new
			 * BufferedWriter(new FileWriter(fil)); output.write(text); output.close();
			 */
		} catch (Exception exep) {
			System.out.println(exep);
		}
	}
}