package com.github.kmaslowiec.template_manager.presenter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.github.kmaslowiec.template_manager.model.Template;
import com.github.kmaslowiec.template_manager.model.TemplateDaoImpl;

public class Presenter {
	
	WordConverter convert; 
	TemplateDaoImpl dao;
	
	public Presenter() {
		this.convert = new WordConverter();
		this.dao = new TemplateDaoImpl();
	}
	
	public List<Template> parseAndSave(File[] files){
		List<Template> result = new ArrayList<>();
		for(File i : files) {
			Template current = convert.parseFromDocx(i);
			result.add(current);
			dao.save(current);
		}
		System.out.println(result.toString());
		return result;
	}
}
