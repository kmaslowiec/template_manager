package com.github.kmaslowiec.template_manager.common;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Template implements Serializable {
	
	private String fileName;
	private String content;
}