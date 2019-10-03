package com.github.kmaslowiec.template_manager;

import java.awt.EventQueue;

import com.github.kmaslowiec.template_manager.controller.Controller;
import com.github.kmaslowiec.template_manager.model.ModelImpl;
import com.github.kmaslowiec.template_manager.views.View;

public class Application {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				//try {
					View frame = new View();
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
		ModelImpl model = new ModelImpl();
		View view = new View();
		Controller controller = new Controller(model, view);
	}
}
	