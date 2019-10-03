package com.github.kmaslowiec.template_manager.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.github.kmaslowiec.template_manager.model.Template;
import com.github.kmaslowiec.template_manager.views.View;
import com.github.kmaslowiec.template_manager.model.ModelImpl;

public class Controller {
	
	private WordConverter convert; 
	private ModelImpl model;
	private View view;
	
	
	public Controller() {
		this.convert = new WordConverter();
		this.model = new ModelImpl();
	}
	
	
	
	public Controller(ModelImpl model, View view) {
		this.model = model;
		this.view = view;
	}



	public List<Template> parseAndSave(File[] files){
		List<Template> result = new ArrayList<>();
		for(File i : files) {
			Template current = convert.parseFromDocx(i);
			result.add(current);
			model.save(current);
		}
		System.out.println(result.toString());
		return result;
	}
}