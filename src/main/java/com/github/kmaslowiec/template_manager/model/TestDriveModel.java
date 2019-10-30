package com.github.kmaslowiec.template_manager.model;

import com.github.kmaslowiec.template_manager.model.dao_impl.TemplateDaoImpl;
import com.github.kmaslowiec.template_manager.utils.ObjectFactory;

public class TestDriveModel {

	public static void main(String[] args) {
		ConnectData data = new ConnectData();

		TemplateDaoImpl imp = new TemplateDaoImpl();
		//boolean a = imp.templateExists(new Template("dupa", "dupa", "dupa"));
		imp.save(ObjectFactory.Template());
		System.out.println(imp.save(ObjectFactory.Template_name_content("Hello", "cos")));
	}
}