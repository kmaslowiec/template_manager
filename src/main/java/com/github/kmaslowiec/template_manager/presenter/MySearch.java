package com.github.kmaslowiec.template_manager.presenter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.github.kmaslowiec.template_manager.model.Template;

class MySearch {
	
	public static List<Template> search(List<Template> list, String word) {
		return list.stream()
					.filter(a -> a.getFileName().toLowerCase().replaceAll("\\s", "")
					.contains(word.toLowerCase().trim()))
					.collect(Collectors.toList());
	}
}