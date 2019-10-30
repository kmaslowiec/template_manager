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
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(MyStringUtils.RESOURCE_TEMPLATE_PATH + baseName))) {
			oos.writeObject(templates);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public Map<String, Template> loadFromFile(String baseName) {
		Map<String, Template> templates = new HashMap<>();

		try (ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream(MyStringUtils.RESOURCE_TEMPLATE_PATH + baseName))) {
			templates = (HashMap<String, Template>) ois.readObject();
		} catch (FileNotFoundException e) {
			log.info(String.format("ConnectData: File %s not found ", baseName));
		} catch (ClassNotFoundException b) {
			log.info(String.format("ConnectData: Class %s not found ", b.getClass().toString()));
			b.printStackTrace();
		} catch (IOException c) {
			c.printStackTrace();
		} 
		return templates;
	}
}