package com.github.kmaslowiec.template_manager.common;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class OpenFile {
	
	JFileChooser fileChooser;
	
	public File pickMe() {
		File file = new File("");
		WordConverter read = new WordConverter();
		fileChooser = new JFileChooser();
		FileFilter filter = new FileNameExtensionFilter("Word files", "docx");
		fileChooser.addChoosableFileFilter(filter);
		
		if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {			
			file = fileChooser.getSelectedFile();
			System.out.print(file.toString());
		}
		return file;
	}
}
