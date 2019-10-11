package com.github.kmaslowiec.template_manager.controller;

import java.io.File;
import java.util.List;

import com.github.kmaslowiec.template_manager.model.dao_impl.TemplateDaoImpl;
import com.github.kmaslowiec.template_manager.service.entity.Template;
import com.github.kmaslowiec.template_manager.service.impl.TemplateServiceImpl;

public class TemplateController {

	private TemplateServiceImpl service;
	private TemplateDaoImpl dao;

	public TemplateController() {
		service = new TemplateServiceImpl();
		dao = new TemplateDaoImpl();
	}

	public boolean save(File[] files) {
		return service.saveTemplate(files);
	}

	public List<Template> getAll() {
		return dao.getAll();
	}
}