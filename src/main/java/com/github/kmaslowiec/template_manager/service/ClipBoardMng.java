package com.github.kmaslowiec.template_manager.service;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class ClipBoardMng {
	
	public void copyToClipboard(String content) {
		StringSelection selection = new StringSelection(content);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, selection);  
	  }
}