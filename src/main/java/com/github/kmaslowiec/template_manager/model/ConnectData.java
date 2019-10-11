package com.github.kmaslowiec.template_manager.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import com.github.kmaslowiec.template_manager.service.entity.Template;
import com.github.kmaslowiec.template_manager.utils.MyStringUtils;

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
			if(ois.readObject() instanceof HashMap) {
				templates = (HashMap<String, Template>) ois.readObject();
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException c) {
			System.out.println("Class not found here");
			c.printStackTrace();
		}

		return templates;
	}
}
