package com.github.kmaslowiec.template_manager;

import java.awt.EventQueue;

import com.github.kmaslowiec.template_manager.views.MainFrame;

public class Application {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				//try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
			/*	} catch (Exception e) {
					e.printStackTrace();
				}*/
			}
		});
	}

	/*
	 * Runs and links the app using Model View Presenter pattern
	 */
	private static void runApp() {
		
	}
}
	