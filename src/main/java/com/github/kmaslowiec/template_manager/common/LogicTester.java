package com.github.kmaslowiec.template_manager.common;

import java.util.Arrays;
import java.util.Map;

import com.github.kmaslowiec.template_manager.model.ConnectData;
import com.github.kmaslowiec.template_manager.model.TemplateDaoImpl;
import com.github.kmaslowiec.template_manager.utils.CollectionFactory;
import com.github.kmaslowiec.template_manager.utils.ObjectFactory;

public class LogicTester {

	public static void main(String[] args) {
		/*
		 * CollectionFactory<String, Template> map = new CollectionFactory<>(); Template
		 * temp = ObjectFactory.Template(); Template temp01 =
		 * ObjectFactory.Template_name_content("second", "something"); Template temp02 =
		 * ObjectFactory.Template_name_content("third", "other");
		 * 
		 * Map<String, Template> hash = map.hashMapBuilder(new String[]
		 * {temp.getFileName(), temp01.getFileName(), temp02.getFileName()}, new
		 * Template[] {temp, temp01, temp02});
		 * 
		 * ConnectData con = new ConnectData(); con.saveToFile(hash, "dupa");
		 * con.loadFromFile("dupa");
		 */
		
		TemplateDaoImpl test = new TemplateDaoImpl();
		test.save(ObjectFactory.Template_name_content("inne", "Test dao"));
			
	 
	}
}