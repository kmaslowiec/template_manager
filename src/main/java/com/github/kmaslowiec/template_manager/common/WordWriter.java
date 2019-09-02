package com.github.kmaslowiec.template_manager.common;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class WordWriter {
	
	/*
	 * Read from the docx file in the directory
	 */
		public void readWord() {
			try {
				File file = new File("test.docx");
				FileInputStream fis = new FileInputStream(file);
				XWPFDocument document = new XWPFDocument(fis);
				
				List<XWPFParagraph> paragraphs = document.getParagraphs();
				for(int i=0;i<paragraphs.size();i++){
				    System.out.println(paragraphs.get(i).getParagraphText());
				}
				fis.close();
				} catch (Exception e) {
				e.printStackTrace();
				}
		}
		
		public List<String> readWordToList(String filePath) {
			List<String> temp = new ArrayList<>();
			
			try {
				File file = new File(filePath);
				FileInputStream fis = new FileInputStream(file);
				XWPFDocument document = new XWPFDocument(fis);
				
				List<XWPFParagraph> paragraphs = document.getParagraphs();
				for(int i=0;i<paragraphs.size();i++){
				    temp.add(paragraphs.get(i).getParagraphText());
				}
				fis.close();
				} catch (Exception e) {
				e.printStackTrace();
				}
			return temp;
		}
}