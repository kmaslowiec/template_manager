package com.github.kmaslowiec.template_manager.model;

import com.github.kmaslowiec.template_manager.common.Template;

import lombok.extern.slf4j.Slf4j;

interface TemplateDao {
	
	public boolean save(Template temp);
	public boolean update(Template temp);
	public void delete(Template temp);
	public Template find(Template temp);

}