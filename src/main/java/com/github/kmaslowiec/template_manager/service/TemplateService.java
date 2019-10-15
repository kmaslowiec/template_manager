package com.github.kmaslowiec.template_manager.service;

import java.io.File;

public interface TemplateService {
	
	public boolean saveTemplate(File[] files);
	public boolean saveManyTemplates(File[] files);
	public boolean updateTemplate(File[] files);
}