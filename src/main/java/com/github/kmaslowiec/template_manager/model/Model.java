package com.github.kmaslowiec.template_manager.model;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

interface Model {
	
	public boolean save(Template temp);
	public boolean update(Template temp);
	public boolean delete(Template temp);
	public List<Template> getAll();
	public Template find(Template temp);

}