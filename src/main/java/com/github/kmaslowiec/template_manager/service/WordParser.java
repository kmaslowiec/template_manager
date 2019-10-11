package com.github.kmaslowiec.template_manager.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.github.kmaslowiec.template_manager.service.entity.Template;
import com.github.kmaslowiec.template_manager.utils.ObjectFactory;

public class WordParser {

	/*
	 * Read from the docx and parse to txt
	 */
	public Template parseFromDocx(File path) {

		try {
			FileInputStream fis = new FileInputStream(path);
			XWPFWordExtractor we = new XWPFWordExtractor(new XWPFDocument(fis));
			String text = we.getText().isEmpty() ? "" : we.getText();
			Template temp = new Template(fileTitle(path.getName()), fileTitle(path.getName()), text);
			we.close();
			return temp;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (org.apache.poi.EmptyFileException e) {
			return new Template(fileTitle(path.getName()), fileTitle(path.getName()), "");
		}
		return ObjectFactory.Template_empty();
	}

	private String fileTitle(String name) {
		String[] arr = name.split("[.]");
		return arr[0];
	}
}