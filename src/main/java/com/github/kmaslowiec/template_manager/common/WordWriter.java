package com.github.kmaslowiec.template_manager.common;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
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
		
		public void writeToTxtFile(List<String> list){
				FileWriter writer;
				try {
					writer = new FileWriter("TextTest.txt");
					for(String i : list) {
						writer.append(i + "\n");
					}
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};	
		}
		
		public void parseDoc(){
		               	 
				POIFSFileSystem fs = null;
		        try {
				 fs = new POIFSFileSystem(new FileInputStream("test.docx"));
			     HWPFDocument doc = new HWPFDocument(fs);
				 WordExtractor we = new WordExtractor(doc);
				 String text = we.getText();
			     File fil = new File("hello.txt");
				 Writer output = new BufferedWriter(new FileWriter(fil));
				 output.write(text);
				 output.close();
			} catch (Exception exep) {
				 System.out.println(exep);
			}
		}
}