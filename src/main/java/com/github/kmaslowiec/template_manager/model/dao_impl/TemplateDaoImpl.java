package com.github.kmaslowiec.template_manager.model.dao_impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.kmaslowiec.template_manager.model.TemplateDao;
import com.github.kmaslowiec.template_manager.model.ConnectData;
import com.github.kmaslowiec.template_manager.model.DbListener;
import com.github.kmaslowiec.template_manager.service.entity.Template;
import com.github.kmaslowiec.template_manager.utils.ObjectFactory;

import lombok.extern.java.Log;

@Log
public class TemplateDaoImpl implements TemplateDao {

	private ConnectData data;
	private String saveFileName = "dupa7";
	private List<DbListener> listeners;

	public TemplateDaoImpl() {
		data = new ConnectData();
		listeners = new ArrayList<>();
	}

	@Override
	public boolean save(Template temp) {
		Map<String, Template> map = new HashMap<>();
		if (templateExists(temp)) {
			log.info(String.format("SAVE: Template  %s already exists.", temp.getFileName()));
			return false;
		} else {
			map = data.loadFromFile(saveFileName);
			map.put(temp.getFileName(), temp);
			data.saveToFile(map, saveFileName);
			notifyListeners();
		}
		log.info(String.format("SAVE: Template  %s saved.", temp.getFileName()));
		return templateExists(temp);
	}

	@Override
	public Template find(Template temp) {
		Template result = ObjectFactory.Template_empty();

		Map<String, Template> map = data.loadFromFile(saveFileName);
		if (templateExists(temp)) {
			log.info(String.format("READ: Template  %s found.", temp.getFileName()));
			result = map.get(temp.getFileName());
		} else {
			log.info(String.format("READ: Template  %s not found.", temp.getFileName()));
		}

		return result;
	}

	@Override
	public boolean saveAll(List<Template> temps) {
		Map<String, Template> map = data.loadFromFile(saveFileName);
		if (map.isEmpty()) {
			for (Template i : temps) {
				map.put(i.getFileName(), i);
				data.saveToFile(map, saveFileName);
			}
			notifyListeners();
		} else {
			for (Template i : temps) {
				map.putIfAbsent(i.getFileName(), i);
				data.saveToFile(map, saveFileName);
			}
			notifyListeners();
		}

		return map.equals(data.loadFromFile(saveFileName));
	}

	@Override
	public boolean update(Template temp) {
		Map<String, Template> map = data.loadFromFile(saveFileName);

		if (templateExists(temp)) {
			map.replace(temp.getFileName(), temp);
			data.saveToFile(map, saveFileName);
			log.info(String.format("UPDATE: Template  %s is updated.", temp.getFileName()));
			notifyListeners();
		} else {
			log.info(String.format("UPDATE:  %s does not exist.", temp.getFileName()));
			return false;
		}

		Map<String, Template> saved = data.loadFromFile(saveFileName);
		System.out.println(saved.toString());
		return temp.getContent().contentEquals(saved.get(temp.getFileName()).getContent());
	}

	@Override
	public boolean delete(Template temp) {
		Map<String, Template> map = data.loadFromFile(saveFileName);

		if (templateExists(temp)) {
			map.remove(temp.getFileName());
			data.saveToFile(map, saveFileName);
			notifyListeners();
			log.info(String.format("DELETE: Template  %s has been deleted.", temp.getFileName()));
		} else {
			log.info(String.format("DELETE:  %s does not exist.", temp.getFileName()));
			return false;
		}

		return !templateExists(temp);
	}

	@Override
	public List<Template> getAll() {
		Map<String, Template> map = data.loadFromFile(saveFileName);
		System.out.println(saveFileName);
		Collection<Template> temps = map.values();
		return new ArrayList<Template>(temps);
	}

	private boolean templateExists(Template temp) {
		Map<String, Template> map = data.loadFromFile(saveFileName);

		return (map.containsKey(temp.getFileName()));
	}
	
	public void addListener(DbListener observer ) {
		if(listeners!=null) {
			listeners.add(observer);
		}
	}
	
	private void notifyListeners() {
		if(listeners.size()>0 && listeners!=null ) {
			for(DbListener i : listeners) {
				i.dbUpdated();
			}
		}
	}
}