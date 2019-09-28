package com.github.kmaslowiec.template_manager.model;

import java.lang.System.Logger;
import java.util.Map;



import com.github.kmaslowiec.template_manager.common.Template;

import lombok.extern.java.Log;



@Log
public class TemplateDaoImpl implements TemplateDao {

	ConnectData data;
	
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
	public void delete(Template temp) {
		// TODO Auto-generated method stub

	}

	@Override
	public Template find(Template temp) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private boolean exists(Template temp){	
		Map<String, Template> map = data.loadFromFile("dupa");
		return (map.containsKey(temp.getFileName()) && map.containsValue(temp));
	}
}
