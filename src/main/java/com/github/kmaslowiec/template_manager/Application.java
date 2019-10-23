package com.github.kmaslowiec.template_manager;

import java.awt.EventQueue;

import com.github.kmaslowiec.template_manager.model.dao_impl.TemplateDaoImpl;
import com.github.kmaslowiec.template_manager.views.View;

public class Application {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				TemplateDaoImpl model = new TemplateDaoImpl();
				View view = new View(model);
				model.addListener(view);
				view.setVisible(true);
			}
		});
	}
}