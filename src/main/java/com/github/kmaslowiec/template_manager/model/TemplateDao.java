package com.github.kmaslowiec.template_manager.model;

import java.util.List;

import com.github.kmaslowiec.template_manager.service.entity.Template;

public interface TemplateDao {

	public boolean save(Template temp);

	public boolean saveAll(List<Template> temps);

	public boolean update(Template temp);

	public boolean delete(Template temp);

	public List<Template> getAll();

	public Template find(Template temp);
}