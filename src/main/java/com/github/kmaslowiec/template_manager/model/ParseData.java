package com.github.kmaslowiec.template_manager.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import com.github.kmaslowiec.template_manager.common.Template;
import com.github.kmaslowiec.template_manager.utils.MyStringUtils;

public class ParseData {
	
	public void serializeArrayList(Map<String, Template> templates, String baseName) {
        try
        {
            FileOutputStream fos = new FileOutputStream(MyStringUtils.RESOURCE_TEMPLATE_PATH + baseName);
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
	
	public Map<String, Template> deserializeArrayList(String baseName){
		Map<String, Template> templates = new HashMap<>();
        
        try
        {
            FileInputStream fis = new FileInputStream(MyStringUtils.RESOURCE_TEMPLATE_PATH + baseName);
            ObjectInputStream ois = new ObjectInputStream(fis);
 
            templates = ((HashMap) ois.readObject());
 
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
