package com.github.kmaslowiec.template_manager.common;

import java.io.File;

import javax.swing.JFileChooser;

public class OpenFile {
	
	JFileChooser fileChooser = new JFileChooser();
	
	public void pickMe() {
		if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			System.out.print(file.toString());
		}
		
	}
}
