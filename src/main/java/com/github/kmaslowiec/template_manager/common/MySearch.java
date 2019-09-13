package com.github.kmaslowiec.template_manager.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.transform.Templates;

public class MySearch {
	
	public static final String TEST_WORD = "Kondzik";
	private Template[] temp = {new Template("name", ""), new Template("No Name", ""), new Template("Ko Name", "")};
	private List<Template> list = new ArrayList<>(Arrays.asList(temp));
	
	public List<Template> search(List<Template> list, String word) {
		List<Template> results = new ArrayList<>();
		list=this.list;
		for(Template i : list) {
			if(i.getFileName().toLowerCase().replaceAll("\\s", "").contains(word.toLowerCase().trim())) {
				results.add(i);
			}
		}
		
		System.out.println(results.toString());
		return results;
	}
	
	public List<Template> searchJ8(List<Template> list, String word) {
		List<Template> results = new ArrayList<>();
		list=this.list;
		results = list.stream()
				.filter(a -> a.getFileName().toLowerCase().replaceAll("\\s", "")
				.contains(word.toLowerCase().trim()))
				.collect(Collectors.toList());
		
		System.out.println(results.toString());
		return results;
	}
}