package com.github.kmaslowiec.template_manager.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class WordConverter {

	public static final String RESOURCE_PATH = "./src/main/java/com/github/kmaslowiec/template_manager/resources/saved_templates/";

	/*
	 * Read from the docx and parse to txt
	 */
	public Template parseDoc(File path) {
		try {
			FileInputStream fis = new FileInputStream(path);
			XWPFWordExtractor we = new XWPFWordExtractor(new XWPFDocument(fis));
			String text = we.getText();
			Template temp = new Template(fileTitle(path.getName()), text);
			we.close();
			System.out.println(temp.toString());
			return temp;
			//Files.write(Paths.get(RESOURCE_PATH + txtFileName(path.getName())), text.getBytes());
			
		} catch (Exception exep) {
			System.out.println(exep);
			
		}
		return new Template("", "");
	}

	private String txtFileName(String name) {
		String[] arr = name.split("[.]");
		return arr[0] + ".txt";
	}
	
	private String fileTitle(String name) {
		String[] arr = name.split("[.]");
		return arr[0];
	}
	
	public void serializeArrayList(Template temp) {
		ArrayList<Template> templates = new ArrayList<>();      
        templates.add(temp);
 
        try
        {
            FileOutputStream fos = new FileOutputStream(RESOURCE_PATH + "templates");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(templates);
            oos.close();
            fos.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
	}
	
	public List<Template> deserializeArrayList(){
		ArrayList<Template> templates = new ArrayList<>();
        
        try
        {
            FileInputStream fis = new FileInputStream(RESOURCE_PATH + "templates");
            ObjectInputStream ois = new ObjectInputStream(fis);
 
            templates = (ArrayList) ois.readObject();
 
            ois.close();
            fis.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        catch (ClassNotFoundException c)
        {
            System.out.println("Class not found");
            c.printStackTrace();
        }
		
		return templates;
	}
}