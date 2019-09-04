package com.github.kmaslowiec.template_manager.common;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Template implements Serializable {
	
	private String fileName;
	private String content;
}