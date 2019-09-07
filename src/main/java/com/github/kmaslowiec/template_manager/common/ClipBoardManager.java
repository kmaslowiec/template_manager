package com.github.kmaslowiec.template_manager.common;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

public class ClipBoardManager {
	
	private Clipboard board;

	public void savetToClipboard(String string) {
		/*
		 * Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		 * Transferable transferable = new StringSelection(string);
		 * clipboard.setContents(transferable, null);
		 */
		
		
		 
	}
	
	public void saveInLinux(String str) {
		Runtime run = Runtime.getRuntime();
		Process p = null;
		try {
		        p = run.exec(new String[]{"sh", "-c", "echo " + str + " | xclip=\"xclip -selection c"});
		}
		catch (Exception e) {
		    System.out.println(e);
		}
	}
}
