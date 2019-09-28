package com.github.kmaslowiec.template_manager.model;

import java.lang.System.Logger;
import java.util.List;
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
		if (exists(temp)) {
			log.info(String.format("SAVE: Template  %s already exists.", temp.getFileName()));
			return false;
		} else {
			map.put(temp.getFileName(), temp);
			data.saveToFile(map, "dupa");
		}
		log.info(String.format("SAVE: Template  %s saved.", temp.getFileName()));
		return exists(temp);
	}

	@Override
	public Template find(Template temp) {
		Template result = ObjectFactory.Template_empty();

		Map<String, Template> map = data.loadFromFile("dupa");
		if (exists(temp)) {
			log.info(String.format("READ: Template  %s found.", temp.getFileName()));
			result = map.get(temp.getFileName());
		} else {
			log.info(String.format("READ: Template  %s not found.", temp.getFileName()));
		}

		return result;
	}

	@Override
	public boolean update(Template temp) {
		Map<String, Template> map = data.loadFromFile("dupa");

		if (exists(temp)) {
			map.replace(temp.getFileName(), temp);
			data.saveToFile(map, "dupa");
			log.info(String.format("UPDATE: Template  %s is updated.", temp.getFileName()));
		} else {
			log.info(String.format("UPDATE:  %s does not exist.", temp.getFileName()));
			return false;
		}

		Map<String, Template> saved = data.loadFromFile("dupa");
		System.out.println(saved.toString());
		return temp.getContent().contentEquals(saved.get(temp.getFileName()).getContent());
	}

	@Override
	public boolean delete(Template temp) {
		Map<String, Template> map = data.loadFromFile("dupa");

		if (exists(temp)) {
			System.out.println("before " + map.toString());
			map.remove(temp.getFileName());
			System.out.println("after " + map.toString());
			data.saveToFile(map, "dupa");

			log.info(String.format("DELETE: Template  %s has been deleted.", temp.getFileName()));
		} else {
			log.info(String.format("DELETE:  %s does not exist.", temp.getFileName()));
			return false;
		}

		return !exists(temp);
	}

	@Override
	public List<Template> getAll() {
		return (List<Template>) data.loadFromFile("dupa").values();
	}

	private boolean exists(Template temp) {
		Map<String, Template> map = data.loadFromFile("dupa");

		return (map.containsKey(temp.getFileName()));
	}

}