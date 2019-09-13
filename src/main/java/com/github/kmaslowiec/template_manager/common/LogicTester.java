package com.github.kmaslowiec.template_manager.common;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

public class LogicTester {

	public static void main(String[] args) {
		MySearch ser = new MySearch();
		
		ser.search(new ArrayList<>(), "NoN");
	  }
}
