package com.github.kmaslowiec.template_manager.common;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class LogicTester {

	public static void main(String[] args) {
	    Clipboard clipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();
        // print the last copied thing
        Transferable t = clipBoard.getContents(null);
        if (t.isDataFlavorSupported(DataFlavor.stringFlavor))
			try {
				StringSelection data = new StringSelection("dupa");
		        clipBoard.setContents(data, data);
		        System.in.read();
		        
		        //System.in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
	  }
}
