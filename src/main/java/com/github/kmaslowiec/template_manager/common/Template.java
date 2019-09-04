package com.github.kmaslowiec.template_manager.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Template {
	
	private String fileName;
	private String content;
}