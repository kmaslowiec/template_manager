package com.github.kmaslowiec.template_manager.service;

import java.io.File;
import java.util.List;

import com.github.kmaslowiec.template_manager.service.entity.Template;

public interface TemplateService {
	
	boolean saveTemplate(File[] files);
	boolean saveManyTemplates(File[] files);
	boolean updateTemplate(File[] files);
	boolean deleteTemplate(Template template);
	Template readTemplate(Template temp);
	List<Template> readAllTemplates();
}