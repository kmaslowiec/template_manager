package com.github.kmaslowiec.template_manager;

import java.awt.EventQueue;

import com.github.kmaslowiec.template_manager.views.View;

public class Application {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				View view = new View();
				view.setVisible(true);
			}
		});
	}	
}