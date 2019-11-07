package com.github.kmaslowiec.template_manager.controller;

import java.io.File;
import java.util.List;

import com.github.kmaslowiec.template_manager.model.dao_impl.TemplateDaoImpl;
import com.github.kmaslowiec.template_manager.service.entity.Template;
import com.github.kmaslowiec.template_manager.service.impl.TemplateServiceImpl;

public class TemplateController {

	private TemplateServiceImpl service;
	private TemplateDaoImpl dao;
	
	public TemplateController(TemplateDaoImpl dao) {
		this.dao = dao;
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