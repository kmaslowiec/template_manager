package com.github.kmaslowiec.template_manager.views;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class OpenFile {
	
	JFileChooser fileChooser;
	
	/*
	 * public File pickMe() { File file = new File(""); WordConverter read = new
	 * WordConverter(); fileChooser = new JFileChooser(); FileFilter filter = new
	 * FileNameExtensionFilter("Word files", "docx");
	 * fileChooser.addChoosableFileFilter(filter);
	 * 
	 * if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) { file =
	 * fileChooser.getSelectedFile(); System.out.print(file.toString()); } return
	 * file; }
	 */
	
	public File[] pickMany() {
		File[] files = {};
		
		//WordConverter read = new WordConverter();
		fileChooser = new JFileChooser();
		FileFilter filter = new FileNameExtensionFilter("Word files", "docx");
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setMultiSelectionEnabled(true);
		
		if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {			
			files = fileChooser.getSelectedFiles();
			System.out.print(files.toString());
		}
		return files;
	}
}
