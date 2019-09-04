package com.github.kmaslowiec.template_manager.common;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

public class FilesReader {
	
	public void readFiles() {
		try (Stream<Path> paths = Files.walk(Paths.get("./src/main/java/com/github/kmaslowiec/template_manager/resources/saved_templates"))) {
		    paths
		        .filter(Files::isRegularFile)
		        .forEach(System.out::println);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
