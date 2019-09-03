package com.github.kmaslowiec.template_manager.common;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class OpenFile {
	
	JFileChooser fileChooser;
	
	public void pickMe() {
		fileChooser = new JFileChooser();
		FileFilter filter = new FileNameExtensionFilter("Word docx files", "docx");
		fileChooser.addChoosableFileFilter(filter);
		
		if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {			
			File file = fileChooser.getSelectedFile();
			System.out.print(file.toString());
		}
		
	}
}
