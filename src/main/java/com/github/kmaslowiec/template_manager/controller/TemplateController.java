package com.github.kmaslowiec.template_manager.controller;

import java.io.File;
import java.util.List;

import com.github.kmaslowiec.template_manager.model.dao_impl.TemplateDaoImpl;
import com.github.kmaslowiec.template_manager.service.entity.Template;
import com.github.kmaslowiec.template_manager.service.impl.TemplateServiceImpl;
import com.github.kmaslowiec.template_manager.views.View;

public class TemplateController {

	private TemplateServiceImpl service;
	private TemplateDaoImpl dao;
	private View view;
	
	public TemplateController(TemplateDaoImpl dao, View view) {
		this.dao = dao;
		this.view = view;
		service = new TemplateServiceImpl(dao);	;
	}

	public boolean create(File[] files) {		
		if(files.length!=0) {
			switch(files.length) {
			case 1: return service.saveTemplate(files);
			default: return service.saveManyTemplates(files);	
			}
		}
		return false;
	}
	
	public boolean delete(Template temp) {
		return dao.delete(temp);
	}
	
	public List<Template> readAll() {
		return dao.getAll();
	}
}