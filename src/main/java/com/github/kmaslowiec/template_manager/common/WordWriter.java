package com.github.kmaslowiec.template_manager.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.Writer;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


public class WordWriter {

	/*
	 * Read from the docx and parse to txt
	 */
	public void parseDoc() {
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