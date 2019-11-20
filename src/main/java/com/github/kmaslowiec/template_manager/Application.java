package com.github.kmaslowiec.template_manager;

import java.awt.EventQueue;

import com.github.kmaslowiec.template_manager.controller.TemplateController;
import com.github.kmaslowiec.template_manager.model.dao_impl.TemplateDaoImpl;
import com.github.kmaslowiec.template_manager.views.View;
/**
 * 
 * @author Konrad Maslowiec
 * @version 0.5
 *
 */
public class Application {
	/**
	 * This is the application that reads the text from a docx file,
	 * change it to plain text and save it to Template class.
	 * The templates are serialized and saved in txt file.
	 * All saved templates are listed in JList.
	 * Once the template is selected the content is saved in the system's clipboard.
	 * @param args
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				TemplateDaoImpl model = new TemplateDaoImpl();
				View view = new View(model);
				new TemplateController(model);
				view.setVisible(true);
			}
		});
	}
}