package com.github.kmaslowiec.template_manager.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import com.github.kmaslowiec.template_manager.service.entity.Template;
import com.github.kmaslowiec.template_manager.utils.MyStringUtils;

import lombok.extern.java.Log;

@Log
public class ConnectData {

	public void saveToFile(Map<String, Template> templates, String baseName) {
		try {
			FileOutputStream fos = new FileOutputStream(MyStringUtils.RESOURCE_TEMPLATE_PATH + baseName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(templates);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public Map<String, Template> loadFromFile(String baseName) {
		Map<String, Template> templates = new HashMap<>();

		try (ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream(MyStringUtils.RESOURCE_TEMPLATE_PATH + baseName))) {
			templates = (HashMap<String, Template>) ois.readObject();
		} catch (FileNotFoundException a) {
			log.info(String.format("File %s created by service", baseName));
			return templates;
		} catch (ClassNotFoundException b) {
			log.info(String.format("Class not s% found ", b.getClass().toString()));
			b.printStackTrace();
		} catch (IOException c) {
			c.printStackTrace();
		}

		return templates;
	}
}