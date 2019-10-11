package com.github.kmaslowiec.template_manager.service.impl;

import java.io.File;

import com.github.kmaslowiec.template_manager.model.dao_impl.TemplateDaoImpl;
import com.github.kmaslowiec.template_manager.service.TemplateService;
import com.github.kmaslowiec.template_manager.service.WordParser;
import com.github.kmaslowiec.template_manager.service.entity.Template;

public class TemplateServiceImpl implements TemplateService{
	
	private WordParser parser;
	private TemplateDaoImpl dao;

	public TemplateServiceImpl() {
		this.parser = new WordParser();
		this.dao = new TemplateDaoImpl();
	}

	@Override
	public boolean saveTemplate(File[] files) {
		
		Template template = parser.parseFromDocx(files[0]);
		
		return dao.save(template);
	}
}