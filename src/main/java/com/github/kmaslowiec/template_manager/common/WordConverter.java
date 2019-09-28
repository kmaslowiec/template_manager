package com.github.kmaslowiec.template_manager.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.github.kmaslowiec.template_manager.utils.ObjectFactory;

public class WordConverter {

	public static final String RESOURCE_PATH = "./src/main/java/com/github/kmaslowiec/template_manager/resources/";

	/*
	 * Read from the docx and parse to txt
	 */
	public Template parseDoc(File path) {
		try {
			FileInputStream fis = new FileInputStream(path);
			XWPFWordExtractor we = new XWPFWordExtractor(new XWPFDocument(fis));
			String text = we.getText();
			Template temp = new Template(fileTitle(path.getName()), fileTitle(path.getName()),  text);
			we.close();
			return temp;
			
		} catch (Exception exep) {
			System.out.println(exep);
			
		}
		return ObjectFactory.Template_empty();
	}
	
	private String fileTitle(String name) {
		String[] arr = name.split("[.]");
		return arr[0];
	}
	
	public void serializeArrayList(List<Template> templates, String path) {
        try
        {
            FileOutputStream fos = new FileOutputStream(RESOURCE_PATH + path);
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
	
	public List<Template> deserializeArrayList(String path){
		ArrayList<Template> templates = new ArrayList<>();
        
        try
        {
            FileInputStream fis = new FileInputStream(RESOURCE_PATH +  path);
            ObjectInputStream ois = new ObjectInputStream(fis);
 
            templates = ((ArrayList) ois.readObject());
 
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
	
	public boolean isListExist(String path) {
		File file = new File(path);
		
		return file.exists();
	}
}