package com.github.kmaslowiec.template_manager.controller;

import java.io.File;

import com.github.kmaslowiec.template_manager.model.Template;

public class CheckController {

	public static void main(String[] args) {
		WordConverter convert = new WordConverter();
		Template temp = convert.parseFromDocx(new File("C:\\Users\\konra\\Desktop\\TempManagerTest\\empty.docx"));
		System.out.printf("Name: %s Content: %s", temp.getFileName(), temp.getContent());
	}

}
