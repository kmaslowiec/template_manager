package com.github.kmaslowiec.template_manager.common;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class WordConverter {

	public static final String RESOURCE_PATH = "./src/main/java/com/github/kmaslowiec/template_manager/resources/saved_templates/";

	/*
	 * Read from the docx and parse to txt
	 */
	public void parseDoc(File path) {
		try {
			FileInputStream fis = new FileInputStream(path);
			XWPFWordExtractor we = new XWPFWordExtractor(new XWPFDocument(fis));
			String text = we.getText();
			Files.write(Paths.get(RESOURCE_PATH + txtFileName(path.getName())), text.getBytes());
			we.close();
		} catch (Exception exep) {
			System.out.println(exep);
		}
	}

	private String txtFileName(String name) {
		String[] arr = name.split("[.]");
		return arr[0] + ".txt";
	}
}