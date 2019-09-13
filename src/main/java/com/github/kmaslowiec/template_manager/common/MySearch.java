package com.github.kmaslowiec.template_manager.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.transform.Templates;

public class MySearch {
	
	public static final String TEST_WORD = "Kondzik";
	private Template[] temp = {new Template("name", ""), new Template("No Name", ""), new Template("Ko Name", "")};
	private List<Template> list = new ArrayList<>(Arrays.asList(temp));
	
	public List<Template> search(List<Template> list, String word) {
		List<Template> results = new ArrayList<>();
		list=this.list;
		for(Template i : list) {
			//System.out.printf("word a: %s word b %s \n", i.getFileName().toLowerCase().replaceAll("\\s", ""),  word.toLowerCase().strip());
			
			if(i.getFileName().toLowerCase().replaceAll("\\s", "").contains(word.toLowerCase().trim())) {
				results.add(i);
			}
		}
		
		System.out.println(results.toString());
		return results;
	}
}
