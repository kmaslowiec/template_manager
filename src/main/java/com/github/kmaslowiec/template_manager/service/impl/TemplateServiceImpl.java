package com.github.kmaslowiec.template_manager.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.github.kmaslowiec.template_manager.model.dao_impl.TemplateDaoImpl;
import com.github.kmaslowiec.template_manager.service.TemplateService;
import com.github.kmaslowiec.template_manager.service.WordParser;
import com.github.kmaslowiec.template_manager.service.entity.Template;

public class TemplateServiceImpl implements TemplateService {

	private WordParser parser;
	private TemplateDaoImpl dao;

	public TemplateServiceImpl(TemplateDaoImpl dao) {
		this.parser = new WordParser();
		this.dao = dao;
	}

	@Override
	public boolean saveTemplate(File[] files) {
		Template template = parser.parseFromDocx(files[0]);
	
		return dao.save(template);
	}

	@Override
	public boolean saveManyTemplates(File[] files) {
		List<Template> temps = new ArrayList<>();

		for (File i : files) {
			temps.add(parser.parseFromDocx(i));
		}

		return dao.saveAll(temps);
	}

	@Override
	public boolean updateTemplate(File[] files) {
		Template template = parser.parseFromDocx(files[0]);

		return dao.update(template);
	}

	@Override
	public List<Template> readAllTemplates() {
		return dao.getAll();
	}
}