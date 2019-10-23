package com.github.kmaslowiec.template_manager.controller;

public class TestDriveController {

	public static void main(String[] args) {
		TemplateController test = new TemplateController();
			
		System.out.println(test.readAll().toString());
	}
}