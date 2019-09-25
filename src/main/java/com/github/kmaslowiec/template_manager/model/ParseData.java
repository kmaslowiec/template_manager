package com.github.kmaslowiec.template_manager.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

import com.github.kmaslowiec.template_manager.common.Template;
import com.github.kmaslowiec.template_manager.utils.MyStringUtils;

public class ParseData {
	
	public void serializeArrayList(Map<String, Template> templates, String baseName) {
        try
        {
            FileOutputStream fos = new FileOutputStream(MyStringUtils.resourceTemplatePath + baseName);
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

}
