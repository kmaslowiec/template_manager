package com.github.kmaslowiec.template_manager.views;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.github.kmaslowiec.template_manager.common.OpenFile;
import com.github.kmaslowiec.template_manager.common.Template;
import com.github.kmaslowiec.template_manager.common.WordConverter;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.DefaultListCellRenderer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmAddTemplate;
	private OpenFile openFile;
	private JScrollPane scrollPane;
	private JList<Template> list;
	private DefaultListModel<Template> model;
	private JMenuItem mntmAddElement;
	private File chosenFile;
	private WordConverter convert;
	private List<Template> templates;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		openFile = new OpenFile();
		convert = new WordConverter();
		templates = new ArrayList<>();
		
		initComponents();
		createEvents();
		
		if(convert.isListExist(WordConverter.RESOURCE_PATH + "templates")) {
			templates = convert.deserializeArrayList();
			model.addAll(templates);
		}
	}
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		model = new DefaultListModel<Template>();
		list = new JList<Template>(model);
		setupDefaultJListRenderer(list);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmAddTemplate = new JMenuItem("Add template");	
		mnFile.add(mntmAddTemplate);
		mntmAddElement = new JMenuItem("Save templates");
		
		
		mnFile.add(mntmAddElement);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(list);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		initGroupLayout(groupLayout);
		
		getContentPane().setLayout(groupLayout);
	}
	
	private void createEvents() {
		addTemplateEvent();
		addElementEvent();
		templateClickedEvent();
	}
	
	private void addTemplateEvent() {
		mntmAddTemplate.addActionListener(a -> {
			chosenFile = openFile.pickMe();
			Template temp = convert.parseDoc(chosenFile);
			templates.add(temp);
			model.addElement(temp);
		});
	}
	
	private void addElementEvent() {
		mntmAddElement.addActionListener(a -> {
			model.removeAllElements();
			model.addAll(templates);
			convert.serializeArrayList(templates);
		});
	}
	
	private void templateClickedEvent() {
		list.addListSelectionListener(a->{
			if(!a.getValueIsAdjusting()) {
				System.out.println(list.getSelectedValue().getContent());
			}
		});
	}
	
	private void setupDefaultJListRenderer(JList<Template> list) {
		list.setCellRenderer(new DefaultListCellRenderer() {

			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if(renderer instanceof JLabel && value instanceof Template ) {
					((JLabel) renderer).setText(((Template)value).getFileName());
				}
				
				return renderer; 
			}	
		});
	}

	private void initGroupLayout(GroupLayout groupLayout) {
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
					.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 425, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(13, Short.MAX_VALUE))
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
	}

}