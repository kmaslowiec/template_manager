package com.github.kmaslowiec.template_manager.common;

import java.util.List;

public class LogicTester {

	public static void main(String[] args) {
		WordWriter word = new WordWriter();
		//word.readWord();
		List<String> list = word.readWordToList("test.docx");
		list.forEach(System.out::println);
	}

}
