package com.github.kmaslowiec.template_manager.presenter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.github.kmaslowiec.template_manager.model.Template;
import com.github.kmaslowiec.template_manager.utils.ObjectFactory;

class WordConverter {

	/*
	 * Read from the docx and parse to txt
	 */
	public Template parseFromDocx(File path) {
		try {
			FileInputStream fis = new FileInputStream(path);
			XWPFWordExtractor we = new XWPFWordExtractor(new XWPFDocument(fis));
			String text = we.getText();
			Template temp = new Template(fileTitle(path.getName()), fileTitle(path.getName()),  text);
			we.close();
			return temp;
			
		} catch (Exception exep) {
			System.out.println(exep);
			
		}
		return ObjectFactory.Template_empty();
	}
	
	private String fileTitle(String name) {
		String[] arr = name.split("[.]");
		return arr[0];
	}
	
	public boolean isListExist(String path) {
		File file = new File(path);
		
		return file.exists();
	}
}