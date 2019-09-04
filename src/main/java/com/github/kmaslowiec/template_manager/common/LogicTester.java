package com.github.kmaslowiec.template_manager.common;

public class LogicTester {

	public static void main(String[] args) {
		FilesReader read = new FilesReader();	
		WordConverter convert = new WordConverter();
		if(read.isListExist(WordConverter.RESOURCE_PATH + "templates")){
			String print = convert.deserilizeArrayList().toString();
			System.out.println(print);
		}
	}
}