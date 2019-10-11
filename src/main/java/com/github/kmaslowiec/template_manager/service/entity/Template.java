package com.github.kmaslowiec.template_manager.service.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Template implements Serializable {

	private static final long serialVersionUID = 1L;
	private final String fileName;
	private String tempName;
	private String content;
}