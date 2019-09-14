package com.github.kmaslowiec.template_manager.common;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MySearch {
	
	public static List<Template> search(List<Template> list, String word) {
		return list.stream()
					.filter(a -> a.getFileName().toLowerCase().replaceAll("\\s", "")
					.contains(word.toLowerCase().trim()))
					.collect(Collectors.toList());
	}
}