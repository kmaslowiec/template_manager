package com.github.kmaslowiec.template_manager;

import java.io.File;

import com.github.kmaslowiec.template_manager.controller.TemplateSaveListener;

public interface ListenersSetup {
	
	void setTemplateSaveListener(TemplateSaveListener listener);
	void fireTemplateSaveEvent(File[] file);
}
