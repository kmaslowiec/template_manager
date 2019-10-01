package com.github.kmaslowiec.template_manager.utils;

import com.github.kmaslowiec.template_manager.model.Template;

public class ObjectFactory {
	
	
	public static Template Template() {
		return Template.builder().fileName(MyStringUtils.FILE_NAME).tempName(MyStringUtils.TEMP_NAME).content(MyStringUtils.CONTENT).build();
	}
	
	public static Template Template_empty() {
		return Template.builder().fileName(new String()).tempName(new String()).content(new String()).build();
	}
	
	public static Template Template_name_content(String fileName, String content) {
		return Template.builder().fileName(fileName).tempName(MyStringUtils.TEMP_NAME).content(content).build();
	}		
}