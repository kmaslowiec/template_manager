package com.github.kmaslowiec.template_manager.views;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;

import com.github.kmaslowiec.template_manager.common.OpenFile;
import com.github.kmaslowiec.template_manager.common.Template;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.AbstractListModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmAddTemplate;
	private OpenFile openFile = new OpenFile();
	private JScrollPane scrollPane;
	private JList list;
	private DefaultListModel<Template> model;
	private JMenuItem mntmAddElement;

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
		initComponents();
		createEvents();
	}
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		model = new DefaultListModel<Template>();
		list = new JList<Template>(model);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmAddTemplate = new JMenuItem("Add template");	
		mnFile.add(mntmAddTemplate);
		mntmAddElement = new JMenuItem("Add element");
		addElementEvent(new Template("Test", "Content"), model);
		mnFile.add(mntmAddElement);
		
		scrollPane = new JScrollPane();
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
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
	
		
		
		
		scrollPane.setViewportView(list);
		list.addListSelectionListener(a->{
			if(!a.getValueIsAdjusting()) {
				System.out.println(list.getSelectedValue().toString());
			}
		});
		
		getContentPane().setLayout(groupLayout);
	}
	
	private void createEvents() {
		addTemplateEvent();
	}
	
	private void addTemplateEvent() {
		mntmAddTemplate.addActionListener(a -> {
			openFile.pickMe();
		});
	}
	
	private void addElementEvent(Template temp, DefaultListModel<Template> model) {
		mntmAddElement.addActionListener(a -> {
			model.addElement(temp);
		});
	}
}