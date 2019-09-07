package com.github.kmaslowiec.template_manager.common;

public class LogicTester {

	public static void main(String[] args) {
		ClipBoardManager man = new ClipBoardManager();
		
		//man.savetToClipboard("dupa");
		
		man.saveInLinux("dupa");
	}
}