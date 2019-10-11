package com.github.kmaslowiec.template_manager.views;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class OpenFile {

	JFileChooser fileChooser;

	public File[] pickMany() {
		File[] files = {};

		fileChooser = new JFileChooser();
		FileFilter filter = new FileNameExtensionFilter("Word files", "docx");
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setMultiSelectionEnabled(true);

		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			files = fileChooser.getSelectedFiles();
			System.out.print(files.toString());
		}
		return files;
	}
}