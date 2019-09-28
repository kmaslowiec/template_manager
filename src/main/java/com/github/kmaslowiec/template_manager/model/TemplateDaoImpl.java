package com.github.kmaslowiec.template_manager.model;

import java.lang.System.Logger;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import com.github.kmaslowiec.template_manager.common.Template;
import com.github.kmaslowiec.template_manager.utils.ObjectFactory;

import lombok.extern.java.Log;



@Log
public class TemplateDaoImpl implements TemplateDao {

	private ConnectData data;
	
	public TemplateDaoImpl() {
		data = new ConnectData();
	}
	
	@Override
	public boolean save(Template temp) {
		
		Map<String, Template> map = data.loadFromFile("dupa");
		if(exists(temp)) {
		log.info(String.format("Template  %s already exists.", temp.getFileName()));
			return false;
		}else {
			map.put(temp.getFileName(), temp);
			data.saveToFile(map, "dupa");
		}
		log.info(String.format("Template  %s saved.", temp.getFileName()));
		return exists(temp);
	}
	
	@Override
	public Template find(Template temp) {
		Template result = ObjectFactory.Template_empty();
		Map<String, Template> map = data.loadFromFile("dupa");
		if(exists(temp)) {
			log.info(String.format("Template  %s found.", temp.getFileName()));
			result = map.get(temp.getFileName());
		}else {
			log.info(String.format("Template  %s not found.", temp.getFileName()));
		}	
		return result;
	}
	
	@Override
	public boolean update(Template temp) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void delete(Template temp) {
		// TODO Auto-generated method stub

	}

	
	
	private boolean exists(Template temp){	
		Map<String, Template> map = data.loadFromFile("dupa");
		return (map.containsKey(temp.getFileName()) && map.containsValue(temp));
	}

	
}
