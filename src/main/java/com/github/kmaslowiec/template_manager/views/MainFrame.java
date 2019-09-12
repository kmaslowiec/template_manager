package com.github.kmaslowiec.template_manager.views;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.github.kmaslowiec.template_manager.common.ClipBoardMng;
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
import javax.swing.KeyStroke;
import javax.swing.DefaultListCellRenderer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.LayoutStyle.ComponentPlacement;

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
	private ClipBoardMng board;

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
		board = new ClipBoardMng();
		openFile = new OpenFile();
		convert = new WordConverter();
		templates = new ArrayList<>();

		initComponents();
		createEvents();

		if (convert.isListExist(WordConverter.RESOURCE_PATH + "saved_templates/templates")) {
			templates = convert.deserializeArrayList("saved_templates/templates");
			model.addAll(templates);
		}
	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 461, 312);

		model = new DefaultListModel<Template>();

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnFile = new JMenu("File");
		menuBar.add(mnFile);

		mntmAddTemplate = new JMenuItem("Add template");
		mnFile.add(mntmAddTemplate);
		mntmAddElement = new JMenuItem("Save templates");

		mnFile.add(mntmAddElement);

		scrollPane = new JScrollPane();

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
					.addGap(10))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
					.addGap(13))
		);
		list = new JList<Template>(model);
		scrollPane.setViewportView(list);
		setupDefaultJListRenderer(list);

		getContentPane().setLayout(groupLayout);
	}

	private void createEvents() {
		addTemplateEvent();
		saveTemplates();
		templateClickedEvent();
	}

	private void addTemplateEvent() {
		mntmAddTemplate.addActionListener(a -> {	
			 File[] files = openFile.pickMany();
			 
			 for(File i:files) {
				 Template temp = convert.parseDoc(i);
				 templates.add(temp); 
			 }
			 model.removeAllElements();
			 model.addAll(templates);		
		});
		
	}

	private void saveTemplates() {
		mntmAddElement.addActionListener(a -> {
			model.removeAllElements();
			if (templates != null) {
				model.addAll(templates);
				convert.serializeArrayList(templates, "saved_templates/templates");
			}
		});
	}

	private void templateClickedEvent() {
		list.addListSelectionListener(a -> {
			if (!a.getValueIsAdjusting()) {
				board.copyToClipboard(list.getSelectedValue().getContent());			
			}
		});
	}

	private void setupDefaultJListRenderer(JList<Template> list) {
		list.setCellRenderer(new DefaultListCellRenderer() {

			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (renderer instanceof JLabel && value instanceof Template) {
					((JLabel) renderer).setText(((Template) value).getFileName());
				}

				return renderer;
			}
		});
	}

}