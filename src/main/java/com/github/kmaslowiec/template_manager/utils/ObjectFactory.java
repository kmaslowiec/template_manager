package com.github.kmaslowiec.template_manager.utils;

import com.github.kmaslowiec.template_manager.common.Template;

public class ObjectFactory {
	
	
	public static Template Template_name_content() {
		return Template.builder().fileName(MyStringUtils.FILE_NAME).content(MyStringUtils.CONTENT).build();
	}
	
	public static Template Template_name_content(String fileName, String content) {
		return Template.builder().fileName(fileName).content(content).build();
	}		
}